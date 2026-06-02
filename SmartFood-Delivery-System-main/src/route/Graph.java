// ============================================================
// Author: [Person 4 - HARRESH]
// Module: Route Optimization
// File:   Graph.java — Weighted graph using adjacency list
// ============================================================

package route;

import java.util.*;

public class Graph {

    class Edge {
        String destination;
        int weight;

        Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public String getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return destination + " (" + weight + ")";
        }
    }

    // Adjacency list
    HashMap<String, List<Edge>> adjacencyList;

    // Constructor
    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Add a new location (vertex)
    public void addLocation(String name) {
        adjacencyList.putIfAbsent(name, new ArrayList<>());
    }

    // Add a bidirectional route (edge) between two locations
    public void addRoute(String from, String to, int weight) {
        // Add locations first if they don't exist yet
        addLocation(from);
        addLocation(to);

        // Add edge both ways (bidirectional)
        adjacencyList.get(from).add(new Edge(to, weight));
        adjacencyList.get(to).add(new Edge(from, weight));
    }

    // Return list of edges for a given location
    public List<Edge> getNeighbors(String location) {
        return adjacencyList.getOrDefault(location, new ArrayList<>());
    }

    // Return all location names
    public Set<String> getNodes() {
        return adjacencyList.keySet();
    }

    // Print the graph
    public void displayGraph() {
        for (String location : adjacencyList.keySet()) {
            System.out.println(location + " → " + adjacencyList.get(location));
        }
        


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
