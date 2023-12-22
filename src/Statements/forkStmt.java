package Statements;

import DataStructures.MyIDictionary;
import DataStructures.MyIStack;
import DataStructures.MyStack;
import Exceptions.MyException;
import States.PrgState;
import Types.Type;

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

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        stmt.typecheck(typeEnv.deepcopy());
        return typeEnv;
    }
}
