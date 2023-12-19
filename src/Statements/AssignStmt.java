package Statements;
import Exceptions.MyException;
import Expressions.Exp;
import Types.Type;
import Values.Value;
import DataStructures.*;
import States.PrgState;
public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }
    public String toString() {
        return id + " = " + exp.toString();
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if (symTbl.lookup(id)== true ) {
            Value val = exp.eval(symTbl, state.getHeap());
            Type typId = (symTbl.get(id)).getType();
            if (val.getType().equals(typId))
                symTbl.add(id, val);
            else
                throw new MyException("declared type of variable" + id + " and type of the assigned expression do not match");
        }
        else
            throw new MyException("the used variable" + id + " was not declared before");
        return null;

    }

    public IStmt deepCopy() {
        return new AssignStmt(id, exp.deepcopy());
    }
}

