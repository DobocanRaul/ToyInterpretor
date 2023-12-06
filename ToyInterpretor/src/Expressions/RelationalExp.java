package Expressions;

import DataStructures.MyHeap;
import DataStructures.MyIDictionary;
import Exceptions.MyException;
import Types.IntType;
import Values.BoolValue;
import Values.IntValue;
import Values.Value;

public class RelationalExp implements Exp{
    Exp e1, e2;
    String op;

    public RelationalExp(Exp e1, Exp e2, String op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    public String toString(){
        return "(" + e1.toString() + op + e2.toString() + ")";
    }

    public Value eval(MyIDictionary<String, Value> tbl, MyHeap hp) throws MyException{
        Value v1, v2;
        v1 = e1.eval(tbl,hp);
        if(v1.getType().equals(new IntType())){
            v2 = e2.eval(tbl,hp);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op.equals("<"))
                    return new BoolValue(n1 < n2);
                if(op.equals("<="))
                    return new BoolValue(n1 <= n2);
                if(op.equals("=="))
                    return new BoolValue(n1 == n2);
                if(op.equals("!="))
                    return new BoolValue(n1 != n2);
                if(op.equals(">"))
                    return new BoolValue(n1 > n2);
                if(op.equals(">="))
                    return new BoolValue(n1 >= n2);
            }
            else
                throw new MyException("second operand is not an integer");
        }
        else
            throw new MyException("first operand is not an integer");
        return new BoolValue(false);
    }

    public Exp deepcopy(){
        return new RelationalExp(e1.deepcopy(),e2.deepcopy(),op);
    }

}
