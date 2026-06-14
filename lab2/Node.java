package lab2;

public class Node<T> {
    private T data;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
    }
}