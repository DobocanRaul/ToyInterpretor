package DataStructures;

public class MyStack<T> implements MyIStack<T>{
    private java.util.Stack<T> stack;

    public MyStack() {
        stack = new java.util.Stack<T>();
    }

    public T pop() {
        return stack.pop();
    }
    public void clear() {
        stack.clear();
    }
    public void push(T v) {
        stack.push(v);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        String str = "";
        ///do it inverse
        for (int i = stack.size() - 1; i >= 0; i--) {
            str += stack.get(i).toString() + "\n";
        }
        return str;
    }
}
