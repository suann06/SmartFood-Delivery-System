// ============================================================
// Author: [Person 4 - HARRESH]
// Module: Route Optimization
// File:   Dijkstra.java — Shortest path algorithm
// ============================================================

package route;

import java.util.*;

public class Dijkstra {

    // TODO: Implement findShortestPath(Graph graph, String source, String destination)
    //
    // Steps:
    // 1. Create a distances map: HashMap<String, Integer> — set all to Integer.MAX_VALUE, source to 0
    // 2. Create a previous map: HashMap<String, String> — to track the path
    // 3. Create a visited set: HashSet<String>
    // 4. Use a PriorityQueue that sorts by distance (you CAN use java.util.PriorityQueue here)
    //
    // Algorithm:
    //   - Add source to the priority queue with distance 0
    //   - While queue is not empty:
    //       a. Poll the node with smallest distance
    //       b. If it's the destination, we're done
    //       c. If already visited, skip
    //       d. Mark as visited
    //       e. For each neighbor:
    //          - Calculate newDist = currentDist + edge weight
    //          - If newDist < known distance to neighbor, update it and add to queue
    //          - Also update previous[neighbor] = current
    //
    // 5. Reconstruct path using the previous map (backtrack from destination to source)
    // 6. Print the path and total distance
    //
    // Return: the shortest distance (int), and print the path


    // TODO: reconstructPath(HashMap<String, String> previous, String source, String destination)
    //       Backtrack from destination to source using the previous map
    //       Return a list of location names in order


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
