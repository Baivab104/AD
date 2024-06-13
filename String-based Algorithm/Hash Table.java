import java.util.LinkedList;

public class HashTable<K, V> {
    // Node class to store key-value pairs
    private static class Node<K, V> {
        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final LinkedList<Node<K, V>>[] table;

    // Constructor to initialize the hash table
    public HashTable(int capacity) {
        this.capacity = capacity;
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Hash function to compute the index for a key
    private int hash(K key) {
        return key.hashCode() % capacity;
    }

    // Method to add a key-value pair to the hash table
    public void put(K key, V value) {
        int index = hash(key);
        LinkedList<Node<K, V>> bucket = table[index];
        
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value; // Update existing key
                return;
            }
        }
        bucket.add(new Node<>(key, value)); // Insert new key
    }

    // Method to retrieve the value associated with a key
    public V get(K key) {
        int index = hash(key);
        LinkedList<Node<K, V>> bucket = table[index];
        
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null; // Key not found
    }

    // Method to remove a key-value pair from the hash table
    public V remove(K key) {
        int index = hash(key);
        LinkedList<Node<K, V>> bucket = table[index];
        
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                V value = node.value;
                bucket.remove(node);
                return value;
            }
        }
        return null; // Key not found
    }

    // Method to check if the hash table contains a specific key
    public boolean contains(K key) {
        int index = hash(key);
        LinkedList<Node<K, V>> bucket = table[index];
        
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the hash table is empty
    public boolean isEmpty() {
        for (LinkedList<Node<K, V>> bucket : table) {
            if (!bucket.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Method to get the size of the hash table
    public int size() {
        int size = 0;
        for (LinkedList<Node<K, V>> bucket : table) {
            size += bucket.size();
        }
        return size;
    }

    // Method to clear the hash table
    public void clear() {
        for (LinkedList<Node<K, V>> bucket : table) {
            bucket.clear();
        }
    }

    // Main method to test the hash table implementation
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>(10);

        // Add some key-value pairs
        hashTable.put("apple", 5);
        hashTable.put("banana", 2);
        hashTable.put("orange", 8);

        // Retrieve and print values
        System.out.println("apple: " + hashTable.get("apple"));
        System.out.println("banana: " + hashTable.get("banana"));
        System.out.println("orange: " + hashTable.get("orange"));

        // Check if key exists
        System.out.println("Contains 'banana': " + hashTable.contains("banana"));

        // Remove a key-value pair
        hashTable.remove("banana");
        System.out.println("Contains 'banana' after removal: " + hashTable.contains("banana"));

        // Get the size of the hash table
        System.out.println("Size: " + hashTable.size());

        // Clear the hash table
        hashTable.clear();
        System.out.println("Size after clearing: " + hashTable.size());
    }
}
