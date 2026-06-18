package assignment1;

public class Node<T> {
  private T data;
  private Node<T> next;
  
  public Node(T data){
    this.data = data;
    this.next = null;
  }

  public T getData(){return data;}
  public Node<T> getNext(){return next;}

  public void steData(T data){this.data = data;}
  public void steNext(Node<T> next){this.next = next;}

  @Override
  public String toString(){
    return String.valueOf(this.data);
  }
}
