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
    private ArrayList<Telemetry_data> datas= new ArrayList<Telemetry_data>();

    private ArrayList<String> lines = new ArrayList<String>();

    Data_Reader(String path)
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
            document = builder.parse(new File(path));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element root = document.getDocumentElement();

        NodeList nodeList = root.getChildNodes();

        String[] arr = new String[10];
        ArrayList<Element> lst = new ArrayList<Element>();

        for(int i = 0; i < nodeList.getLength();i++)
        {
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                lst.add(element);
                //System.out.println(element.getTagName());
                //System.out.println(lst.get(i));

            }
        }

       lst = Insider(lst,nodeList,3);
        AddData_to_datas(lst,nodeList);
    }

    ArrayList<Element> Insider(ArrayList<Element> lst,NodeList nodeList,int level )
    {
        if(level == 0)
        {
            level = Inside_Tags(lst,nodeList).size();
        }
        while(level > 0)   // можно перенести в метод и контролить глубину вхождения во вложеность
        {
            lst = Inside_Tags(lst,nodeList);
            level--;
        }
        return lst;

    }
    ArrayList<Element> Inside_Tags(ArrayList<Element> lst,NodeList nodeList)
    {
        ArrayList<Element> new_lst = new ArrayList<Element>();

        for(int i = 0; i < lst.size();i++)
        {
            nodeList = lst.get(i).getChildNodes();
            //System.out.println(elements.get(i).getTagName());

            for(int j = 0 ; j < nodeList.getLength();j++)
            {
                Node node = nodeList.item(j);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    //elements.add(element);
                    //Tmi_IN = element.getAttribute("in");
                    if(!lst.contains(element))
                    {
                        new_lst.add(element);
                    }


                }
            }
        }

        return new_lst;
    }

    void AddData_to_datas(ArrayList<Element> lst,NodeList nodeList)
    {
        NodeList descriptNodelist;
        NodeList TextList,TextsList;
        for (Element elem: lst)
        {
            nodeList = elem.getElementsByTagName("Param");
            descriptNodelist = elem.getElementsByTagName("Description");
            TextsList = elem.getElementsByTagName("Textes");
            //TextList = elem.getElementsByTagName("Text");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                     datas.add(new Telemetry_data(element.getAttribute("name"),element.getAttribute("number")));

                     try
                     {
                         descriptNodelist = ((Element) node).getElementsByTagName("Description");
                         datas.get(i).SetDescription(descriptNodelist.item(0).getTextContent());
                     }
                     catch (Exception e)
                     {
                         datas.get(i).SetDescription("-");
                     }


                    // for(int j = 0 ; j < TextsList.getLength();j++)
                    // {
                        try
                        {

                         TextList = ((Element) node).getElementsByTagName("Text");
                         Node nodeText = TextsList.item(i);
                         System.out.println(" ");

                         for(int k = 0 ; k < TextList.getLength();k++)
                         {
                             nodeText = TextList.item(k);

                             if (nodeText.getNodeType() == Node.ELEMENT_NODE)
                             {
                                 Element elementText = (Element) nodeText;
                                 lines.add(elementText.getTextContent());
                                 //datas.get(i).AddTextes(elementText.getTextContent());

                             }
                            // Node nodeText = TextList.item(o);
                           //  Node nodeText = TextList.item(j);
                         }

                        }
                        catch (Exception e)
                        {
                            lines.add(" ");
                        }

                            datas.get(i).AddTextes(lines);

                            lines.clear();


                     datas.get(i).print();
                     System.out.println("/////");
                    //Dim_OUT = element.getAttribute("out");
                }
            }



        }






    }


}
