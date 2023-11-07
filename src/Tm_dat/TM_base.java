package Tm_dat;

import java.nio.ByteBuffer;

public abstract class TM_base {

    protected final static int SEC = 1000;
    protected final static int MIN = SEC*60;
    protected final static int HOUR = MIN*60;
    public short param_number;
    public int param_time;
    public byte size;

    public boolean isTech;

    public TM_base(byte[] _paramNum, byte[] _time,Boolean _isTech)
    {
        param_number  = ByteBuffer.wrap(_paramNum).getShort();
        param_time = ByteBuffer.wrap(_time).getInt();
        isTech = _isTech;
    }

    public TM_base(TM_base mes)
    {
        param_number = mes.param_number;
        param_time = mes.param_time;
        size = mes.size;
        isTech = mes.isTech;
    }



    public void setParam_number(short param_number) {
        this.param_number = param_number;
    }


    public void setParam_time(int param_time) {
        this.param_time = param_time;
    }

    public void setSize(byte size) {
        this.size = size;
        if(size > 255)
        {
            throw new RuntimeException("ERROR SIZE");
        }
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
}
