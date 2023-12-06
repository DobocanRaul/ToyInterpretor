package Statements;

import Expressions.Exp;
import States.PrgState;
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
                        return state;
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
                return state;
            }
        }
        else
        {
            System.out.println("Variable not declared!");
        }

        return state;
    }

    public IStmt deepCopy(){
        return new WriteHeap(varName, exp.deepcopy());
    }
}
