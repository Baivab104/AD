import java.util.*;

class Graph {
    private final Map<Integer, List<Node>> adjList = new HashMap<>();

    static class Node implements Comparable<Node> {
        int vertex, weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    void addEdge(int src, int dest, int weight) {
        adjList.computeIfAbsent(src, k -> new ArrayList<>()).add(new Node(dest, weight));
        adjList.computeIfAbsent(dest, k -> new ArrayList<>()).add(new Node(src, weight));
    }

    void primMST() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<Integer, Integer> key = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        Set<Integer> inMST = new HashSet<>();

        for (Integer vertex : adjList.keySet()) {
            key.put(vertex, Integer.MAX_VALUE);
        }

        int start = adjList.keySet().iterator().next();
        pq.add(new Node(start, 0));
        key.put(start, 0);
        parent.put(start, -1);

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            inMST.add(u);

            for (Node neighbor : adjList.getOrDefault(u, new ArrayList<>())) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (!inMST.contains(v) && key.get(v) > weight) {
                    key.put(v, weight);
                    pq.add(new Node(v, weight));
                    parent.put(v, u);
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : parent.entrySet()) {
            if (entry.getValue() != -1) {
                System.out.println("Edge: " + entry.getValue() + " - " + entry.getKey());
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 6);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 9);
        graph.addEdge(3, 4, 7);

        graph.primMST();
    }
}
