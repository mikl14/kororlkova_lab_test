import Config.Config;
import Datas.Data_Buffer;
import Datas.Data_Reader;
import Datas.Data_Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Data_Buffer datbuf = new Data_Buffer();

        JFrame frame = new JFrame("sss");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton but = new JButton("Run");

        JLabel label = new JLabel("click!");

        JPanel panel = new JPanel();
        panel.setSize(100,100);
        panel.add(but);
        panel.add(label);
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                label.setText("Clicked!");
                Config configer = new Config();

                configer.print();

                Data_Reader ss = new Data_Reader(configer);

                ss.Xml_Read_data();

                ss.Read_Demention();

                ss.ParceTm();


                Data_Writer wr = new Data_Writer();


                wr.Create_ION_txt(configer.Dim_OUT,datbuf.GetION());

                wr.Create_XML_txt(configer.Dataxml_OUT,datbuf.GetXML());
            }
        });


        frame.add(panel);


        frame.pack();
        frame.setSize(500,500);
        frame.getContentPane().add(BorderLayout.SOUTH,panel);
        frame.setVisible(true);
        /*ss.ParceTm();

        Datas.Data_Writer wr = new Datas.Data_Writer();


        wr.Create_ION_txt(configer.Dim_OUT,ss.ION_datas);

        wr.Create_XML_txt(configer.Dataxml_OUT,ss.XML_datas);

        */





        // Datas.Data_Reader dr = new Datas.Data_Reader("C:\\Users\\mikle\\Desktop\\KNP-173.14.33.58.dat.xml");
    }


}