package Repository;

import Exceptions.MyException;
import States.PrgState;

public interface IRepository {
    public void add(PrgState obj);
    public void remove(int index);
    public PrgState get(int index);
    public int size();
    public void logPrgStateExec() throws MyException;
}
