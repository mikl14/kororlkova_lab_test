package Tm_dat.Tm_DataRecords;

import Datas.Data_Buffer;
import Tm_dat.TM_base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DataRecord extends TM_base
{
    Data_Buffer databuf = new Data_Buffer();

    String razmernost;

    byte[] data;

    public DataRecord(byte[] _paramNum, byte[] _time, byte _size,byte _atrib_type,byte[] _data) {
        super(_paramNum, _time, false);
        short razmNum = (short) _size;
        if(razmNum == 18){
            razmernost = "TEXT";
        }else{
            razmernost = databuf.getRazm(razmNum);
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
