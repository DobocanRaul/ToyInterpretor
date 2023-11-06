package Repository;

import States.PrgState;

public class Repository implements IRepository{
    private PrgState[] states;
    private int size;

    public Repository() {
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
}
