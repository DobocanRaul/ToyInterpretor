package States;

import DataStructures.*;
import Values.*;
import Statements.*;
import Exceptions.*;
public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    IStmt originalProgram;

    public PrgState( MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value> ot, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = prg;
        stk.push(prg.deepCopy());
    }
    public void clean(){
        exeStack.clear();
        symTable.clear();
        out.clear();
        exeStack.push(originalProgram.deepCopy());
    }
    public MyIStack<IStmt> getStk() {
        return exeStack;
    }

    public MyIDictionary<String,Value> getSymTable() {
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public String toString() {
        return "ExeStack:\n" + exeStack.toString() + "\nSymTable:\n" + symTable.toString() + "\nOut:\n" + out.toString() + "\n + Original Program:\n" + originalProgram.toString() + "\n";
    }

    public void oneStep(){
        if(exeStack.isEmpty()) {
            System.out.println(out.toString());
        }
        IStmt crtStmt = exeStack.pop();
        try {
            crtStmt.execute(this);
        } catch (MyException e) {
            System.out.println(e.toString());
        }
    }


}
