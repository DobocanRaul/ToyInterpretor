package Statements;

import DataStructures.*;
import States.PrgState;
import Exceptions.*;
import Expressions.Exp;
import Values.Value;
import Types.Type;
public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> out = state.getOut();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        out.add(exp.eval(symTbl,state.getHeap()));
        return null;
    }

    public IStmt deepCopy() {
        return new PrintStmt(exp.deepcopy());
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
