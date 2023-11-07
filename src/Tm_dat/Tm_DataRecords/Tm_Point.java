package Tm_dat.Tm_DataRecords;

public class Tm_Point extends DataRecord
{
    public Tm_Point(byte[] _paramNum, byte[] _time, byte _razmernost, byte _attribute_type, byte[] _data) {
        super(_paramNum, _time, _razmernost, _attribute_type, _data);
    }
    public String toHeaderString(){
        return String.format("%-8s %-21s %-7s %19s", paramName, "Point", razmernost, "Время");
    }

    public String toDataString(){
        return String.format("%-8s %-21s %27s", " ", "point", getTimeString());
    }

}
