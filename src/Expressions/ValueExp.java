package Expressions;

import Values.Value;
import Exceptions.MyException;
import DataStructures.MyIDictionary;
public class ValueExp implements Exp{
    Value val;

    public ValueExp(Value val)
    {
        this.val = val;
    }
    public Value eval(MyIDictionary<String,Value> tbl) throws MyException
    {
        return val;
    }

    public String toString(){
        return val.toString();
    }

    public Exp deepcopy()
    {
        return new ValueExp(val);
    }
}
