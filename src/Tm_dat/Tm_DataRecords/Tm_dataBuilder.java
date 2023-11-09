package Tm_dat.Tm_DataRecords;

public class Tm_dataBuilder {

    public static DataRecord createDataRecord(byte[] _paramNum, byte[] _time, byte _razmernost, byte _attribute_type, byte[] _data) {
        int tb = _attribute_type & 0x0F;
        switch (tb) {
            case 0:
                return new Tm_Long(_paramNum, _time, _razmernost, _attribute_type, _data);
            case 1:
                return new Tm_Double(_paramNum, _time, _razmernost, _attribute_type, _data);
            case 2:
                return new Tm_Code(_paramNum, _time, _razmernost, _attribute_type, _data);
            case 3:
                return new Tm_Point(_paramNum, _time, _razmernost, _attribute_type, _data);
            default:
                System.out.println("Record TYPE ERROR " + tb);
                return null;
        }
    }
}
