package DataStructures;

import java.util.*;

public class MyList<T> implements MyIList<T>{

    private List<T> list;

    public MyList(){
        this.list = new ArrayList<T>();
    }
    public void clear(){
        list.clear();
    }
    public void add(T val){
        list.add(val);
    }

    public T get(int index){
        return list.get(index);
    }

    public int size(){
        return list.size();
    }

    public String toString(){
        return list.toString();
    }

    public boolean includes(T val){
        return list.contains(val);
    }
}
