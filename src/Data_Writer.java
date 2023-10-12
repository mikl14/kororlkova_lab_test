import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;

public class Data_Writer
{
   void Create_txt(String path, Map<Integer,Telemetry_data> datas)
   {
       try
       {
           BufferedWriter writer = new BufferedWriter(new FileWriter(path));
           for (Map.Entry<Integer,Telemetry_data> entry : datas.entrySet())
           {
              //writer.write(dat.GetParamName() + " " + dat.GetParamNumber());
               writer.write(entry.getValue().ToString());
               writer.newLine();
           }
           writer.write(datas.get(3202).ToString());
       }
       catch (Exception e)
       {

       }
   }

}