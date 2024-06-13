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

    void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        for (Integer vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int currentVertex = currentNode.vertex;

            if (visited.contains(currentVertex)) {
                continue;
            }
            visited.add(currentVertex);

            for (Node neighbor : adjList.getOrDefault(currentVertex, Collections.emptyList())) {
                if (!visited.contains(neighbor.vertex)) {
                    int newDist = distances.get(currentVertex) + neighbor.weight;
                    if (newDist < distances.get(neighbor.vertex)) {
                        distances.put(neighbor.vertex, newDist);
                        pq.add(new Node(neighbor.vertex, newDist));
                    }
                }
            }
        }

        distances.forEach((vertex, distance) -> {
            System.out.println("Distance from " + start + " to " + vertex + " is " + distance);
        });
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);

        graph.dijkstra(0);
    }
}
