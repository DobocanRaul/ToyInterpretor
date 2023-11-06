package DataStructures;

import java.util.Dictionary;
import java.util.Hashtable;

public class MyDictionary<K,V> implements MyIDictionary<K, V>{
    Hashtable<K, V> dict;

    public MyDictionary(){
        this.dict = new Hashtable<K, V>();
    }

    public Boolean lookup(K key){
        if (dict.containsKey(key)){
            return true;
        }
        return false;
    }

    public void clear(){
        dict.clear();
    }
    public V get(K key){
        return dict.get(key);
    }

    public void add(K key, V val){
        dict.put(key, val);
    }

    public String toString(){
        String str = "";
        for(K key : dict.keySet()){
            str += key.toString() + " -> " + dict.get(key).toString() + ", ";
        }
        return str;
    }
}
