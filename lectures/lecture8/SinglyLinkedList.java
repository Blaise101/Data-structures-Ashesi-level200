package lectures.lecture8;


public class SinglyLinkedList <T> {

  private static class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  private Node<T> head;
  private int size;

  public SinglyLinkedList(){
    this.head = null;
    this.size = 0;
  }

  // Insertion
  public void insert(int position, T element){
    if(position < 1 || position > size+1){
      throw new IndexOutOfBoundsException("Invalid position!");
    }

    Node<T> newNode = new Node<>(element);

    // inserting at the front
    if(position == 1){
      newNode.next = head;
      head = newNode;
    }else{
      // insert at the middle or at the absolute tail
      Node<T> h = head;
      int count = 1;
      
      // loop do find the node at position (position - 1)
      while(count < position-1 && h != null){
        h = h.next;
        count++;
      }
      
      // Rewire pointers
      newNode.next = h.next;
      h.next=newNode;
    }
    size++;
  }

  // Deletion
  public void delete(int position){
    // deleting the first item
    if(position == 1){
      head = head.next;
    }else{
      // delete item in the middle or at the absolute tail
      Node<T> h = head;
      int count = 1;
      while(count < (position+1)){
        h = h.next;
        count++;
      }
      // skip over the target node
      h.next = h.next.next;
    }
    size--;
  }

  public T get(int position){
    if(position < 1 || position > size){
      throw new IndexOutOfBoundsException("Invalid position");
    }else{
      Node<T> h = head;
      int count = 1;
      while(count < position-1){
        h = h.next;
        count++;
      }
      return h.data;
    }
  }
}
