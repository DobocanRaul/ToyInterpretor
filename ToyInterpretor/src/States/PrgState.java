package States;

import DataStructures.*;
import Values.*;
import Statements.*;
import Exceptions.*;

import java.io.BufferedReader;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyHeap Heap;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIList<Value> out;
    IStmt originalProgram;

    public PrgState( MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl,MyIDictionary<StringValue,BufferedReader> filetbl, MyIList<Value> ot, IStmt prg,MyHeap hp) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        Heap=hp;
        fileTable=filetbl;
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
    public MyHeap getHeap() {
        return Heap;
    }
    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public String toString() {
        return "ExeStack:\n" + exeStack.toString() + "\nSymTable:\n" + symTable.toString() + "\nOut:\n" + out.toString() +"\nFileTable:\n"+fileTable.toString()+"\nHeap:\n"+Heap.toString() +"\n Original Program: " + originalProgram.toString() + "\n ------------------------------------------------------------------------------ \n";
    }

    public void oneStep(){
        if(!exeStack.isEmpty()) {
        IStmt crtStmt = exeStack.pop();
        try {
            crtStmt.execute(this);
        } catch (MyException e) {
            System.out.println(e.toString());
            }
        }
    }


}
