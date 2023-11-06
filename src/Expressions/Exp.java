package Expressions;
import DataStructures.MyIDictionary;
import Exceptions.MyException;
import Values.Value;
public interface Exp {
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException;
    public String toString();

    public Exp deepcopy();
}
