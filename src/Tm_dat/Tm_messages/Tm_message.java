package Tm_dat.Tm_messages;
import Tm_dat.TM_base;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Tm_message extends TM_base
{
    public Type_message type_message;
    public byte[] data;

    public Tm_message(byte[] _paramNum, byte[] _time,  byte _mesType,byte _znachType,  byte[] _mes)
    {
           super(_paramNum,_time,true);
           type_message =getType(_mesType);
           data = _mes;
    }

    enum Type_message{
        EMPTY, START, TIME, END, NEW_MODE, NEW_DATE, ERROR
    }

    public Tm_message(Tm_message mes) {
        super(mes);
    }

    private Type_message getType(byte b){
        switch (b){
            case 1: return Type_message.START;
            case 2: return Type_message.TIME;
            case 3: return Type_message.END;
            case 4: return Type_message.NEW_MODE;
            case 5: return Type_message.NEW_DATE;
            case 6: return Type_message.ERROR;
            default: return Type_message.EMPTY;
        }
    }

    private Object getData(){
        switch (type_message){
            case EMPTY:
                return "";
            case START:
                return "Start-data";
            case TIME:
                byte[] buff = Arrays.copyOfRange(data, 0, 4);
                return ByteBuffer.wrap(buff).getInt();
            case END:
                return "END";
            case ERROR:
            case NEW_DATE:
                case NEW_MODE:
                byte[] buff2 = Arrays.copyOfRange(data, 4, 8);
                return ByteBuffer.wrap(buff2).getInt();
        }
        return "TECH DATA ERROR";
    }



    public String ToString() {
        return param_number + " " +getTimeString() + " " + type_message + " " + getData();
    }

    public void print()
    {
        System.out.println(param_number + " " +getTimeString() + " " + type_message + " " + getData());
    }

}
