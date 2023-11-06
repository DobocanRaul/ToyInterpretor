import DataStructures.MyIStack;
import DataStructures.MyStack;
import DataStructures.MyDictionary;
import DataStructures.MyIList;
import DataStructures.MyList;
import Expressions.*;
import Repository.Repository;
import Statements.*;
import States.PrgState;
import Types.*;
import Values.IntValue;
import Values.*;

public class Main {
    public static void main(String[] args) {
        MyStack<IStmt> exeStack = new MyStack<>();
        MyStack<IStmt> exeStack2 = new MyStack<IStmt>();
        MyStack<IStmt> exeStack3 = new MyStack<IStmt>();
        MyDictionary<String, Value> symTable = new MyDictionary<String, Value>();
        MyDictionary<String, Value> symTable2 = new MyDictionary<String, Value>();
        MyDictionary<String, Value> symTable3 = new MyDictionary<String, Value>();
        MyIList<Value> out = new MyList<Value>();
        MyIList<Value> out2 = new MyList<Value>();
        MyIList<Value> out3 = new MyList<Value>();
        IStmt org= new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))),new PrintStmt(new VarExp("v"))));
        IStmt org2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                                ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        IStmt org3=new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(false))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        PrgState prg = new PrgState(exeStack, symTable, out,org);
        PrgState prg2 = new PrgState(exeStack2, symTable2, out2,org2);
        PrgState prg3 = new PrgState(exeStack3, symTable3, out3,org3);
        Repository repo = new Repository();
        repo.add(prg);
        repo.add(prg2);
        repo.add(prg3);
        Controller ctrl = new Controller(repo);
        ctrl.setFlag();
        try {
            ctrl.chooseState(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ctrl.allStep();

    }
}