package Statements;

import DataStructures.MyIDictionary;
import Exceptions.*;
import States.PrgState;
import Types.Type;
public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    public String toString();

    public IStmt deepCopy();
    MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
