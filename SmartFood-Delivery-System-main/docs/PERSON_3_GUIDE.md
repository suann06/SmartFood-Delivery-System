# 🏍️ Person 3 — Delivery Rider Assignment

> **Data Structure:** Priority Queue (Min Heap)  
> **Marks:** 15  
> **Files:** `src/delivery/Rider.java`, `src/delivery/MinHeap.java`, `src/delivery/DeliveryAssignment.java`

---

## 🎯 Your Goal

Build a system that assigns the **best delivery rider** to each order.  
"Best" = shortest estimated delivery time (or nearest distance).  
Use a **Min Heap** so the rider with the lowest time is always picked first.

---

## 📝 What You Need to Implement

### `Rider.java` — Data model for a rider
- Fields: `riderId`, `name`, `estimatedTime` (in minutes), `isAvailable`
- Constructor + getters/setters
- `toString()` for display

### `MinHeap.java` — Manual Min Heap implementation
- **Do NOT use `java.util.PriorityQueue`** — build it yourself
- Internal array to store `Rider` objects
- `insert(Rider rider)` — add rider and bubble up
- `extractMin()` — remove and return the rider with shortest time, then heapify down
- `peek()` — view the best rider without removing
- `size()` and `isEmpty()`
- Helper methods: `bubbleUp(int index)`, `heapifyDown(int index)`, `swap(int i, int j)`, `parent(int i)`, `leftChild(int i)`, `rightChild(int i)`

### `DeliveryAssignment.java` — Assigns riders to orders
- `assignRider()` — extract the best rider from the heap
- `addRider(Rider rider)` — add a new available rider
- `displayAvailableRiders()` — show all riders in heap

---

## 💡 Why Min Heap? (Compare with Linear Search)

This is a key part of your marks — you must explain:

| Operation | Linear Search (unsorted list) | Min Heap |
|-----------|-------------------------------|----------|
| Find best rider | O(n) — scan all riders | O(1) — peek at root |
| Remove best rider | O(n) — find + shift | O(log n) — extract + heapify |
| Add new rider | O(1) — append | O(log n) — insert + bubble up |

**Key argument:** When there are many riders, a Min Heap is significantly faster for repeatedly finding and removing the best option. Linear search scans the entire list every time, while a heap maintains order automatically.

---

## 🧪 How to Test Your Module

```java
public static void main(String[] args) {
    MinHeap heap = new MinHeap(10);
    heap.insert(new Rider(1, "Ahmad", 15, true));   // 15 min
    heap.insert(new Rider(2, "Mei Ling", 8, true));  // 8 min
    heap.insert(new Rider(3, "Raj", 12, true));       // 12 min

    System.out.println("Best rider: " + heap.peek());
    // Should print Mei Ling (8 min)

    Rider assigned = heap.extractMin();
    System.out.println("Assigned: " + assigned);
    // Mei Ling is removed, next best is Raj (12 min)

    System.out.println("Next best: " + heap.peek());
}
```

---

## 📊 Diagram You Need for the Report

**Min Heap as a tree:**
```
           [8 min]
          /       \
     [12 min]   [15 min]
```

After extracting 8 min:
```
          [12 min]
          /
     [15 min]
```

Also show the **array representation**: `[8, 12, 15]` → parent at `i`, children at `2i+1` and `2i+2`.

---

## 🔗 How Your Module Connects to Others

- **Person 2** (Orders) dequeues an order → your module assigns the best rider
- **Person 4** (Route) then finds the shortest delivery path for that rider
- In `Main.java`: dequeue order → `assignRider()` → find route
