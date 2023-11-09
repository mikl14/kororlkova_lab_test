package Tm_dat.Tm_DataRecords;

import java.nio.ByteBuffer;

public class Tm_Double extends DataRecord{
    public Tm_Double(byte[] _paramNum, byte[] _time, byte _size, byte _atrib_type, byte[] _data) {
        super(_paramNum, _time, _size, _atrib_type, _data);
    }

    private double getData(){
        return ByteBuffer.wrap(data).getDouble();
    }

    public String ToString()
    {
        return "Номер: "+param_number+" Имя: " + paramName + " Double Данные: "+getData()+" Время: "+ getTimeString();
    }

    public void print()
    {
        System.out.println("Номер: "+param_number+" Имя: " + paramName + " Double Данные: "+getData()+" Время: "+ getTimeString());
    }
}
