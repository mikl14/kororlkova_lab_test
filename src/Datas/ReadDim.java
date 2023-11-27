package Datas;

import Config.Config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadDim {

    public static Config config;

    protected static Map<Integer,String> ION_datas = new HashMap<>();

    public ReadDim(Config config)
    {
        this.config = config;
    }

    public static void ION_put(int key, String data)
    {
        ION_datas.put(key,data);
    }

    public Map<Integer,String> GetION()
    {
        return ION_datas;
    }

    public static String getRazm(int key)
    {
        return ION_datas.get(key);
    }

    public static void Read_Demention()
    {
        String path = config.Dim_IN;
        File file = new File(path);
        int index = 1;
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                if(line.length() > 0)
                {
                    ION_put(index,line);
                }
                index++;
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
