package assignment3;

public class MyStack<T> {
  private Node<T> top;
  private int size;

  public MyStack(){
    this.top = null;
    this.size = 0;

  }

  public void push(T element){
    Node<T> newNode = new Node<>(element);
    newNode.next = top;
    top = newNode;
    size++;
  }

  public T pop(){
    if(isEmpty()){
      throw new RuntimeException("Stack Underflow!");
    }
    T data = top.data;
    top = top.next;
    size--;
    return data;
  }

  public T peek(){
    if(isEmpty()){
      throw new RuntimeException("The stack is empty");
    }
    return top.data;
  }

  public boolean isEmpty(){
    return size == 0;
  }

  public int size(){
    return size;
  }
  
}
