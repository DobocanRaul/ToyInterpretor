package Values;

import Types.RefType;
import Types.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public Type getLocationType(){return locationType; }

    public Type getType() {
        return new RefType(locationType);
    }

    public String toString() {
        return "(" + address + "," + locationType.toString() + ")";
    }
}
