package DataStructures;

public interface MyIList <T>{
    public void add(T val);
    public T get(int index);
    public int size();
    public String toString();
    public void clear();
    public boolean includes(T val);
}
