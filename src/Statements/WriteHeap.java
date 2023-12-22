package Statements;

import DataStructures.MyIDictionary;
import Exceptions.MyException;
import Expressions.Exp;
import States.PrgState;
import Types.RefType;
import Types.Type;
import Values.RefValue;
import Values.Value;

public class WriteHeap implements IStmt{
    private String varName;
    private Exp exp;

    public WriteHeap(String varName, Exp exp){
        this.varName = varName;
        this.exp = exp;
    }

    public String toString(){
        return "wH(" + varName + "," + exp.toString() + ")";
    }

    public PrgState execute(PrgState state){
        if(state.getSymTable().lookup(varName))
        {
            Value val=state.getSymTable().get(varName);
            if(val instanceof RefValue){
                if(state.getHeap().isDefined(((RefValue) val).getAddress()))
                {
                    Value val2;
                    try{
                        val2 = exp.eval(state.getSymTable(),state.getHeap());
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.toString());
                        return null;
                    }
                    if(((RefValue) val).getLocationType().equals(val2.getType()))
                    {
                        state.getHeap().update(((RefValue) val).getAddress(),val2);
                    }
                    else
                    {
                        System.out.println("Types not equal!");
                    }
                }
                else
                {
                    System.out.println("Address not allocated!");
                }

            }
            else
            {
                System.out.println("Type not RefType!");
            }
        }
        else
        {
            System.out.println("Variable not declared!");
        }

        return null;
    }

    public IStmt deepCopy(){
        return new WriteHeap(varName, exp.deepcopy());
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ = exp.typecheck(typeEnv);
        Type typ2 = typeEnv.get(varName);
        if(typ2 instanceof RefType)
        {
            if(typ.equals(((RefType) typ2).getInner()))
                return typeEnv;
            else
                throw new MyException("WriteHeap: types not equal!");
        }
        else
            throw new MyException("WriteHeap: variable not RefType!");
    }
}
