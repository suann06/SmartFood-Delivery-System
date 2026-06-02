# 🗺️ Person 4 — Route Optimization

> **Data Structure:** Graph + Dijkstra's Algorithm  
> **Marks:** 20 (highest weight!)  
> **Files:** `src/route/Location.java`, `src/route/Graph.java`, `src/route/Dijkstra.java`

---

## 🎯 Your Goal

Build a system that finds the **shortest route** between a restaurant and a customer.  
Represent locations as a **weighted graph** and implement **Dijkstra's algorithm** to find the shortest path.

---

## 📝 What You Need to Implement

### `Location.java` — Data model for a location node
- Fields: `locationId`, `name` (e.g., "Restaurant A", "Junction 1", "Customer B")
- Constructor + getters/setters
- `toString()` for display

### `Graph.java` — Weighted graph using adjacency list
- Use a `HashMap<String, List<Edge>>` or a 2D array (adjacency matrix)
- Inner class or separate `Edge` class: `destination`, `weight` (distance/time)
- `addLocation(String name)` — add a vertex
- `addRoute(String from, String to, int weight)` — add an edge (bidirectional)
- `displayGraph()` — print all locations and their connections
- `getNeighbors(String location)` — return adjacent nodes

### `Dijkstra.java` — Shortest path algorithm
- `findShortestPath(Graph graph, String source, String destination)`
- Should return:
  - The shortest distance (int)
  - The actual path (list of location names)
- Use a priority queue (you can use `java.util.PriorityQueue` here since the focus is on the graph algorithm)

---

## 💡 Why Graph + Dijkstra?

Explain in your report:

**Why a Graph?**
- Locations and roads form a natural graph: locations = nodes, roads = edges
- Weighted edges represent distances or travel times
- Adjacency list is space-efficient: O(V + E) vs O(V²) for adjacency matrix

**Why Dijkstra's Algorithm?**
- Finds the shortest path from one node to all others
- Time complexity: O((V + E) log V) with a priority queue
- Works for weighted graphs with non-negative weights
- Better than brute force which would check all possible paths (exponential)

**How Dijkstra Works (brief explanation for report):**
1. Start at source, set its distance to 0, all others to infinity
2. Visit the unvisited node with the smallest distance
3. For each neighbor, calculate distance through current node
4. If shorter than known distance, update it
5. Repeat until destination is reached

---

## 🧪 How to Test Your Module

```java
public static void main(String[] args) {
    Graph graph = new Graph();

    // Add locations
    graph.addLocation("Restaurant A");
    graph.addLocation("Junction 1");
    graph.addLocation("Junction 2");
    graph.addLocation("Junction 3");
    graph.addLocation("Customer B");

    // Add routes with distances (km or minutes)
    graph.addRoute("Restaurant A", "Junction 1", 4);
    graph.addRoute("Restaurant A", "Junction 2", 2);
    graph.addRoute("Junction 1", "Junction 3", 5);
    graph.addRoute("Junction 2", "Junction 1", 1);
    graph.addRoute("Junction 2", "Junction 3", 8);
    graph.addRoute("Junction 3", "Customer B", 3);

    graph.displayGraph();

    // Find shortest path
    Dijkstra.findShortestPath(graph, "Restaurant A", "Customer B");
    // Expected: Restaurant A → Junction 2 → Junction 1 → Junction 3 → Customer B
    // Distance: 2 + 1 + 5 + 3 = 11
}
```

---

## 📊 Diagram You Need for the Report

**Graph diagram with weights:**
```
Restaurant A ---4--- Junction 1
    |                    |
    2                    5
    |                    |
Junction 2 ---8--- Junction 3 ---3--- Customer B
    |                    
    +--------1-----------+
       (Junction 2 → Junction 1)
```

Also show a **step-by-step trace** of Dijkstra's algorithm with a distance table:

| Step | Visited | Distances: A | J1 | J2 | J3 | B |
|------|---------|----|----|----|----|---|
| 0 | {} | 0 | ∞ | ∞ | ∞ | ∞ |
| 1 | {A} | 0 | 4 | 2 | ∞ | ∞ |
| 2 | {A,J2} | 0 | 3 | 2 | 10 | ∞ |
| ... | ... | ... | ... | ... | ... | ... |

---

## 🔗 How Your Module Connects to Others

- **Person 3** (Delivery) assigns a rider → your module then finds the best route for that rider
- **Person 1** provides restaurant/customer locations
- In `Main.java`: after rider is assigned, call `findShortestPath(restaurantLocation, customerLocation)`
