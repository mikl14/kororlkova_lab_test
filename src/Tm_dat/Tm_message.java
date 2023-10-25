package Tm_dat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tm_message
{
    short param_number;
    public int time;
    public byte type_message,type_data;

    public Tm_message(short number)
    {
        if((number & 0xffff) >= 0xffff)
        {
           param_number = number;
           return;
        }
        throw new RuntimeException("Not message");
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



    public void print()
    {
        System.out.println(param_number + " " +unixTimeToString(time) + " " + type_message + " " + type_data);
    }

    private static String unixTimeToString(long unixTime) {
        Date date = new Date(unixTime * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

}
