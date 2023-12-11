package Datas;

import Config.Config;
import Tm_dat.TM_base;
import Tm_dat.Tm_DataRecords.Tm_dataBuilder;
import Tm_dat.Tm_messages.Tm_message;

import java.io.*;
import java.util.*;

public class ReadTMI
{
    public static Config config;

    protected static Map<Integer, List<TM_base>> Record_datas = new HashMap<>();

    public static Map<Integer,List<TM_base>> GetRecords(){return Record_datas;}

    public ReadTMI(Config config)
    {
        this.config = config;
    }


    public static void ParceTm()
    {

        File file = new File(config.Tmi_IN);

        try(FileInputStream fileInputStream = new FileInputStream(file)) {

            byte[] recordInBytes = new byte[16];
            while (fileInputStream.read(recordInBytes) != -1) {
                byte[] paramNum = Arrays.copyOf(recordInBytes, 2);
                byte[] bTime = Arrays.copyOfRange(recordInBytes, 2, 6);
                TM_base record;
                if ((paramNum[0] & 0xFF) == 0xFF && (paramNum[1] & 0xFF) == 0xFF) { // Служебная

                    byte bMes = recordInBytes[6];
                    byte bZnach = recordInBytes[7];
                    byte[] bData = Arrays.copyOfRange(recordInBytes, 8, 16);
                    if(bMes == 1){
                        byte[] extraData = new byte[16];
                        fileInputStream.read(extraData);
                        byte[] buff = bData;
                        bData = new byte[24];
                        System.arraycopy(buff, 0, bData, 0, 8);
                        System.arraycopy(extraData, 0, bData, 8, 16);
                    }
                    record = new Tm_message(paramNum, bTime, bMes, bZnach, bData);
                    //record.print();
                }
                else{                                           // Обычная
                    byte dim = recordInBytes[6];
                    byte atr_type = recordInBytes[7];
                    byte[] bData = Arrays.copyOfRange(recordInBytes, 8, 16);
                    if((atr_type&0x0F) == 3){
                        int extraDataSize = ((Byte.toUnsignedInt(recordInBytes[10]) << 8) + Byte.toUnsignedInt(recordInBytes[11])) - 4;
                        byte[] extraData = new byte[extraDataSize];
                        fileInputStream.read(extraData);

                        byte[] buff = bData;
                        bData = new byte[extraDataSize + 8];
                        System.arraycopy(buff, 0, bData, 0, 8);
                        System.arraycopy(extraData, 0, bData, 8, extraDataSize);
                    }
                    record = Tm_dataBuilder.createDataRecord(paramNum, bTime, dim, atr_type, bData);

                }
                putRecord(record.param_number,record);
            }
        }
       catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void putRecord(int key,TM_base data)
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
    public static List<String> FindByList(List<Integer> list,List<String> checklist)
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

        }
        return list1;

    }

}
