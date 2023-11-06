package DataStructures;

public interface MyIDictionary <K, V>{
        public Boolean lookup(K key);
        public V get(K key);
        public void add(K key, V val);

        public String toString();
        public void clear();
}
