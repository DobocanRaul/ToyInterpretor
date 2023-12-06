package Repository;

import Exceptions.MyException;
import States.PrgState;
import java.io.*;
public class Repository implements IRepository{
    private PrgState[] states;
    private String logFilePath;
    private int size;

    public Repository(String FilePath) {
        logFilePath = FilePath;
        states = new PrgState[100];
        size = 0;
    }

    public void add(PrgState state) {
        states[size++] = state;
    }

    public void remove(int index) {
        for(int i = index; i < size - 1; i++) {
            states[i] = states[i + 1];
        }
    }

    public PrgState get(int index) {
        return states[index];
    }

    public int size() {
        return size;
    }

    public void logPrgStateExec() throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            ///clear the log file
            logFile.print("");
            logFile.println(states[0].toString());
            logFile.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }
}
