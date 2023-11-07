package Tm_dat.Tm_messages;
import Tm_dat.TM_base;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tm_message extends TM_base
{
    public byte type_message,type_data;

    public Tm_message(byte[] _paramNum, byte[] _time, byte size)
    {
           super(_paramNum,_time,size,true);
    }

    public Tm_message(Tm_message mes) {
        super(mes);
    }

    public void SetMessageType(byte mestype)
    {
        if(mestype >= 0 && mestype <= 6)
        {
            type_message = mestype;
            return;
        }
        type_message = -1;

    }

    public void SetTypeData(byte mestype)
    {
        if(mestype >= 0 && mestype <= 4)
        {
            type_data = mestype;
            return;
        }
        type_data = -1;

    }


    public String ToString() {
        return param_number + " " +getTimeString() + " " + type_message + " " + type_data;
    }

    public void print()
    {
        System.out.println(param_number + " " +getTimeString() + " " + type_message + " " + type_data);
    }

}
