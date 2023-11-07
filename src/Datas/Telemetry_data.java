package Datas;

import java.util.*;

public class Telemetry_data {
   public String Param_name;

    public  int Param_number;
    public String[] Textes_datas ;

    public String Description;


    Telemetry_data(String name, int number)
    {
        Param_name = name;
        Param_number = number;
    }

    String GetParamName()
    {
        return this.Param_name;
    }

    int GetParamNumber()
    {
        return this.Param_number;
    }

    String GetDescription()
    {
        return Description;
    }

    String GetTextes()
    {
        String str_buf = "";
        for (String str:this.Textes_datas)
        {
            str_buf += str + ",";
        }
        return str_buf;
    }


    public void SetTextes(String[] newList)
    {
        Textes_datas = newList;

    }


    void SetDescription(String description)
    {
        Description = description;
    }

    void print()
    {
        String str_buf = "";
        for (String str:this.Textes_datas)
        {
            str_buf += str + ",";
        }
        //str_buf += str_buf.substring(0,str_buf.length()-1);
        System.out.println(Param_name + " " + Param_number +" "+Description);

        System.out.println( str_buf );
    }

    String ToString()
    {
        String str_buf = "";
        for (String str:this.Textes_datas)
        {
            str_buf += str + ",";
        }
        //str_buf += str_buf.substring(0,str_buf.length()-1);
        return (Param_name + " " + Param_number +" "+Description +'\n' + str_buf);

        //System.out.println( str_buf );
    }
}
