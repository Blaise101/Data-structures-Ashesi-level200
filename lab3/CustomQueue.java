package lab3;

public class CustomQueue<T> {
  private Node<T> head = null;
  private Node<T> tail = null;
  private int size = 0;

  public void enqueue(T element){
    Node<T> newNode = new Node<>(element);
    if (head == null) {
      head = tail = newNode;
    }else{
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
    size++;
  }

  public T dequeue(){
    if(isEmpty()) throw new RuntimeException("Queue Underflow");
    T data = head.data;
    head = head.next;
    if(head == null){
      tail = null; // The queue is now completely empty
    }else{
      head.prev = null;
    }

    // Ensure size never drops below 0
    if (size > 0) {
      size--;
    }
    
    return data;
  }

  public T peek() {
    if (head == null) {
      throw new java.util.NoSuchElementException("Queue is empty.");
    }
    return head.data;
  }

  public boolean isEmpty(){
    return head == null; // Binding structural emptiness directly to the head pointer
  }

  public int size() {
      return size;
  }
}