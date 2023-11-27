package Datas;

import Config.Config;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ReadXML {

    public static Config config;
    protected static Map<Integer,Telemetry_data> XML_datas = new HashMap<>();
    public static void XML_put(int key,Telemetry_data data)
    {
        XML_datas.put(key,data);
    }



    public static Map<Integer,Telemetry_data> GetXML()
    {
        return XML_datas;
    }


    public static String getParamName(int key)
    {
        return XML_datas.get(key).Param_name;
    }

    public static String getText(int num){

        return XML_datas.get(num).GetTextes();
    }

    public static int findXMlbyName(String name)
    {
        for(Map.Entry<Integer,Telemetry_data> entry : XML_datas.entrySet())
        {
            if(entry.getValue().Param_name.equals(name))
            {
                return entry.getValue().Param_number;
            }

        }
        return -1;
    }

    public static List<Telemetry_data> mapToList(Map<Integer, Telemetry_data> map) {
        List<Telemetry_data> list = new ArrayList<>();

        for(Map.Entry<Integer,Telemetry_data> entry : XML_datas.entrySet())
        {
            if(ReadTMI.Record_datas.containsKey(entry.getValue().Param_number))
            {
                list.add(entry.getValue());
            }
        }
        Collections.sort(list, new Comparator<Telemetry_data>() {
            @Override
            public int compare(Telemetry_data o1, Telemetry_data o2) {
                return o1.Param_name.compareTo(o2.Param_name);
            }
        });
        return list;

    }

    public static void Xml_Read_data()
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

    private static String Get_Description(Element elem)
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

    private static String[] Get_Text(Element elem)
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

    static void AddData_to_datas(ArrayList<Element> lst)
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
                    buf.SetDescription(Get_Description((Element) node));

                    buf.SetTextes(Get_Text(((Element) node)));

                    XML_put(buf.GetParamNumber(),buf);
                }

            }
        }

    }
}
