package Values;

import Types.BoolType;
import Types.Type;

public class BoolValue implements Value{
    boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public void defaultValue() {
        value = false;
    }

    public boolean getVal() {
        return value;
    }

    public String toString() {
        return Boolean.toString(value);
    }

    public Type getType() {
        return new BoolType();
    }
}
