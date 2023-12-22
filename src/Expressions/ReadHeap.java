package Expressions;

import DataStructures.MyHeap;
import DataStructures.MyIDictionary;
import Exceptions.MyException;
import Types.RefType;
import Types.Type;
import Values.RefValue;
import Values.Value;

public class ReadHeap implements Exp{

    Exp exp;
    public ReadHeap(Exp exp){
        this.exp = exp;
    }

    public String toString(){
        return "rH(" + exp.toString() + ")";
    }

    public Exp deepcopy(){
        return new ReadHeap(exp.deepcopy());
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyHeap hp) throws MyException {
        Value v = exp.eval(tbl, hp);
        if(v instanceof RefValue) {
            Integer address = ((RefValue) v).getAddress();
            if(hp.isDefined(address))
                return hp.get(address);
            else
                throw new MyException("Address is not defined in the heap");
        }
        else
            throw new MyException("Expression is not a RefValue");
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ = exp.typecheck(typeEnv);
        if(typ instanceof RefType){
            RefType reft = (RefType)typ;
            return reft.getInner();
        }
        else
            throw new MyException("the rH argument is not a Ref Type");
    }
}
