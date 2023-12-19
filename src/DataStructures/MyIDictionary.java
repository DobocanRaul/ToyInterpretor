package DataStructures;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MyIDictionary <K, V>{
        public Boolean lookup(K key);
        public V get(K key);
        public void add(K key, V val);

        public void update(K key, V val);
        public void remove(K key);
        public String toString();
        public void clear();
        public Collection<V> getContent();
        public MyIDictionary<K,V> deepcopy();
}
