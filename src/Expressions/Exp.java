package Expressions;
import DataStructures.MyHeap;
import DataStructures.MyIDictionary;
import Exceptions.MyException;
import Values.Value;
public interface Exp {
    public Value eval(MyIDictionary<String, Value> tbl, MyHeap hp) throws MyException;
    public String toString();

    public Exp deepcopy();
}
