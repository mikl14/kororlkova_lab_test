import java.util.*;

public class Telemetry_data {
   static public String Param_name,Param_number;
    static public ArrayList<String> Textes_datas = new ArrayList<String>();

    static public String Description;


    Telemetry_data(String name, String number)
    {
        Param_name = name;
        Param_number = number;
    }

    public void AddTextes(ArrayList<String> newList)
    {
        Textes_datas = new ArrayList<String>();
        this.Textes_datas.addAll(newList);
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
}
