
import Commands.ExitCommand;
import Commands.RunExample;
import Controller.Controller;
import DataStructures.MyStack;
import DataStructures.MyDictionary;
import DataStructures.MyIList;
import DataStructures.MyList;
import Expressions.*;
import Repository.Repository;
import Statements.*;
import States.PrgState;
import Types.*;
import Values.*;

import java.io.BufferedReader;
import java.nio.Buffer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyStack<IStmt> exeStack = new MyStack<>();
        MyStack<IStmt> exeStack2 = new MyStack<>();
        MyStack<IStmt> exeStack3 = new MyStack<>();
        MyDictionary<String, Value> symTable = new MyDictionary<>();
        MyDictionary<String, Value> symTable2 = new MyDictionary<>();
        MyDictionary<String, Value> symTable3 = new MyDictionary<>();
        MyDictionary<StringValue, BufferedReader> fileTable=new MyDictionary<>();
        MyDictionary<StringValue, BufferedReader> fileTable2=new MyDictionary<>();
        MyDictionary<StringValue, BufferedReader> fileTable3=new MyDictionary<>();
        MyIList<Value> out = new MyList<>();
        MyIList<Value> out2 = new MyList<>();
        MyIList<Value> out3 = new MyList<>();
        IStmt org = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        IStmt org2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        ///IStmt org3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(false))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        IStmt org3=new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("test.in"))),new CompStmt(new openRFile(new VarExp("varf")),new CompStmt(new VarDeclStmt("varc",new IntType()),new CompStmt(new readFile(new VarExp("varf"),"varc"),new CloseRFile(new VarExp("varf")))))));
        PrgState prg = new PrgState(exeStack, symTable,fileTable, out, org);
        PrgState prg2 = new PrgState(exeStack2, symTable2,fileTable2, out2, org2);
        PrgState prg3 = new PrgState(exeStack3, symTable3,fileTable3, out3, org3);
        Repository repo = new Repository("log.txt");
        Repository repo2 = new Repository("log2.txt");
        Repository repo3 = new Repository("log3.txt");
        repo.add(prg);
        repo2.add(prg2);
        repo3.add(prg3);
        Controller ctrl1 = new Controller(repo);
        Controller ctrl2 = new Controller(repo2);
        Controller ctrl3 = new Controller(repo3);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", org.toString(), ctrl1));
        menu.addCommand(new RunExample("2", org2.toString(), ctrl2));
        menu.addCommand(new RunExample("3", org3.toString(), ctrl3));
        menu.show();
    }
}