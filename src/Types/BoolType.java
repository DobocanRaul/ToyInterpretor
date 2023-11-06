package Types;

import Values.*;
public class BoolType implements Type{
    public boolean equals(Object other) {
        return other instanceof BoolType;
    }

    public Value defaultValue() {
        return new BoolValue(false);
    }
    public String toString() {
        return "bool ";
    }
}
