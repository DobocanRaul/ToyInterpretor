package Expressions;

import DataStructures.MyHeap;
import Types.Type;
import Values.Value;
import Exceptions.MyException;
import DataStructures.MyIDictionary;
public class ValueExp implements Exp{
    Value val;

    public ValueExp(Value val)
    {
        this.val = val;
    }
    public Value eval(MyIDictionary<String,Value> tbl, MyHeap hp) throws MyException
    {
        return val;
    }
    public String toString(){
        return val.toString();
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return val.getType();
    }
    public Exp deepcopy()
    {
        return new ValueExp(val);
    }
}
