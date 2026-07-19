package lab3;

import java.util.EmptyStackException;

public class CustomStack<T> {
  private Node<T> top = null;
  private int size = 0;

  public boolean isEmpty() {
    return size == 0;
  }

  public void push(T element){
    Node<T> newNode = new Node<>(element);
    if(top != null){
      newNode.next = top;
      top.prev = newNode;
    }
    top = newNode;
    size++;
  }

  public T pop(){
    if(isEmpty()) throw new EmptyStackException();
    T data = top.data;
    top = top.next; 
    if(top != null){
      top.prev = null;
    }
    size--;
    return data;
  }

  public T peek() {
    if (isEmpty()) throw new EmptyStackException();
    return top.data;
  }

  public int size(){
    return size;
  }

}
