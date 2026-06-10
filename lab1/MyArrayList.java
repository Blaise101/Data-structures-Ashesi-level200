package lab1;

public class MyArrayList<E> {
    // Internal array to hold the elements
    private Object[] elementData;
    // Tracks the actual number of elements currently stored
    private int size;
    // Default initial capacity of the internal array
    private static final int DEFAULT_CAPACITY = 10;

    // Constructor initializing the list with default capacity
    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Returns the current number of elements in the list
    public int size() {
        return this.size;
    }

    // Checks if the list has no elements
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Adds an element to the end of the list. Resizes array if full.
    public void add(E element) {
        // Dynamic resizing calculation: double capacity if internal array is completely full
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = element;
        size++;
    }

    // Helper method to double the capacity of the array when filled
    private void grow() {
        int newCapacity = elementData.length * 2;
        Object[] newArray = new Object[newCapacity];
        
        // Manual array element migration loop
        for (int i = 0; i < size; i++) {
            newArray[i] = elementData[i];
        }
        elementData = newArray;
    }

    // Helper method to validate if an index is within legal active bounds
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Retrieves element at specific index with type casting safety
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    // Modifies element at a specific index and returns the old value
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    // Removes element at specific index and shifts subsequent elements left
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E removedValue = (E) elementData[index];

        // Element shifting loop to close the gap created by removal
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        
        // Clear reference for garbage collection and decrement tracking size
        elementData[size - 1] = null;
        size--;
        
        return removedValue;
    }

    // Iterates and prints out all elements currently active in the list
    public void display() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(elementData[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}