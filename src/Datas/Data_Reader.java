package Datas;

import Config.Config;
import Tm_dat.TM_base;
import Tm_dat.Tm_DataRecords.Tm_dataBuilder;
import Tm_dat.Tm_messages.Tm_message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

public class Data_Reader
{

    Data_Buffer datbuf = new Data_Buffer();
    int[] values = new int[10];
    byte[] bytes = new byte[1];

    Config config;

    public Data_Reader(Config config)
    {
        this.config = config;
    }


    public void ParceTm()
    {

        File file = new File(config.Tmi_IN);

        try(FileInputStream fileInputStream = new FileInputStream(file)) {

            byte[] recordInBytes = new byte[16];
            while (fileInputStream.read(recordInBytes) != -1) {
                byte[] paramNum = Arrays.copyOf(recordInBytes, 2);
                byte[] bTime = Arrays.copyOfRange(recordInBytes, 2, 6);
                TM_base record;
                if ((paramNum[0] & 0xFF) == 0xFF && (paramNum[1] & 0xFF) == 0xFF) { // Служебная

                    byte bMes = recordInBytes[6];
                    byte bZnach = recordInBytes[7];
                    byte[] bData = Arrays.copyOfRange(recordInBytes, 8, 16);
                    if(bMes == 1){
                        byte[] extraData = new byte[16];
                        fileInputStream.read(extraData);
                        byte[] buff = bData;
                        bData = new byte[24];
                        System.arraycopy(buff, 0, bData, 0, 8);
                        System.arraycopy(extraData, 0, bData, 8, 16);
                    }
                    record = new Tm_message(paramNum, bTime, bMes, bZnach, bData);
                    //record.print();
                }
                else{                                           // Обычная
                    byte dim = recordInBytes[6];
                    byte atr_type = recordInBytes[7];
                    byte[] bData = Arrays.copyOfRange(recordInBytes, 8, 16);
                    if((atr_type&0x0F) == 3){
                        int extraDataSize = ((Byte.toUnsignedInt(recordInBytes[10]) << 8) + Byte.toUnsignedInt(recordInBytes[11])) - 4;
                        byte[] extraData = new byte[extraDataSize];
                        fileInputStream.read(extraData);

                        byte[] buff = bData;
                        bData = new byte[extraDataSize + 8];
                        System.arraycopy(buff, 0, bData, 0, 8);
                        System.arraycopy(extraData, 0, bData, 8, extraDataSize);
                    }
                    record = Tm_dataBuilder.createDataRecord(paramNum, bTime, dim, atr_type, bData);

                }
                datbuf.putRecord(record.param_number,record);

            }
        }
       catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Read_Demention()
    {
        String path = config.Dim_IN;
        File file = new File(path);
        int index = 1;
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
             String line;
             while ((line = br.readLine()) != null)
             {
                 if(line.length() > 0)
                 {
                     datbuf.ION_put(index,line);
                   //  System.out.println(line);
                 }
                 index++;
             }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void Xml_Read_data()
    {
        String path = config.Dataxml_IN;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document = null;
        try {
            document = builder.parse(new File(path));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element root = document.getDocumentElement();

        NodeList nodeList = root.getChildNodes();

        ArrayList<Element> lst = new ArrayList<Element>();

        for(int i = 0; i < nodeList.getLength();i++)
        {
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                lst.add(element);
            }
        }

        AddData_to_datas(lst);
    }


    private String Get_Description(Element elem)
    {
        NodeList nodeList = elem.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if(((Element)node).getTagName() == "Description")
            {
                return ((Element)node).getTextContent();
            }

        }
        return "";

    }

    private String[] Get_Text(Element elem)
    {
        NodeList nodeList = elem.getElementsByTagName("Text");

        ArrayList<String> textes = new ArrayList<String>();

        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                textes.add(((Element)node).getTextContent());
            }

        }
        return textes.toArray(new String[0]);

    }

    void AddData_to_datas(ArrayList<Element> lst)
    {
        NodeList nodeList;

        for (Element elem: lst)
        {
            nodeList = elem.getElementsByTagName("Param");


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Telemetry_data buf ;
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                           // buf = new Telemetry_data(((Element) node).getTagName(),Integer.parseInt(((Element) node).getAttribute("number")));
                            buf = new Telemetry_data(((Element) node).getAttribute("name"),Integer.parseInt(((Element) node).getAttribute("number")));
                            buf.SetDescription( Get_Description((Element) node));

                            buf.SetTextes(Get_Text(((Element) node)));

                            datbuf.XML_put(buf.GetParamNumber(),buf);
                }

            }
        }

    }
}
