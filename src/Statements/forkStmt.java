package Statements;

import DataStructures.MyIStack;
import DataStructures.MyStack;
import States.PrgState;

public class forkStmt implements IStmt {
    IStmt stmt;

    public forkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }

    public PrgState execute(PrgState state) {
        MyStack<IStmt> newStack=new MyStack<>();
        return new PrgState(newStack, state.getSymTable().deepcopy(), state.getFileTable(), state.getOut(),stmt,state.getHeap(), state.getId() + 1);
    }

    public IStmt deepCopy() {
        return new forkStmt(stmt.deepCopy());
    }
}
