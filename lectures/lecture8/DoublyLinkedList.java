package lectures.lecture8;

public class DoublyLinkedList<T> {
  private static class Node<T>{
    Node<T> prev;
    T data;
    Node<T> next;

    Node(T data){
      this.prev = null;
      this.next = null;
      this.data = data;
    }
  }

  private Node<T> head;
  private int size;

  // insertion
  public void insert(int pos, T element){
    Node<T> newNode = new Node<>(element);
    Node<T> h = head;
    int count = 1;

    while(count < (pos-1)){
      h = h.next;
      count++;
    }

    newNode.next=h.next;
    newNode.prev = h;

    if(h.next != null){
      h.next.prev = newNode;
    }
    h.next = newNode;
    size++;
  }

  // delete
  public void delete(int pos){
    Node<T> h = head;
    int count = 1;
    while(count<(pos-1)){
      h = h.next;
      count++;
    }
    h.next= h.next.next;
    if(h.next!=null){
      h.next.prev = h;
    }
    size--;
  }

  public T get(int pos){
    Node<T> h = head;
    int count  = 1;
    while(count <= pos){
      h = h.next;
      count++;
    }
    return h.data;
  }

}
