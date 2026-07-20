package lab3;

public class CustomDeque<T> {
  private Node<T> head = null;
  private Node<T> tail = null;
  private int size = 0;

  public void insertRear(T element) {
    Node<T> newNode = new Node<>(element);
    if (isEmpty()) {
      head = tail = newNode;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
    size++;
  }

  public T removeFront() {
    if (isEmpty()) throw new RuntimeException("Deque underflow");
    T data = head.data;
    head = head.next;
    if (head == null) {
      tail = null;
    } else {
      head.prev = null;
    }
    size--;
    return data;
  }

  public T removeRear() {
    if (isEmpty()) throw new RuntimeException("Deque underflow");
    T data = tail.data;
    tail = tail.prev;
    if (tail == null) {
      head = null;
    } else {
      tail.next = null;
    }
    size--;
    return data;
  }

  public T peekFront() {
    if (isEmpty()) throw new RuntimeException("Deque is empty");
    return head.data;
  }

  public T peekRear() {
    if (isEmpty()) throw new RuntimeException("Deque is empty");
    return tail.data;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }
}
