package Types;

import Values.RefValue;
import Values.Value;

public class RefType implements Type{
    Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0, inner);
    }

    public Type getInner() {
        return inner;
    }

    public boolean equals(Object other) {
        if(other instanceof RefType) {
            return inner.equals(((RefType)other).getInner());
        }
        return false;
    }

    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }




}
