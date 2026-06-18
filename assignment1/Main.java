package assignment1;

public class Main {
  public static void main(String[] args) {
    
    System.out.println("=== Initialising Generic Linked Node Buffer ===");
    LinkedNodeBuffer<Integer> myBuffer = new LinkedNodeBuffer<Integer>(5);
    
    myBuffer.add(45);
    myBuffer.add(12);
    myBuffer.add(85);
    myBuffer.add(3);
    myBuffer.add(21);

    System.out.print("Original Buffer: ");
    myBuffer.display();
    
    int returnedCount = myBuffer.selectionSort();
    
    System.out.println("\n=== Executing Sorting Algorithm ===");
    System.out.print("Sorted Buffer: ");
    myBuffer.display();

    System.out.println("Basic Operations executed (Comparisons): " + returnedCount + "\n");
  }
}
