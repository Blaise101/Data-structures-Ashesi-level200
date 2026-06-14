package lab2;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Initializing Generic Node Buffer ===");
        NodeBuffer<Integer> buffer = new NodeBuffer<>(10);
        
        buffer.add(45);
        buffer.add(12);
        buffer.add(85);
        buffer.add(3);
        buffer.add(21);
        
        System.out.print("Original Buffer: "); 
        buffer.display(); // Expected: [45, 12, 85, 3, 2
        
        System.out.println("\n=== Executing Sorting Algorithm ==="); 
        int operations = buffer.selectionSort(); 
        
        System.out.print("Sorted Buffer: "); 
        buffer.display(); // Expected: [3, 12, 21, 45, 8
        System.out.println("Basic Operations executed (Comparisons): " + operations); 
    }
}