package Repository;

import Exceptions.MyException;
import States.PrgState;
import java.io.*;
import java.util.List;

public class Repository implements IRepository{
    private List<PrgState> states;
    private String logFilePath;
    private int size;

    public Repository(String FilePath) {
        logFilePath = FilePath;
        size = 0;
    }

    public void add(PrgState state) {
        states.add(state);
    }

    public void remove(int index) {
        states.remove(index);
    }

    public PrgState get(int index) {
        return states.get(index);
    }

    public int size() {
        return size;
    }

    public void logPrgStateExec(PrgState prgState) throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            ///clear the log file
            logFile.print("");
            logFile.println(prgState.toString());
            logFile.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    public List<PrgState> getPrgList() {
        return states;
    }

    public void setPrgList(List<PrgState> newPrgList) {
        states = newPrgList;
    }
}
