package Tm_dat.Tm_DataRecords;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Tm_Code extends DataRecord{
    public Tm_Code(byte[] _paramNum, byte[] _time, byte _size, byte _atrib_type, byte[] _data) {
        super(_paramNum, _time, _size, _atrib_type, _data);
    }

    private int getData(){
        byte[] buff = Arrays.copyOfRange(data, 4, 8);
        return ByteBuffer.wrap(buff).getInt();
    }
    private String Decode_Data()
    {
        return String.format("%-21s", getData());

    }

    public String ToString()
    {
        return "Номер: "+param_number+" Имя: " + paramName + " Code Данные: "+Decode_Data()+" Время: "+ getTimeString();
    }
    public void print() {
        System.out.println(this.ToString());
    }

    public String GetType(){return "Code";}
}
