package Expressions;
import DataStructures.MyHeap;
import DataStructures.MyIDictionary;
import Exceptions.MyException;
import Values.Value;
import Types.Type;
public interface Exp {
    public Value eval(MyIDictionary<String, Value> tbl, MyHeap hp) throws MyException;
    public String toString();
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
    public Exp deepcopy();
}
