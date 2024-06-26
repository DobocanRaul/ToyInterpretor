package Statements;


import DataStructures.MyDictionary;
import DataStructures.MyIDictionary;
import Exceptions.MyException;
import States.PrgState;
import Types.Type;

public class VarDeclStmt implements IStmt{
    String name;
    Type type;

    public VarDeclStmt(String name, Type type){
        this.name = name;
        this.type = type;
    }

    public String toString(){
        return type.toString() + " " + name;
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary symTable = state.getSymTable();
        if(symTable.lookup(name)==true)
            throw new MyException("Variable already declared!");
        else {
            symTable.add(name, type.defaultValue());
        }
        return null;
    }

    public IStmt deepCopy(){
        return new VarDeclStmt(name, type);
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        typeEnv.add(name, type);
        return typeEnv;
    }
}
