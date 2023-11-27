package Tm_dat.Tm_DataRecords;

import Datas.Data_Buffer;
import Tm_dat.TM_base;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

public class Tm_Long extends DataRecord {

    public Tm_Long(byte[] _paramNum, byte[] _time, byte _size, byte _atrib_type, byte[] _data) {
        super(_paramNum, _time, _size, _atrib_type, _data);
    }

    private int getData(){
        byte[] buff = Arrays.copyOfRange(data, 4, 8);
        return ByteBuffer.wrap(buff).getInt();
    }

    public String ToString()
    {
        return "Номер: "+param_number+" Имя: " + paramName + " Long Данные: "+getData()+" Время: "+ getTimeString();
    }

    public void print()
    {
        System.out.println("Номер: "+param_number+" Имя: " + paramName + " Long Данные: "+getData()+" Время: "+ getTimeString());
    }



    public String GetType(){return "Long";}
}
