package Values;

import Types.IntType;
import Types.Type;

public class IntValue implements Value{
    int value;

    public IntValue(int val) {
        this.value =val;
    }

    public void defaultValue() {
        value = 0;
    }

    public int getVal() {
        return value;
    }

    public String toString() {
        return Integer.toString(value);
    }

    public Type getType() {
        return new IntType();
    }



}
