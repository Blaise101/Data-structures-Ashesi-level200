package revisions.midsem;

public class Doubly<T> {
  private static class Node<T> {
    Node<T> prev;
    T data;
    Node<T> next;

    Node(T data){
      this.prev = null;
      this.data = data;
      this.next = null;
    }
  }

  private Node<T> head;
  private int size;

  public void insert(T element, int position){
    if(position < 1 || position > size){
      throw new IndexOutOfBoundsException("Invalid Position");
    }
    Node<T> newNode = new Node<>(element);
    Node<T> h = head;
    int count = 1;
    while(count < (position-1)){
      h = h.next;
      count++;
    }
    newNode.next = h.next;
    newNode.prev = h;
    if(h.next != null){
      h.next.prev = newNode;
    }
    h.next = newNode;
    size++;
  }
}


/**
ALGORITHM insertInMiddle(element, position)
    IF position <= 1 OR position >= size THEN
        THROW IndexOutOfBoundsException
    ENDIF
    
    IF head == NULL THEN
        PRINT "List is empty, cannot insert in the middle"
        RETURN
    ENDIF

    newNode = CREATE NODE(element)
    h = head
    count = 1
    
    WHILE count < (position - 1) DO
        h = h.next
        count = count + 1
    ENDWHILE
    
    newNode.next = h.next
    newNode.prev = h
    IF h.next != NULL THEN
        h.next.prev = newNode
    ENDIF
    h.next = newNode
    
    size = size + 1
END ALGORITHM
*/