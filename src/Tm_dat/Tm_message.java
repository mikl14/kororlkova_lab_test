package Tm_dat;

public class Tm_message
{
    short param_number;
    int time;
    byte type_message,type_data;

    public Tm_message(short number)
    {
        if((number & 0xffff) >= 0xffff)
        {
           param_number = number;
           return;
        }
        throw new RuntimeException("Not message");
    }

    public void print()
    {
        System.out.println(param_number);
    }

}
