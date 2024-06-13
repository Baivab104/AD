import java.util.HashMap;
import java.util.Map;

public class SymbolTable<K, V> {
    private final Map<K, V> table;

    public SymbolTable() {
        table = new HashMap<>();
    }

    // Add a key-value pair to the symbol table
    public void put(K key, V value) {
        table.put(key, value);
    }

    // Retrieve the value associated with the key
    public V get(K key) {
        return table.get(key);
    }

    // Remove a key-value pair from the symbol table
    public V remove(K key) {
        return table.remove(key);
    }

    // Check if the symbol table contains a specific key
    public boolean contains(K key) {
        return table.containsKey(key);
    }

    // Check if the symbol table is empty
    public boolean isEmpty() {
        return table.isEmpty();
    }

    // Get the size of the symbol table
    public int size() {
        return table.size();
    }

    // Clear the symbol table
    public void clear() {
        table.clear();
    }

    public static void main(String[] args) {
        SymbolTable<String, Integer> symTable = new SymbolTable<>();

        // Add some key-value pairs
        symTable.put("apple", 5);
        symTable.put("banana", 2);
        symTable.put("orange", 8);

        // Retrieve and print values
        System.out.println("apple: " + symTable.get("apple"));
        System.out.println("banana: " + symTable.get("banana"));
        System.out.println("orange: " + symTable.get("orange"));

        // Check if key exists
        System.out.println("Contains 'banana': " + symTable.contains("banana"));

        // Remove a key-value pair
        symTable.remove("banana");
        System.out.println("Contains 'banana' after removal: " + symTable.contains("banana"));

        // Get the size of the symbol table
        System.out.println("Size: " + symTable.size());

        // Clear the symbol table
        symTable.clear();
        System.out.println("Size after clearing: " + symTable.size());
    }
}
