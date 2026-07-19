package lab3;

public class Node<T> {
  T data;
  Node<T> next;
  Node<T> prev;

  public Node(T data){
    this.data = data;
  }
}
