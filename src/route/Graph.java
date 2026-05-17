// ============================================================
// Author: [Person 4 - Write your name here]
// Module: Route Optimization
// File:   Graph.java — Weighted graph using adjacency list
// ============================================================

package route;

import java.util.*;

public class Graph {

    // TODO: Create an inner class 'Edge' with:
    //       - String destination
    //       - int weight (distance or time)
    //       - Constructor + toString()


    // TODO: Declare adjacency list:
    //       HashMap<String, List<Edge>> adjacencyList
    //       (It's okay to use java.util.HashMap here — focus is on the graph, not the map)


    // TODO: Constructor — initialize the adjacency list


    // --- Core Methods ---

    // TODO: addLocation(String name) — add a new vertex (key) with empty edge list

    // TODO: addRoute(String from, String to, int weight) — add bidirectional edge
    //       Add edge from→to AND to→from
    //       Handle: if location doesn't exist yet, add it first

    // TODO: getNeighbors(String location) — return list of edges for a location

    // TODO: getLocations() — return all location names (Set<String>)

    // TODO: displayGraph() — print each location and its connections
    //       Example output:
    //       Restaurant A → [Junction 1 (4), Junction 2 (2)]
    //       Junction 1 → [Restaurant A (4), Junction 3 (5)]


    // --- Testing ---
    /*
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addLocation("Restaurant A");
        graph.addLocation("Junction 1");
        graph.addLocation("Junction 2");
        graph.addLocation("Customer B");

        graph.addRoute("Restaurant A", "Junction 1", 4);
        graph.addRoute("Restaurant A", "Junction 2", 2);
        graph.addRoute("Junction 1", "Customer B", 5);
        graph.addRoute("Junction 2", "Customer B", 8);

        graph.displayGraph();
    }
    */
}
