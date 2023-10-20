package Tm_dat;

public class TM_base {
    public int param_number,param_time,size,atrib;

    public void setTM(int number,int time, byte size,byte atrib)
    {
        param_number = number;
        param_time = time;
        this.size = size;
        this.atrib = atrib;
    }

    public void setParam_number(int param_number) {
        this.param_number = param_number;
    }

    public void setAtrib(int atrib) {
        this.atrib = atrib;
    }

    public void setParam_time(int param_time) {
        this.param_time = param_time;
    }

    public void setSize(int size) {
        this.size = size;
        if(size > 255)
        {
            throw new RuntimeException("ERROR SIZE");
        }
    }

    public void print()
    {
        String atrib_name = "";
        switch (atrib)
        {
            case 0:
                atrib_name = "Long";
                break;
            case 1:
                atrib_name = "Double";
                break;
            case 2:
                atrib_name = "Code";
                break;
            case 3:
                atrib_name = "Point";
                break;
        }
        if(atrib_name == "")
        {
            System.out.println("number: "+param_number + " time: " + param_time + " size "+size + " atrib: " + atrib);
            return;
        }
        System.out.println("number: "+param_number + " time: " + param_time + " size "+size + " atrib: " + atrib_name);
    }
}
