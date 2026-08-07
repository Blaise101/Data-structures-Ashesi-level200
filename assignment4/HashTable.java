package assignment4;

public class HashTable<K, V> {
    private static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> next;

        public Node(K key, V value, Node<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int initial_capacity = 8;
    private double load_factor_threshold = 0.75;
    private double shrink_factor_threshold = 0.25;

    private Node<K, V>[] buckets;
    private int size;
    private int capacity;
    private int collisionCount;
    private int rehashCount;

    public HashTable(){
        this.capacity = initial_capacity;
        this.buckets = (Node<K, V>[]) new Node[capacity];
        this.size = 0;
        this.collisionCount = 0;
        this.rehashCount = 0;
        this.shrinkCount = 0;
    }

    private int getBucketIndex(Object key){
        if(key == null) return 0;
        return key.hashCode() % capacity;
    }

    public void put(K key, V value){
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];

        // 1. Check if key already exists (UPDATE case)
        while(current != null){
            if((key == null && current.key == null) || (key != null && key.equals(current.key))){
                current.value = value;
                return;
            }
            current = current.next;
        }

        // 2. Key is new: Check if bucket is already occupied (COLLISION case)
        if (buckets[index] != null) {
            collisionCount++;
        }

        // 3. Insert new node at head of chain
        Node<K, V> newNode = new Node<>(key, value, buckets[index]);
        buckets[index] = newNode;
        size++;

        // 4. Rehash check when Load Factor exceeds 0.75
        if ((double) size / capacity > load_factor_threshold) {
            rehash(capacity * 2);
            rehashCount++;
        }
    }

    public V get(K key){
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];
        while(current  != null){
            if((key == null && current.key == null) || (key != null && key.equals(current.key))){
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public boolean remove(K key){
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while (current != null){
            if((key == null && current.key == null) || (key != null && key.equals(current.key))){
                if(prev == null){
                    buckets[index] = current.next;
                }else{
                    prev.next = current.next;
                }
                size--;

                if(capacity > initial_capacity && (double) size / capacity shrink_factor_threshold){
                    rehash(Math.max(initial_capacity, capacity/2));
                    shrinkCount++;
                }
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    private void rehash(int newCapacity) {
        Node<K, V>[] oldBuckets = buckets;
        this.capacity = newCapacity;
        this.buckets = (Node<K, V>[]) new Node[capacity];

        for (Node<K, V> head : oldBuckets) {
            Node<K, V> current = head;
            while (current != null) {
                Node<K, V> next = current.next;
                int newIndex = getBucketIndex(current.key);
                current.next = buckets[newIndex];
                buckets[newIndex] = current;
                current = next;
            }
        }
    }

    public int size(){
        return size;
    }

    public int getCollisionCount() {
        return collisionCount;
    }

    public int getRehashCount() {
        return rehashCount;
    }

    public int getShrinkCount() {
        return shrinkCount;
    }

    public void dislay(){
        System.out.println("HashTable (Size: " + size + ", Capacity: " + capacity + "):");
        for(int i = 0; i < capacity; i++){
            System.out.print("  Bucket " + i + ": ");
            Node<K, V> current = buckets[i];
            if (current == null) {
                System.out.println("[empty]");
            } else {
                while (current != null) {
                    System.out.print("[" + current.key + " => " + current.value + "] ");
                    current = current.next;
                }
                System.out.println();
            }
        }
    }
}