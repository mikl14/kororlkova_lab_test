package Datas;

import Config.Config;
import Tm_dat.TM_base;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class Data_Writer
{

    Config config;

    public Data_Writer(Config config)
    {
        this.config = config;
    }


    public void Create_List_txt(String text)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(config.Tmi_OUT));

                writer.write(text);
                writer.newLine();

            writer.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }




}