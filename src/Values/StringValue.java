package Values;

import Types.StringType;
import Types.Type;

public class StringValue implements Value{
    String val;

    public StringValue(String val){
        this.val = val;
    }

    public String getVal(){
        return val;
    }

    public String toString(){
        return val;
    }
    public void defaultValue(){
        val = "";
    }
    public boolean equals(Object another){
        if(another instanceof StringValue)
            return true;
        else
            return false;
    }

    public Type getType(){
        return new StringType();
    }

    public Value deepcopy(){
        return new StringValue(val);
    }
}
