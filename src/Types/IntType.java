package Types;

import Values.*;
public class IntType implements Type{
    public boolean equals(Object other) {
        return (other instanceof IntType);
    }

    public Value defaultValue() {
        return new IntValue(0);
    }

    public String toString() {
        return "int ";
    }
}
