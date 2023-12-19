package Statements;

import Exceptions.MyException;
import Expressions.Exp;
import States.PrgState;
import Types.StringType;
import Values.StringValue;
import Values.Value;

public class CloseRFile implements IStmt{
    Exp exp;

    public CloseRFile(Exp e){
        exp = e;
    }

    public String toString(){
        return "closeRFile(" + exp.toString() + ")";
    }

    public IStmt deepCopy(){
        return new CloseRFile(exp.deepcopy());
    }

    public PrgState execute(PrgState state) throws MyException {
        Value val;
        try{
            val=exp.eval(state.getSymTable(),state.getHeap());
        }
        catch (Exception e){
            throw new RuntimeException("Expression not evaluated!");
        }
        if(val.getType().equals(new StringType())){
            if(state.getFileTable().lookup((StringValue) val)){
                state.getFileTable().remove((StringValue) val);
            }
            else throw new MyException("File not opened!");
        }
        else throw new MyException("Expression not evaluated to string!");
        return null;
    }
}
