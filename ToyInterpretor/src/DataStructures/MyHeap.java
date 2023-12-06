package DataStructures;

import Values.Value;

import java.util.Hashtable;
import java.util.Map;

public class MyHeap {
    Map<Integer, Value> Heap;
    Integer FreeLocation=1;

    public MyHeap(){
        this.Heap=new Hashtable<Integer,Value>();
    }

    @Override
    public String toString() {
        String str = "";
        if(!this.Heap.isEmpty())
            for(Integer key : Heap.keySet()){
            str += key.toString() + " -> " + Heap.get(key).toString() + "\n";
            }
        return str;
    }

    public void add(Value val){
        Heap.put(FreeLocation, val);
        FreeLocation++;
    }
    public void remove(Integer key){
        Heap.remove(key);
    }

    public Boolean isDefined(Integer key){
        return Heap.containsKey(key);
    }

    public void update(Integer key, Value val){
        Heap.put(key, val);
    }
    public Value get(Integer key){
        return Heap.get(key);
    }

    public Integer getAddress(Value val){
        for(Integer key : Heap.keySet()){
            if(Heap.get(key).equals(val)){
                return key;
            }
        }
        return -1;
    }

    public void setContent(Map<Integer, Value> newHeap){
        Heap=newHeap;
    }

    public Map<Integer, Value> getContent(){
        return Heap;
    }
}
