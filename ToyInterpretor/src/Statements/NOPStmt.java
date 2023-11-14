package Statements;

import States.PrgState;
public class NOPStmt implements IStmt{
    public String toString() {
        return "NOP";
    }

    public PrgState execute(PrgState state) {
        return state;
    }

    public IStmt deepCopy() {
        return new NOPStmt();
    }
}
