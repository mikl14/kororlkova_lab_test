package Datas;

import java.util.HashMap;
import java.util.Map;

public class Data_Buffer {
    protected static Map<Integer,Telemetry_data> XML_datas = new HashMap<>();

    protected static Map<Integer,String> ION_datas = new HashMap<>();

    public void XML_put(int key,Telemetry_data data)
    {
        XML_datas.put(key,data);
    }

    public void ION_put(int key,String data)
    {
        ION_datas.put(key,data);
    }

    public Map<Integer,Telemetry_data> GetXML()
    {
        return XML_datas;
    }

    public Map<Integer,String> GetION()
    {
        return ION_datas;
    }
    
    public void print_XML()
    {

    }


}
