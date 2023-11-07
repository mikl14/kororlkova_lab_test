import Tm_dat.TM_base;
import Tm_dat.Tm_messages.Tm_message;
import Tm_dat.Tm_messages.Tm_message_start;
import Tm_dat.Tm_messages.Tm_message_time;
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
    public Map<Integer,Telemetry_data> XML_datas = new HashMap<>();

    public Map<Integer,String> ION_datas = new HashMap<>();

    int[] values = new int[10];

    byte[] bytes = new byte[1];

    public Map<Short,TM_base> TMS = new HashMap<>();

    public Map<String,Tm_message> Messages = new HashMap<>();
    Config config;

    Data_Reader(Config config)
    {
        this.config = config;
    }


    public void ParceTm()
    {
        TM_base tm;
        Tm_message mes = new Tm_message((short)0xffff);
        byte prev_byte = 0;
        int byte_counter = 0,counter = 0;
        boolean is_message = false;
        byte[] message_title = new byte[8];
        Tm_message_start mes_start = new Tm_message_start((short)0xffff);

        for(byte current_byte : bytes)
        {

                if(((current_byte&0xFF) == 0xFF & (prev_byte&0xFF) == 0xFF))
                {
                    mes = new Tm_message((short)(current_byte + prev_byte));
                    //Messages.put(counter,mes);
                    is_message = true;
                    byte_counter = 2;

                    continue;
                }
                if(is_message)
                {
                    if(byte_counter < 5)
                    {
                        mes.time += current_byte;
                    }
                    if(byte_counter == 6)
                    {
                        mes.SetMessageType(current_byte);
                    }
                    if(byte_counter == 7)
                    {
                        mes.SetTypeData(current_byte);
                        counter++;
                        //mes.print();
                    }
                    if(byte_counter >= 8)
                    {
                        message_title[byte_counter - 8] = current_byte;
                        if(byte_counter ==15) {
                            switch (mes.type_message)
                            {
                                case 0:
                                    Messages.put("Type:"+mes.type_message+"Number:"+counter,mes);
                                    break;
                                case 1:
                                    mes_start = new Tm_message_start(mes);
                                    mes_start.Parse_message(message_title);
                                    Messages.put("Type:"+mes.type_message+"Number:"+counter,mes_start);
                                    break;
                                case 2:
                                    Tm_message_time mes_time = new Tm_message_time(mes);
                                    mes_time.Parce_time(message_title);
                                    Messages.put("Type:"+mes.type_message+"Number:" + counter,mes_time);
                                    mes_time.print();
                                    break;
                            }
                            is_message = false;
                            byte_counter = 0;
                        }
                        //continue;*/

                    }
                }
                    //Messages.put(counter,mes);
                    prev_byte = current_byte;
                    byte_counter++;

        }
        System.out.println(Messages.size());
      // System.out.println(counter);
    }
    public TM_base Get_TM(int start_index)
    {
        TM_base tm = new TM_base();

        tm.setParam_number((short) (bytes[start_index + 0] + bytes[start_index+1]));

        int buf = 0;
        for(int i = start_index+2;i < 6;i++) buf += values[start_index+ i];

        tm.setParam_time(buf);
        tm.setSize(bytes[start_index + 6]);
        tm.setAtrib(bytes[start_index + 7]);
        tm.print();
        return tm;
    }
    public void Read_KNP(){

        try {
            File file = new File(config.Tmi_IN);
            FileInputStream fileInputStream = new FileInputStream(file);

            bytes = fileInputStream.readAllBytes();

            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        values = new int[bytes.length];

        for(int i = 0 ; i < bytes.length;i++)
        {
            values[i] = bytes[i] &  0xFF;
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
                     ION_datas.put(index,line);
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

                            buf = new Telemetry_data(((Element) node).getTagName(),Integer.parseInt(((Element) node).getAttribute("number")));
                            buf.SetDescription( Get_Description((Element) node));

                            buf.SetTextes(Get_Text(((Element) node)));

                            XML_datas.put(buf.GetParamNumber(),buf);


                }

            }
        }

    }
}
