package Tm_dat.Tm_messages;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;

public class Tm_message_time extends Tm_message
{
    String date;
    long res;
    public Tm_message_time(Tm_message mes) {
        super(mes);
    }

    public void Parce_time(byte[] message_title)
    {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[]{message_title[0], message_title[1], message_title[2], message_title[3]}).order(ByteOrder.LITTLE_ENDIAN);
        int unixtime = buffer.getInt();
        res = unixtime * 1000L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
        date = sdf.format(res);
    }

    public void print()
    {
        return;
      //  System.out.println(param_number + " " +unixTimeToString(time) + " " + type_message + " " + type_data + " date: " + date);
    }

}
