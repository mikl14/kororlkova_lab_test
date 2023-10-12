// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Config configer = new Config();

        configer.print();

        Data_Reader ss = new Data_Reader(configer);

        ss.Xml_Read_data();

        ss.Read_Demention();

        Data_Writer wr = new Data_Writer();

        wr.Create_txt(configer.Dataxml_OUT,ss.XML_datas);


        // Data_Reader dr = new Data_Reader("C:\\Users\\mikle\\Desktop\\KNP-173.14.33.58.dat.xml");
    }
}