package Tm_dat.Tm_DataRecords;

public class Tm_dataBuilder {

    public static Tm_Point createDataRecord(byte[] _paramNum, byte[] _time, byte _razmernost, byte _attribute_type, byte[] _data) {
        int tb = _attribute_type & 0x0F;
        switch (tb) {
            case 0:
                System.out.println("ss 0");
                //return new LongRecord(_paramNum, _time, _razmernost, _attribute_type, _data);
            case 1:
                System.out.println("ss 1");
               // return new DoubleRecord(_paramNum, _time, _razmernost, _attribute_type, _data);
            case 2:
                System.out.println("ss 2");
               // return new CodeRecord(_paramNum, _time, _razmernost, _attribute_type, _data);
            case 3:
                return new Tm_Point(_paramNum, _time, _razmernost, _attribute_type, _data);
            default:
                System.out.println("Record TYPE ERROR " + tb);
                return null;
        }
    }
}
