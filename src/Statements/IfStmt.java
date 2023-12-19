package Statements;

import Expressions.Exp;
import Types.BoolType;
import Values.Value;
import States.PrgState;
import Exceptions.MyException;
import Values.*;
public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        Value cond = exp.eval(state.getSymTable(), state.getHeap());
        if (cond.getType().equals(new BoolType())) {
            BoolValue b = (BoolValue) cond;
            if (b.getVal() == true)
                state.getStk().push(thenS);
            else
                state.getStk().push(elseS);
        } else throw new MyException("Conditional expression is not a boolean");
        return null;

    }

    public IStmt deepCopy() {
        return new IfStmt(exp.deepcopy(), thenS.deepCopy(), elseS.deepCopy());
    }
}
