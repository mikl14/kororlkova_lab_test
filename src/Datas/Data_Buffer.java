package Datas;

import Tm_dat.TM_base;

import java.util.HashMap;
import java.util.Map;

public class Data_Buffer {
    protected static Map<Integer,Telemetry_data> XML_datas = new HashMap<>();
    protected static Map<Integer,String> ION_datas = new HashMap<>();
    protected static Map<Integer, TM_base> Record_datas = new HashMap<>();

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

    public String getRazm(int key)
    {
        return ION_datas.get(key);
    }

    public String getParamName(int key)
    {
        return XML_datas.get(key).Param_name;
    }

    public void putRecord(TM_base data)
    {
        Record_datas.put((int) data.param_number,data);
    }
    public void print_XML()
    {

    }


}
