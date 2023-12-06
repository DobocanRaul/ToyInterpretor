package Types;

import Values.StringValue;
import Values.Value;

public class StringType implements Type{
    public boolean equals(Object another){
        if(another instanceof StringType)
            return true;
        else
            return false;
    }

    public String toString(){
        return "string";
    }

    public Value defaultValue(){
        return new StringValue("-");
    }

    public Type deepcopy(){
        return new StringType();
    }
}
