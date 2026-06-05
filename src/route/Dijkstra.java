// ============================================================
// Author: [Person 4 - HARRESH]
// Module: Route Optimization
// File:   Dijkstra.java — Shortest path algorithm
// ============================================================

package route;

import java.util.*;

public class Dijkstra {

   public int findShortestPath(Graph graph, String source, String destination) {

        // Step 1: Distances map — all set to infinity, source = 0
        HashMap<String, Integer> distances = new HashMap<>();
        for (String node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        // Step 2: Previous map — to reconstruct path
        HashMap<String, String> previous = new HashMap<>();

        // Step 3: Visited set
        HashSet<String> visited = new HashSet<>();

        // Step 4: Priority queue — sorts by distance (smallest first)
        PriorityQueue<String[]> queue = new PriorityQueue<>(
            Comparator.comparingInt(a -> Integer.parseInt(a[1]))
        );
        queue.add(new String[]{source, "0"});

        // Algorithm
        while (!queue.isEmpty()) {

            // a. Poll node with smallest distance
            String[] current = queue.poll();
            String currentNode = current[0];
            int currentDist = Integer.parseInt(current[1]);

            // b. If destination reached, stop early
            if (currentNode.equals(destination)) break;

            // c. Skip if already visited
            if (visited.contains(currentNode)) continue;

            // d. Mark as visited
            visited.add(currentNode);

            // e. Explore each neighbor
            for (Graph.Edge edge : graph.getNeighbors(currentNode)) {
                String neighbor = edge.getDestination();
                int weight = edge.getWeight();

                if (visited.contains(neighbor)) continue;

                int newDist = currentDist + weight;

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, currentNode);
                    queue.add(new String[]{neighbor, String.valueOf(newDist)});
                }
            }
        }

        // Step 5 & 6: Reconstruct path and print results
        List<String> path = reconstructPath(previous, source, destination);

        if (path.isEmpty()) {
            System.out.println("No path found from " + source + " to " + destination);
            return -1;
        }

        System.out.println("Shortest path: " + String.join(" -> ", path));
        System.out.println("Total distance: " + distances.get(destination));

        return distances.get(destination);
    }

    public List<String> reconstructPath(HashMap<String, String> previous, String source, String destination) {
        List<String> path = new ArrayList<>();
        String current = destination;

        // Backtrack from destination to source using previous map
        while (current != null) {
            path.add(0, current);       // insert at front to maintain order
            if (current.equals(source)) break;
            current = previous.get(current);
        }

        // If path doesn't start with source, no valid path exists
        if (path.isEmpty() || !path.get(0).equals(source)) {
            return new ArrayList<>();
        }

        return path;
    }


    // --- Testing ---
    /*
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addLocation("Restaurant A");
        graph.addLocation("Junction 1");
        graph.addLocation("Junction 2");
        graph.addLocation("Junction 3");
        graph.addLocation("Customer B");

        graph.addRoute("Restaurant A", "Junction 1", 4);
        graph.addRoute("Restaurant A", "Junction 2", 2);
        graph.addRoute("Junction 1", "Junction 3", 5);
        graph.addRoute("Junction 2", "Junction 1", 1);
        graph.addRoute("Junction 2", "Junction 3", 8);
        graph.addRoute("Junction 3", "Customer B", 3);

        findShortestPath(graph, "Restaurant A", "Customer B");
        // Expected path: Restaurant A → Junction 2 → Junction 1 → Junction 3 → Customer B
        // Expected distance: 2 + 1 + 5 + 3 = 11
    }
    */
}
