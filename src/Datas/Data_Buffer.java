package Datas;

import Tm_dat.TM_base;

import java.util.*;

public class Data_Buffer {
    protected static Map<Integer,Telemetry_data> XML_datas = new HashMap<>();
    protected static Map<Integer,String> ION_datas = new HashMap<>();
    protected static Map<Integer, List<TM_base>> Record_datas = new HashMap<>();

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

    public  Map<Integer,List<TM_base>> GetRecords(){return Record_datas;}

    public String getRazm(int key)
    {
        return ION_datas.get(key);
    }

    public String getParamName(int key)
    {
        return XML_datas.get(key).Param_name;
    }

    public static String getText(int num){

        return XML_datas.get(num).GetTextes();
    }

    public void putRecord(int key,TM_base data)
    {
        if(Record_datas.containsKey(key))
        {
            Record_datas.get(key).add(data);

        }
        else
        {
            List<TM_base> list = new ArrayList<TM_base>();
            list.add(data);
            Record_datas.put(key,list);
        }

    }
    public void print_XML()
    {

    }

    public int findXMlbyName(String name)
    {
        for(Map.Entry<Integer,Telemetry_data> entry : XML_datas.entrySet())
        {
            if(entry.getValue().Param_name.equals(name))
            {
                return entry.getValue().Param_number;
            }

        }
     return -1;
    }


    public List<String> FindByList(List<Integer> list,List<String> checklist)
    {
        List<String> list1 = new ArrayList<>();

        for (Integer key: list)
        {
            if (Record_datas.containsKey(key))
            {
                List<TM_base> values = Record_datas.get(key);

                System.out.println(values.size());
                for(TM_base base: values)
                {
                    for(String type:checklist)
                    {
                        if(type.equals(base.GetType()))
                        {
                            list1.add(base.ToString());
                        }
                    }


                }
            }

         /*   for(Map.Entry<Integer,TM_base> entry : Record_datas.entrySet())
            {
                for(String type:checklist) {
                    if (str.equals(entry.getValue().getParamName()) && type.equals(entry.getValue().GetType())) {

                        List<String> values = Record_datas.get();
                        list1.add(entry.getValue().ToString());

                        //System.out.println(entry.getValue().ToString());
                    }
                }
            }*/
        }
        return list1;

    }

    public static List<Telemetry_data> mapToList(Map<Integer, Telemetry_data> map) {
        List<Telemetry_data> list = new ArrayList<>();

        for(Map.Entry<Integer,Telemetry_data> entry : XML_datas.entrySet())
        {
            if(Record_datas.containsKey(entry.getValue().Param_number))
            {
                list.add(entry.getValue());
            }
        }
        Collections.sort(list, new Comparator<Telemetry_data>() {
            @Override
            public int compare(Telemetry_data o1, Telemetry_data o2) {
                return o1.Param_name.compareTo(o2.Param_name);
            }
        });
        return list;

    }

}
