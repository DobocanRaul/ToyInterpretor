package Statements;

import DataStructures.MyIDictionary;
import Exceptions.MyException;
import Expressions.Exp;
import States.PrgState;
import Types.StringType;
import Types.Type;
import Values.StringValue;
import Values.Value;

import java.io.BufferedReader;
import java.io.FileReader;

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
        id = exp.eval(state.getSymTable(),state.getHeap());
        if(id.getType().equals(new StringType())){
            if(state.getFileTable().lookup((StringValue) id)){
                throw new MyException("File already opened!");
            }
            else{
                try{
                state.getFileTable().add((StringValue) id,new BufferedReader(new FileReader((id.toString()))));
                }
                catch (Exception e){
                    throw new MyException("File not found!");
                }
            }
        }
        else{
            throw new MyException("Expression is not a string!");
        }

        return null;
    }

    public IStmt deepCopy(){
        return new openRFile(exp.deepcopy());
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ = exp.typecheck(typeEnv);
        if(typ.equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("OpenRFile: exp is not a string!");
    }
}
