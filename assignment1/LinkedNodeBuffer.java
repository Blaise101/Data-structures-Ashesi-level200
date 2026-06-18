package assignment1;

public class LinkedNodeBuffer<T extends Comparable<T>> {
  private Node<T> head;
  private int size;
  private int capacity;

  public LinkedNodeBuffer(int capacity){
    this.head = null;
    this.size = 0;
    this.capacity = capacity;
  }

  public boolean isFull(){return size >= capacity;}
  public boolean isEmpty(){return head == null;}

  public void add(T item){
    if(isFull()){
      throw new UnsupportedOperationException("Buffer is full!");
    }

    Node<T> newNode = new Node<>(item);

    if(isEmpty()){
      head = newNode; // If empty, this is the first node
    }else{
      Node<T> current = head;
      while(current.getNext() != null){ // find the end of the chain
        current = current.getNext();
      }
      current.setNext(newNode); // Link the last node to our new node
    }
    size++; // Resize the tracker
  }

  public  int getSize(){return this.size;}

  public Node<T> get(int index){
    if(index < 0 || index >= size){
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    Node<T> current = head;

    for(int i = 0; i < index; i++){
      current = current.getNext();
    }

    return current;
  }

  public void swap(int i, int j){
    Node<T> first_node = this.get(i);
    Node<T> second_node = this.get(j);
    T temp_node = first_node.getData();

    first_node.setData(second_node.getData());
    second_node.setData(temp_node);
  }

  public void display(){
    System.out.print("[");
    Node<T> current = head;
    while(current != null){
      System.out.print(current.getData());
      if(current.getNext() != null){
        System.out.print(", ");
      }
      current = current.getNext(); // Move to next node
    }
    System.out.print("]");
  }

  // Problem 2
  public int selectionSort(LinkedNodeBuffer<T> buffer){

    int n =buffer.getSize();
    int comparisonCount = 0;

    // task 2
    if(n <= 1){
      return 0;
    }

    for(int i = 0; i < n-2; i++){
      int mainIndex = i;
      for(int j = i+1; j < n-1; j++){
        comparisonCount++;

        T nodeA = buffer.get(j).getData();
        T nodeB = buffer.get(mainIndex).getData();

        if(nodeA.compareTo(nodeB) < 0){
          mainIndex = j;
        }
      }
    }

    return comparisonCount; // Return the total operations executed
  }
}
