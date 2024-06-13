import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Node class to store the character, frequency, and child nodes
class HuffmanNode {
    int frequency;
    char character;
    HuffmanNode left;
    HuffmanNode right;

    HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    HuffmanNode(int frequency) {
        this.character = '\0';
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
}

class HuffmanCoding {

    // Comparator class to compare nodes based on frequency
    static class NodeComparator implements Comparator<HuffmanNode> {
        public int compare(HuffmanNode node1, HuffmanNode node2) {
            return Integer.compare(node1.frequency, node2.frequency);
        }
    }

    // Method to generate Huffman codes
    public static Map<Character, String> generateHuffmanCodes(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(new NodeComparator());

        // Create a leaf node for each character and add it to the priority queue
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Build the Huffman tree
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            priorityQueue.add(parent);
        }

        HuffmanNode root = priorityQueue.poll();

        // Generate Huffman codes by traversing the tree
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodesRecursive(root, "", huffmanCodes);

        return huffmanCodes;
    }

    // Recursive method to generate codes
    private static void generateCodesRecursive(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, code);
        }
        generateCodesRecursive(node.left, code + "0", huffmanCodes);
        generateCodesRecursive(node.right, code + "1", huffmanCodes);
    }

    public static void main(String[] args) {
        // Example frequency map
        Map<Character, Integer> frequencyMap = new HashMap<>();
        frequencyMap.put('a', 5);
        frequencyMap.put('b', 9);
        frequencyMap.put('c', 12);
        frequencyMap.put('d', 13);
        frequencyMap.put('e', 16);
        frequencyMap.put('f', 45);

        // Generate Huffman codes
        Map<Character, String> huffmanCodes = generateHuffmanCodes(frequencyMap);

        // Print Huffman codes
        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
