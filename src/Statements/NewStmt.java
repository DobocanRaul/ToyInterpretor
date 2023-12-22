package Statements;

import DataStructures.MyDictionary;
import DataStructures.MyIDictionary;
import Exceptions.MyException;
import Expressions.Exp;
import States.PrgState;
import Types.RefType;
import Types.Type;
import Values.RefValue;
import Values.Value;

public class NewStmt implements IStmt{
    String varName;
    Exp exp;

    public NewStmt(String varName, Exp exp){
        this.varName = varName;
        this.exp = exp;
    }

    public String toString(){
        return "new(" + varName + "," + exp.toString() + ")";
    }

    public PrgState execute(PrgState state){
        if(state.getSymTable().lookup(varName))
        {
            Value value=state.getSymTable().get(varName);
            if(value.getType() instanceof RefType)
            {
                Value val;
                try{
                    val = exp.eval(state.getSymTable(),state.getHeap());
                }
                catch (Exception e)
                {
                    System.out.println(e.toString());
                    return state;
                }
                if(((RefValue)value).getLocationType().equals(val.getType()))
                {
                    state.getHeap().add(val);
                    state.getSymTable().update(varName, new RefValue(state.getHeap().getAddress(val),val.getType()));
                }
                else
                {
                    System.out.println("Types not equal!");
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
        return new NewStmt(varName, exp);
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typevar = typeEnv.get(varName);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }
}
