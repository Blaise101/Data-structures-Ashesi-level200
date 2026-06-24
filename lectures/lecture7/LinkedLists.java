package lectures.lecture7;

public class LinkedLists {
    // Node class for the linked list
    private static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    
    // Constructor to initialize empty linked list
    public LinkedLists() {
        this.head = null;
    }
    
    // Operation 1: Insert at the beginning
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }
    
    // Operation 2: Insert at the end
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
    
    // Operation 3: Insert at a specific position
    public void insertAtPosition(int data, int position) {
        if (position < 0) return;
        
        Node newNode = new Node(data);
        if (position == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        
        Node current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        
        if (current != null) {
            newNode.next = current.next;
            current.next = newNode;
        }
    }
    
    // Operation 4: Delete from the beginning
    public void deleteFromBeginning() {
        if (head != null) {
            head = head.next;
        }
    }
    
    // Operation 5: Delete from the end
    public void deleteFromEnd() {
        if (head == null) return;
        if (head.next == null) {
            head = null;
            return;
        }
        
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }
    
    // Operation 6: Delete at a specific position
    public void deleteAtPosition(int position) {
        if (position < 0 || head == null) return;
        
        if (position == 0) {
            head = head.next;
            return;
        }
        
        Node current = head;
        for (int i = 0; i < position - 1 && current.next != null; i++) {
            current = current.next;
        }
        
        if (current.next != null) {
            current.next = current.next.next;
        }
    }
    
    // Operation 7: Search for an element
    public boolean search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // Operation 8: Display all elements
    public void display() {
        Node current = head;
        System.out.print("LinkedList: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    // Operation 9: Get the size of the linked list
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    // Operation 10: Reverse the linked list
    public void reverse() {
        Node prev = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }
    
    // Main method to demonstrate all operations
    public static void main(String[] args) {
        LinkedLists list = new LinkedLists();
        
        // Display empty list
        System.out.println("--- Initial List ---");
        list.display();
        
        // Insert at end
        System.out.println("\n--- Insert at End: 10, 20, 30 ---");
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.display();
        
        // Insert at beginning
        System.out.println("\n--- Insert at Beginning: 5 ---");
        list.insertAtBeginning(5);
        list.display();
        
        // Insert at position
        System.out.println("\n--- Insert at Position 2: 15 ---");
        list.insertAtPosition(15, 2);
        list.display();
        
        // Display size
        System.out.println("\n--- Size: " + list.size() + " ---");
        
        // Search element
        System.out.println("\n--- Search for 15: " + list.search(15) + " ---");
        System.out.println("--- Search for 100: " + list.search(100) + " ---");
        
        // Delete from beginning
        System.out.println("\n--- Delete from Beginning ---");
        list.deleteFromBeginning();
        list.display();
        
        // Delete from end
        System.out.println("\n--- Delete from End ---");
        list.deleteFromEnd();
        list.display();
        
        // Delete at position
        System.out.println("\n--- Delete at Position 1 ---");
        list.deleteAtPosition(1);
        list.display();
        
        // Reverse the list
        System.out.println("\n--- Reverse the List ---");
        list.reverse();
        list.display();
        
        // Final size
        System.out.println("\n--- Final Size: " + list.size() + " ---");
    }
}