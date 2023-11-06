package Expressions;

import DataStructures.MyIDictionary;
import Exceptions.MyException;
import Values.Value;

public class VarExp implements Exp{
    String id;

    public VarExp(String id){
        this.id = id;
    }

    public String toString(){
        return id;
    }

    public Value eval(MyIDictionary<String, Value> tbl) throws MyException{
        return tbl.get(id);
    }

    public Exp deepcopy(){
        return new VarExp(id);
    }
}
