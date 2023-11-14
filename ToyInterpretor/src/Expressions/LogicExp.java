package Expressions;

import DataStructures.MyIDictionary;
import Exceptions.MyException;
import Values.Value;
import Types.BoolType;
public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    int op; // 1 - &&, 2 - ||

    public LogicExp(Exp e1, Exp e2, int op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public String toString(){
        if(op == 1)
            return e1.toString() + " && " + e2.toString();
        else
            return e1.toString() + " || " + e2.toString();
    }

    public Value eval(MyIDictionary<String, Value> tbl) throws MyException{
        Value v1,v2;
        v1= e1.eval(tbl);
        if(v1.getType().equals(new BoolType())){
            v2 = e2.eval(tbl);
            if(v2.getType().equals(new BoolType())){
                Values.BoolValue b1 = (Values.BoolValue)v1;
                Values.BoolValue b2 = (Values.BoolValue)v2;
                boolean n1,n2;
                n1 = b1.getVal();
                n2 = b2.getVal();
                if(op==1) return new Values.BoolValue(n1 && n2);
                if(op==2) return new Values.BoolValue(n1 || n2);
            }
            else throw new MyException("second operand is not a boolean");
        }
        else throw new MyException("first operand is not a boolean");

        return v1;
    }

    public Exp deepcopy(){
        return new LogicExp(e1.deepcopy(),e2.deepcopy(),op);
    }
}
