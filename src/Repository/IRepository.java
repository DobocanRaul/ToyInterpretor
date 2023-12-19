package Repository;

import Exceptions.MyException;
import States.PrgState;

import java.util.List;

public interface IRepository {
    public void add(PrgState obj);
    public void remove(int index);
    public PrgState get(int index);
    public int size();
    public void logPrgStateExec(PrgState prgstate) throws MyException;
    public List<PrgState> getPrgList();
    public void setPrgList(List<PrgState> newPrgList);
}
