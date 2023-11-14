package Statements;

import DataStructures.MyIStack;
import States.PrgState;
public class CompStmt implements IStmt {
    IStmt first, second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return "(" +
                first.toString() + ";" + second.toString() + ")";
    }

    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(second);
        stk.push(first);
        return state;
    }

    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), second.deepCopy());
    }
}
