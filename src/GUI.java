import Config.Config;
import Datas.Data_Buffer;
import Datas.Data_Reader;
import Datas.Data_Writer;
import Datas.Telemetry_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUI
{
    Config config = new Config();
    Data_Buffer datbuf = new Data_Buffer();
    Data_Writer datawriter = new Data_Writer(config);

    Box box;
    JScrollPane jScrollPane;
    public GUI()
    {
        CreateMainWindows();

        //CreateParamWindows();
    }

    void CreateMainWindows()
    {
        JFrame frame = new JFrame("CUP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton FindButton = new JButton("Find");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(FindButton);

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

       box = CreateCheckboxList();

       // textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(box);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(scrollPane);

        int count = 0;
        FindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.setText("KNP_pressed");
                CreateParamWindow(datbuf.FindByList(getSelected()));

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

    private Box CreateCheckboxList()
    {
        Box box = Box.createVerticalBox();

        for (Map.Entry<Integer, Telemetry_data> entry : datbuf.GetXML().entrySet())
        {
            JCheckBox checkBox1 = new JCheckBox(entry.getValue().Param_name);
            box.add(checkBox1);
        }
        return box;
        // Добавление контейнера с чекбоксами в окно
    }

    private List<String> getSelected(){
        List<String> result = new ArrayList<>();

        int size = box.getComponentCount();
        for(int i = 0; i < size; ++i){
            JCheckBox checkBox = (JCheckBox) box.getComponent(i);
            if(checkBox.isSelected()){
                result.add(checkBox.getText());
            }
        }
        return result;
    }

    public void CreateParamWindow(List<String> list)
    {
        // Создание контейнера со строками для прокрутки
        JTextArea textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);

        for(String str:list)
        {
            textArea.append(str+'\n');
        }


        // Создание кнопок "OK" и "Cancel"
        JButton SaveButton = new JButton("Save ");
        //JButton cancelButton = new JButton("Cancel");




        // Создание панели для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(SaveButton);
       // buttonPanel.add(cancelButton);

        // Создание окна и добавление контейнера со строками и панели с кнопками
        JFrame frame = new JFrame("Scrollable Container");
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(300, 200);
        frame.setVisible(true);

        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CreateParamWindow(datbuf.FindByList(getSelected()));
                datawriter.Create_List_txt(textArea.getText());
            }
        });
    }



}
