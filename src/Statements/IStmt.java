package Statements;

import Exceptions.*;
import States.PrgState;
public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    public String toString();

    public IStmt deepCopy();
}
