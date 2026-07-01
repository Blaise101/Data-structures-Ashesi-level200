package revisions.midsem;

public class Singly<T> {
  private static class Node<T> {
    T data;
    Node<T> next;

    Node(T data){
      this.data = data;
      this.next = null;
    }
  }

  private Node<T> head;
  private int size;

  public void removeDuplicates(Node<T> head){
    if (head == null || head.next == null) {
      return;
    }
    Node<T> current = head;
    while(current != null){
      Node<T> h = current;
      while(h.next != null){
        if(h.next.data.equals(current.data)){
          h.next = h.next.next;
          size--;
        }else{
          h = h.next;
        }
      }
    }
  }
}



/*

ALGORITHM removeDuplicates(head)
  IF head == NULL THEN 
    RETURN
  ENDIF
  
  current = head
  
  WHILE current != NULL DO
    runner = current
      
    WHILE runner.next != NULL DO
      IF runner.next.data == current.data THEN
        runner.next = runner.next.next
      ELSE
        runner = runner.next
      ENDIF
    ENDWHILE
    current = current.next
  ENDWHILE
END ALGORITHM

*/