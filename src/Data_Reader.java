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

    Config config;

    Data_Reader(Config config)
    {
        this.config = config;
    }

    public void Read_KNP(){
        try {
            File file = new File(config.Tmi_IN);
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
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
