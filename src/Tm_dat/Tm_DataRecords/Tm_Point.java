package Tm_dat.Tm_DataRecords;

public class Tm_Point extends DataRecord
{
    public Tm_Point(byte[] _paramNum, byte[] _time, byte _razmernost, byte _attribute_type, byte[] _data) {
        super(_paramNum, _time, _razmernost, _attribute_type, _data);
    }

    public String ToString()
    {
        return "Номер: "+param_number+" Имя: " + paramName + " point "+" Время: "+ getTimeString();
    }
    public void print()
    {
        System.out.println("Номер: "+param_number+" Имя: " + paramName + " point "+" Время: "+ getTimeString());
    }

    public String GetType(){return "Point";}
}
