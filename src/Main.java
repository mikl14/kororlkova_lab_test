import Config.Config;
import Datas.Data_Buffer;
import Datas.Data_Reader;
import Datas.Data_Writer;
import Datas.Telemetry_data;
import Tm_dat.TM_base;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.ComponentView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void createGUI(){
        Data_Buffer datbuf = new Data_Buffer();

        JFrame frame = new JFrame("CUP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton butXML = new JButton("read XML");
        JButton butION = new JButton("read Dimension");
        JButton butKNP = new JButton("read KNP");



//        JLabel label = new JLabel("click!");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(butXML);
        panel.add(butION);
        panel.add(butKNP);

        Config config = new Config();
        config.print();
       Data_Reader data_reader = new Data_Reader(config);
        data_reader.Xml_Read_data();
        data_reader.Read_Demention();
        data_reader.ParceTm();

        JLabel info = new JLabel("last pressed button");
        panel.add(info);
//        Data_Writer wr = new Data_Writer();
//        wr.Create_ION_txt(config.Dim_OUT,datbuf.GetION());
//        wr.Create_XML_txt(config.Dataxml_OUT,datbuf.GetXML());
//        wr.Create_Records_txt(config.Tmi_OUT,datbuf.GetRecords());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(10,20);


        //textArea.setText("a\n b\n s\n d \nsd\n sa\n dsa\n da\n sdajsdkj\n eofjen\n i \n nn \n n \n n \n n");


        textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollPane);
        butXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setText("XML_pressed");
            }
        });
        butION.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setText("ION_pressed");
            }
        });
        butKNP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setText("KNP_pressed");

                int count = 0;

                for (Map.Entry<Integer,Telemetry_data> entry : datbuf.GetXML().entrySet())
                {
                    textArea.append(entry.getValue().Param_name + '\n');
                   // if(count++ > 100) break;
                }
            }
        });
//        frame.add(mainPanel);
        mainPanel.add(panel, BorderLayout.SOUTH);
        frame.getContentPane().add(mainPanel);
        frame.setPreferredSize(new Dimension(350,200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }
}