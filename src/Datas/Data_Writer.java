package Datas;

import Tm_dat.TM_base;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;

public class Data_Writer
{
   public void Create_XML_txt(String path, Map<Integer, Telemetry_data> datas)
   {
       try
       {
           BufferedWriter writer = new BufferedWriter(new FileWriter(path));

           for (Map.Entry<Integer,Telemetry_data> entry : datas.entrySet())
           {
               writer.write(entry.getValue().ToString());
               writer.newLine();
           }

       }
       catch (Exception e)
       {
            System.out.println(e);
       }
   }


    public void Create_ION_txt(String path, Map<Integer, String> datas)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));


            for (Map.Entry<Integer,String> entry : datas.entrySet())
            {
                writer.write(entry.getKey().toString()+" : " + entry.getValue());
                writer.newLine();
            }
            writer.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void Create_Records_txt(String path, Map<Integer, TM_base> datas)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));


            for (Map.Entry<Integer,TM_base> entry : datas.entrySet())
            {
                writer.write(entry.getKey().toString()+" : " + entry.getValue().ToString());
                writer.newLine();
            }
            writer.close();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }




}