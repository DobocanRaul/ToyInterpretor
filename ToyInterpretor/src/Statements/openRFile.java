package Statements;

import Exceptions.MyException;
import Expressions.Exp;
import States.PrgState;
import Types.StringType;
import Values.StringValue;
import Values.Value;

public class openRFile implements IStmt{
    Exp exp;

    public openRFile(Exp e){
        exp = e;
    }

    public String toString(){
        return "openRFile(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException{
        Value id;
        id = exp.eval(state.getSymTable());
        if(id.getType().equals(new StringType())){
            if(state.getFileTable().lookup(id.toString())){
                throw new MyException("File already opened!");
            }
            else{
                state.getFileTable().add(id.toString(),new StringValue(id.toString()));
            }
        }
        else{
            throw new MyException("Expression is not a string!");
        }

        return state;
    }

    public IStmt deepCopy(){
        return new openRFile(exp.deepcopy());
    }

}
