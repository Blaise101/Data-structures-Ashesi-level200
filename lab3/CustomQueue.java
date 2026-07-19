package lab3;


public class CustomQueue<T> {
  private Node<T> head = null;
  private Node<T> tail = null;
  private int size = 0;

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void enqueue(T element){
    Node<T> newNode = new Node<>(element);
    if(isEmpty()){
      head = tail = newNode;
    }else{
      tail.prev = newNode;
      newNode.next = tail;
      tail = newNode;
    }
    size++;
  }

  public T dequeue(){
    if(isEmpty()) throw new RuntimeException("Queue Underflow");
    T data = head.data;
    head = head.next;
    if(head == null){
      tail = null;
    }else{
      head.prev = null;
    }
    size--;
    return data;
  }

  public T peek() {
    if (isEmpty()) throw new RuntimeException("Queue is empty");
    return head.data;
  }

}
