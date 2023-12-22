package Statements;

import DataStructures.MyIDictionary;
import Exceptions.MyException;
import States.PrgState;
import Types.Type;

public class NOPStmt implements IStmt{
    public String toString() {
        return "NOP";
    }

    public PrgState execute(PrgState state) {
        return null;
    }

    public IStmt deepCopy() {
        return new NOPStmt();
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
