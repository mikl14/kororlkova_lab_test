package Tm_dat.Tm_DataRecords;

import Tm_dat.TM_base;

public class DataRecord extends TM_base
{

    String razmernost;

    byte[] data;

    public DataRecord(byte[] _paramNum, byte[] _time, byte _size,byte _atrib_type,byte[] _data) {
        super(_paramNum, _time, false);
        short razmNum = (short) _size;
        if(razmNum == 18){
            razmernost = "TEXT";
        }else{
           // razmernost = dimParser.getRazm(razmNum);
        }
        data = _data;
    }

    @Override
    public String ToString() {
        return null;
    }

    @Override
    public void print() {

    }
}
