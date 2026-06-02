# 🔍 Person 5 — Search, Recommendation & Data Retrieval

> **Data Structures:** BST (Binary Search Tree) + HashMap  
> **Marks:** 15 (BST) + 10 (HashMap) = 25 total  
> **Files:** `src/search/FoodItem.java`, `src/search/FoodBST.java`, `src/search/DataHashMap.java`

---

## 🎯 Your Goal

Two tasks:

1. **Search & Recommendation (BST):** Let users search food items and view them sorted — using a Binary Search Tree
2. **Data Retrieval (HashMap):** Provide fast O(1) access to user profiles and order details — using a Hash Table

---

## 📝 What You Need to Implement

### `FoodItem.java` — Data model for a food item
- Fields: `name`, `price`, `category`, `restaurantId`
- Constructor + getters/setters
- `toString()` for display

### `FoodBST.java` — Binary Search Tree for food items
- Tree node inner class: `FoodItem data`, `left`, `right`
- `insert(FoodItem item)` — insert based on price (or name alphabetically)
- `search(String name)` — find a food item by name
- `searchByPrice(double minPrice, double maxPrice)` — range search
- `inOrderTraversal()` — display all items sorted (ascending)
- `reverseInOrder()` — display all items sorted (descending, for "most expensive first")
- `delete(String name)` — remove a food item

### `DataHashMap.java` — Hash Table for fast data retrieval
- **Do NOT use `java.util.HashMap`** — build your own
- Internal array of buckets (use chaining with linked lists for collision handling)
- `put(String key, Object value)` — store data (e.g., key = "user_101", value = User object)
- `get(String key)` — retrieve data by key
- `remove(String key)` — delete an entry
- `containsKey(String key)` — check if key exists
- `display()` — show all key-value pairs
- `hash(String key)` — your hash function

---

## 💡 Why BST? Why HashMap?

### BST Explanation (for report):

| Operation | Unsorted List | BST (balanced) |
|-----------|--------------|----------------|
| Search | O(n) | O(log n) |
| Insert | O(1) | O(log n) |
| Sorted display | O(n log n) sort needed | O(n) in-order traversal |
| Range search | O(n) | O(log n + k) |

**Key argument:** A BST keeps food items naturally sorted. In-order traversal gives sorted results without needing a separate sort step. Search is O(log n) on average, much faster than scanning an entire list.

### HashMap Explanation (for report):

| Operation | Linked List | HashMap |
|-----------|------------|---------|
| Access by key | O(n) | O(1) average |
| Insert | O(1) | O(1) average |
| Delete | O(n) | O(1) average |

**Key argument:** When you know a user ID or order ID, a HashMap gives you instant access without scanning. This is critical for a delivery system where speed matters.

---

## 🧪 How to Test Your Module

```java
public static void main(String[] args) {
    // Test BST
    FoodBST bst = new FoodBST();
    bst.insert(new FoodItem("Nasi Lemak", 8.50, "Malay", 201));
    bst.insert(new FoodItem("Roti Canai", 3.00, "Malay", 201));
    bst.insert(new FoodItem("Char Kuey Teow", 10.00, "Chinese", 202));
    bst.insert(new FoodItem("Teh Tarik", 2.50, "Drinks", 201));

    System.out.println("=== All items sorted by price ===");
    bst.inOrderTraversal();

    System.out.println("=== Search for Nasi Lemak ===");
    bst.search("Nasi Lemak");

    // Test HashMap
    DataHashMap map = new DataHashMap(16);
    map.put("user_101", "Ali - KL - 012-345");
    map.put("user_102", "Siti - PJ - 013-456");
    map.put("order_001", "Order #1 - Nasi Lemak x2 - RM17.00");

    System.out.println(map.get("user_101"));   // Fast O(1) access
    System.out.println(map.get("order_001"));
    map.display();
}
```

---

## 📊 Diagrams You Need for the Report

**BST Diagram (sorted by price):**
```
              [8.50 Nasi Lemak]
             /                 \
    [3.00 Roti Canai]    [10.00 Char Kuey Teow]
        /
 [2.50 Teh Tarik]
```

In-order traversal gives: 2.50 → 3.00 → 8.50 → 10.00 (sorted!)

**HashMap Diagram (with chaining):**
```
Bucket 0: → [user_101 | Ali...] → null
Bucket 1: → null
Bucket 2: → [order_001 | Order #1...] → null
Bucket 3: → [user_102 | Siti...] → [user_105 | Raj...] → null  (collision!)
...
```

---

## 🔗 How Your Module Connects to Others

- **Person 1** provides `User` and `Restaurant` objects → your HashMap stores them for fast retrieval
- **Person 2** creates `Order` objects → your HashMap can store order details
- **BST** is used when customers browse the menu / search for food
- In `Main.java`: search food (BST) → place order → retrieve user data (HashMap)
