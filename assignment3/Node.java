package assignment3;

// Node class for the custom Stack
public class Node<T> {
  T data;
  Node<T> next;

  public Node(T data){
    this.data = data;
    this.next = null;
  }

}
