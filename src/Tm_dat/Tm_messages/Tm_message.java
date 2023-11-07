package Tm_dat.Tm_messages;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tm_message
{
    short param_number;
    public int time;
    public byte type_message,type_data;

    public Tm_message(short number)
    {

           param_number = number;
    }

    public Tm_message(Tm_message mes) {
        param_number = mes.param_number;
        time = mes.time;
        type_message = mes.type_message;
        type_data = mes.type_data;
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

    static String unixTimeToString(long unixTime) {
        Date date = new Date(unixTime * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }
    static String unixDateToString(long unixTime) {
        long date = (new Date().getTime()/((1000*60*60*24) - 3653));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
        return sdf.format(date);
    }

}
