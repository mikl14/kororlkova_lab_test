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
import java.io.ObjectInputStream;

public class Config
{
    public static String Tmi_IN,Tmi_OUT,Dataxml_IN,Dataxml_OUT,Dim_IN,Dim_OUT ;

    Config()
    {
        read();
    }



    public void read()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document = null;
        try {
            document = builder.parse(new File("D:\\kororlkova_lab_test\\src\\config.xml"));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element root = document.getDocumentElement();


        NodeList nodeList = root.getElementsByTagName("tmi");
        for(int i = 0; i < nodeList.getLength();i++)
        {
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                Tmi_IN = element.getAttribute("in");
                Tmi_OUT = element.getAttribute("out");
            }
        }


        nodeList = root.getElementsByTagName("dataxml");
        for(int i = 0; i < nodeList.getLength();i++)
        {
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                Dataxml_IN = element.getAttribute("in");
                Dataxml_OUT = element.getAttribute("out");
            }
        }

            nodeList = root.getElementsByTagName("dim");
            for(int i = 0; i < nodeList.getLength();i++)
            {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    Dim_IN = element.getAttribute("in");
                    Dim_OUT = element.getAttribute("out");
                }
            }
    }


    public void print()
    {
     System.out.println('\n'+Tmi_IN + " " + Tmi_OUT + '\n' + Dataxml_IN +" " +Dataxml_OUT + '\n' +Dim_IN+ " " +Dim_OUT );
    }
}
