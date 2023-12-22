
import Commands.ExitCommand;
import Commands.RunExample;
import Controller.Controller;
import DataStructures.*;
import Exceptions.MyException;
import Expressions.*;
import Repository.Repository;
import Statements.*;
import States.PrgState;
import Types.*;
import Values.*;

import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        int idadder=1;
        MyStack<IStmt> exeStack = new MyStack<>();
        MyStack<IStmt> exeStack2 = new MyStack<>();
        MyStack<IStmt> exeStack3 = new MyStack<>();
        MyStack<IStmt> exeStack4 = new MyStack<>();
        MyStack<IStmt> exeStack5 = new MyStack<>();

        MyDictionary<String, Value> symTable = new MyDictionary<>();
        MyDictionary<String, Value> symTable2 = new MyDictionary<>();
        MyDictionary<String, Value> symTable3 = new MyDictionary<>();
        MyDictionary<String, Value> symTable4 = new MyDictionary<>();
        MyDictionary<String, Value> symTable5 = new MyDictionary<>();

        MyDictionary<StringValue, BufferedReader> fileTable=new MyDictionary<>();
        MyDictionary<StringValue, BufferedReader> fileTable2=new MyDictionary<>();
        MyDictionary<StringValue, BufferedReader> fileTable3=new MyDictionary<>();
        MyDictionary<StringValue, BufferedReader> fileTable4=new MyDictionary<>();
        MyDictionary<StringValue, BufferedReader> fileTable5=new MyDictionary<>();

        MyHeap heap=new MyHeap();
        MyHeap heap2=new MyHeap();
        MyHeap heap3=new MyHeap();
        MyHeap heap4=new MyHeap();
        MyHeap heap5=new MyHeap();

        MyIList<Value> out = new MyList<>();
        MyIList<Value> out2 = new MyList<>();
        MyIList<Value> out3 = new MyList<>();
        MyIList<Value> out4 = new MyList<>();
        MyIList<Value> out5 = new MyList<>();
        IStmt org=new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))),new CompStmt(new NewStmt("a",new ValueExp(new IntValue(22))),new CompStmt(new forkStmt(new CompStmt(new WriteHeap("a",new ValueExp(new IntValue(30))),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new ReadHeap(new VarExp("a"))))))),new CompStmt(new PrintStmt(new VarExp("v")),new CompStmt(new PrintStmt(new ReadHeap(new VarExp("a"))),new CompStmt(new NewStmt("a",new ValueExp(new IntValue(23))),new PrintStmt(new VarExp("v"))))))))));
        IStmt org2 =new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new NewStmt("a",new ValueExp(new IntValue(20))),new CompStmt(new PrintStmt(new ReadHeap(new VarExp("a"))),new CompStmt(new WriteHeap("a",new ValueExp(new IntValue(30))),new PrintStmt(new ReadHeap(new VarExp("a")))))));
        IStmt org3=new CompStmt(new VarDeclStmt("a",new IntType()),new CompStmt(new AssignStmt("a",new ValueExp(new IntValue(5))),new WhileStmt(new RelationalExp(new VarExp("a"),new ValueExp(new IntValue(2)),">"),new CompStmt(new PrintStmt(new VarExp("a")),new AssignStmt("a",new ArithExp('-',new VarExp("a"),new ValueExp(new IntValue(1))))))));
        IStmt org4=new CompStmt(new VarDeclStmt("v",new RefType((new IntType()))),new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new NewStmt("a",new VarExp("v")),new CompStmt(new NewStmt("v",new ValueExp(new IntValue(30))),new PrintStmt(new ReadHeap(new ReadHeap(new VarExp("a")))))))));
        IStmt org5=new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new NewStmt("a",new ValueExp(new IntValue(5))),new NewStmt("a",new ValueExp(new IntValue(6)))));
        //Test daca e master
        PrgState prg = new PrgState(exeStack, symTable,fileTable, out, org,heap,idadder);
        PrgState prg2 = new PrgState(exeStack2, symTable2,fileTable2, out2, org2,heap2,idadder);
        PrgState prg3 = new PrgState(exeStack3, symTable3,fileTable3, out3, org3,heap3,idadder);
        PrgState prg4 = new PrgState(exeStack4, symTable4,fileTable4, out4, org4,heap4,idadder);
        PrgState prg5 = new PrgState(exeStack5, symTable5,fileTable5, out5, org5,heap5,idadder);

        Repository repo = new Repository("log.txt");
        Repository repo2 = new Repository("log2.txt");
        Repository repo3 = new Repository("log3.txt");
        Repository repo4 = new Repository("log4.txt");
        Repository repo5 = new Repository("log5.txt");

        try {
            org3.typecheck(new MyDictionary<String, Type>());
            repo3.add(prg3);
        }
        catch (MyException e)
        {
            System.out.println(e.toString());
        }
        repo.add(prg);
        repo2.add(prg2);
        repo4.add(prg4);
        repo5.add(prg5);

        Controller ctrl1 = new Controller(repo);
        Controller ctrl2 = new Controller(repo2);
        Controller ctrl3 = new Controller(repo3);
        Controller ctrl4 = new Controller(repo4);
        Controller ctrl5 = new Controller(repo5);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", org.toString(), ctrl1));
        menu.addCommand(new RunExample("2", org2.toString(), ctrl2));
        menu.addCommand(new RunExample("3", org3.toString(), ctrl3));
        menu.addCommand(new RunExample("4", org4.toString(), ctrl4));
        menu.addCommand(new RunExample("5", org5.toString(), ctrl5));
        menu.show();
    }
}