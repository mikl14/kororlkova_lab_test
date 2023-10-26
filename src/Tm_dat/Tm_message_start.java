package Tm_dat;

public class Tm_message_start extends Tm_message{

    public short size;
    public int message;
    public Tm_message_start(short number) {
        super(number);
    }

    public Tm_message_start(Tm_message mes) {
        super(mes.param_number);
        param_number = mes.param_number;
        time = mes.time;
        type_message = mes.type_message;
        type_data = mes.type_data;


    }

    public void Parse_message(byte[] bytemass)
    {
        size = (short) (bytemass[2] + bytemass[3]);

        message = bytemass[4] + bytemass[5] + bytemass[6] + bytemass[7];
    }

    public void print()
    {
        System.out.println(param_number + " " +unixTimeToString(time) + " " + type_message + " " + type_data + " size: " + size + " mes: " + message);
    }

}
