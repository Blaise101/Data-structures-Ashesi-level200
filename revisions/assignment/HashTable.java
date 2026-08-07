public class HashTable<K, V>{
    public static class Node<K, V>{
        public K key;
        public V value;
        public Node<K, V> next;

        public  Node(K key, V value, Node<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int initial_capacity = 8;
    private double shrink_factor_threshold = 0.25;
    private double laod_factor_threshold = 0.75;

    private Node<K, V>[] buckets;
    private int size;
    private int capacity;
    private int collisionCount;
    private int rehashCount;
    private int shrinkCount;

    public HashTable(){
        this.buckets = (Node<K, V>[]) new Node[capacity];
        this.capacity = initial_capacity;
        this.size = 0;
        this.collisionCount = 0;
        this.shrinkCount = 0;
        this.rehashCount = 0;
    }

    private int getBucketIndex(Object key){
        if(key == null) return 0;
        return Math.floorMod(key.hashCode(), capacity)
    }

    
}