package Expressions;

import DataStructures.MyHeap;
import Types.IntType;
import Types.Type;
import Values.IntValue;
import Values.Value;
import Exceptions.MyException;
import DataStructures.MyIDictionary;
public class ArithExp implements Exp{
    Exp e1;
    Exp e2;
    Character op; // 1 - +, 2 - -, 3 - *, 4 - /

    public String toString(){
        String s = "";
        if(op.equals('+')) s = e1.toString() + " + " + e2.toString();
        if(op.equals('-')) s = e1.toString() + " - " + e2.toString();
        if(op.equals('*')) s = e1.toString() + " * " + e2.toString();
        if(op.equals('/')) s = e1.toString() + " / " + e2.toString();
        return s;
    }
    public ArithExp( Character op, Exp e1, Exp e2){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyHeap hp) throws MyException
    {
        Value v1,v2;
        v1= e1.eval(tbl,hp);
        if(v1.getType().equals(new IntType()))
        {
            v2 = e2.eval(tbl,hp);
            if(v2.getType().equals(new IntType()))
            {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op.equals('+')) return new IntValue(n1+n2);
                if(op.equals('-')) return new IntValue(n1-n2);
                if(op.equals('*')) return new IntValue(n1*n2);
                if(op.equals('/'))
                {
                    if(n2==0) throw new MyException("division by zero");
                    else return new IntValue(n1/n2);
                }
            }
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");

        return v1;
    }

    public Exp deepcopy()
    {
        return new ArithExp(op,e1.deepcopy(),e2.deepcopy());
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new IntType()))
        {
            if (typ2.equals(new IntType()))
            {
                return new IntType();
            }
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");
    }
}
