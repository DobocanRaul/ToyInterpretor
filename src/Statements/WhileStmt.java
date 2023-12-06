package Statements;

import Expressions.Exp;
import States.PrgState;
import Types.BoolType;
import Values.BoolValue;
import Values.Value;

public class WhileStmt implements IStmt{
    private IStmt stmt;
    private Exp exp;

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    public String toString() {
        return "while(" + exp.toString() + ") " + stmt.toString();
    }

    public PrgState execute(PrgState state) {

        Value val;
        try {
            val = exp.eval(state.getSymTable(),state.getHeap());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        if(val.getType().equals(new BoolType()))
        {
            BoolValue boolVal = (BoolValue)val;
            if(boolVal.getVal())
            {
                state.getStk().push(new WhileStmt(exp, stmt));
                state.getStk().push(stmt);
            }
        }
        else
        {
            System.out.println("Condition does not evaluate to boolean!");
        }
        return state;
    }

    public IStmt deepCopy() {
        return new WhileStmt(exp.deepcopy(), stmt.deepCopy());
    }


}
