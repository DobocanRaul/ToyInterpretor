package Expressions;

import DataStructures.MyHeap;
import DataStructures.MyIDictionary;
import Exceptions.MyException;
import Types.Type;
import Values.Value;

public class VarExp implements Exp{
    String id;

    public VarExp(String id){
        this.id = id;
    }

    public String toString(){
        return id;
    }

    public Value eval(MyIDictionary<String, Value> tbl, MyHeap hp) throws MyException{
        return tbl.get(id);
    }
    public Exp deepcopy(){
        return new VarExp(id);
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return typeEnv.get(id);
    }
}
