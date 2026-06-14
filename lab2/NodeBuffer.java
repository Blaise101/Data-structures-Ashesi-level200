package lab2;

public class NodeBuffer<T extends Comparable<T>> {
    private Node<T>[] elements;
    private int size;    

    @SuppressWarnings("unchecked")
    public NodeBuffer(int capacity) {
        this.elements = (Node<T>[]) new Node[capacity];
        this.size = 0; 
    }

    public void add(T item) {
        if (size < elements.length) {
            elements[size] = new Node<>(item); 
            size++; 
        } else {
            System.out.println("Buffer is full!"); 
        }
    }

    public int getSize() {
        return this.size; 
    }

    /**
     * Returns the Node at the specified inde44]
     */
    public Node<T> get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid buffer index!");
        }
        return elements[index]; 
    }

    /**
     * Swaps the nodes at two different indices
     * This operation is type-safe because the internal elements array is structurally guaranteed to only contain Node instances of matching type parameters <T> checked at compile time.
     */
    public void swap(int i, int j) {
        Node<T> temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;       
    }

    // Problem 2, Step 1 
    public int selectionSort() {
        int n = this.getSize();
        int comparisonCount = 0;

        for (int i = 0; i <= n - 2; i++) {
            int minIndex = i;

            for (int j = i + 1; j <= n - 1; j++) {
                comparisonCount++;

                T dataA = this.get(j).getData();
                T dataB = this.get(minIndex).getData();

                if (dataA.compareTo(dataB) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                this.swap(i, minIndex);
            }
        }
        return comparisonCount; 
    }

    // Problem 2, Step 2
    public void display() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i] + (i < size - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}