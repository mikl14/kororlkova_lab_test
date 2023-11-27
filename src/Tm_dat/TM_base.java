package Tm_dat;

import Datas.ReadXML;

import java.nio.ByteBuffer;

public abstract class TM_base {

    protected final static int SEC = 1000;
    protected final static int MIN = SEC*60;
    protected final static int HOUR = MIN*60;
    public short param_number;
    public int param_time;
    public byte size;

    protected String paramName;

    public boolean isTech;

    public TM_base(byte[] _paramNum, byte[] _time,Boolean _isTech)
    {
        param_number  = ByteBuffer.wrap(_paramNum).getShort();
        param_time = ByteBuffer.wrap(_time).getInt();
        if(param_number > -1) {
            paramName = ReadXML.getParamName(param_number);
        }
        isTech = _isTech;
    }

    public TM_base(TM_base mes)
    {
        param_number = mes.param_number;
        param_time = mes.param_time;
        size = mes.size;
        isTech = mes.isTech;
    }

    protected String getTimeString(){
        if(param_time < 0) return "NEGATIVE TIME";
        int buff = param_time, h, m, s;

        h = buff/HOUR;
        buff -= HOUR*h;

        m = buff/MIN;
        buff -= MIN*m;

        s = buff/SEC;
        buff -= SEC*s;

        String result = String.format("%02d:%02d:%02d", h,m,s);

        return result;
    }


    public abstract String ToString();
    public abstract void print();

    public abstract String GetType();
}
