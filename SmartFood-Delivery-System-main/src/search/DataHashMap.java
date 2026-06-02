// ============================================================
// Author: [Person 5 - Write your name here]
// Module: Data Retrieval Optimization
// File:   DataHashMap.java — Custom Hash Table for fast data access
// ============================================================

package search;

public class DataHashMap {

    // TODO: Create an inner class 'Entry' with:
    //       - String key
    //       - Object value (or String value — your choice)
    //       - Entry next (for chaining on collision)
    //       - Constructor


    // TODO: Declare:
    //       - Entry[] buckets (the array of buckets)
    //       - int capacity (size of array)
    //       - int size (number of entries stored)


    // TODO: Constructor — initialize buckets array with given capacity, size = 0


    // --- Hash Function ---

    // TODO: hash(String key) — convert key to bucket index
    //       Simple approach: sum of all character ASCII values, then % capacity
    //       Better approach: use key.hashCode() & 0x7fffffff % capacity
    //       Return: int (index between 0 and capacity-1)


    // --- Core Methods ---

    // TODO: put(String key, Object value) — insert or update a key-value pair
    //       1. Compute bucket index using hash()
    //       2. Check if key already exists in that bucket's chain → update value
    //       3. If not, add new Entry at the head of the chain
    //       4. Increment size

    // TODO: get(String key) — retrieve value by key
    //       1. Compute bucket index
    //       2. Walk the chain to find matching key
    //       3. Return value if found, null otherwise

    // TODO: remove(String key) — delete entry by key
    //       Handle: removing head of chain vs middle/end of chain

    // TODO: containsKey(String key) — return true if key exists

    // TODO: display() — print all key-value pairs across all buckets
    //       Show which bucket each entry is in (helps visualize distribution)

    // TODO: getSize() — return number of entries


    // --- Testing ---
    /*
    public static void main(String[] args) {
        DataHashMap map = new DataHashMap(16);

        // Store user profiles
        map.put("user_101", "Ali - KL - 012-345");
        map.put("user_102", "Siti - PJ - 013-456");
        map.put("user_103", "Raj - Shah Alam - 014-567");

        // Store order details
        map.put("order_001", "Nasi Lemak x2 - RM17.00");
        map.put("order_002", "Roti Canai x3 - RM9.00");

        // Fast retrieval
        System.out.println("=== Get user_101 ===");
        System.out.println(map.get("user_101"));   // O(1) access!

        System.out.println("=== Get order_001 ===");
        System.out.println(map.get("order_001"));

        System.out.println("=== Contains user_999? ===");
        System.out.println(map.containsKey("user_999"));  // false

        System.out.println("=== All Entries ===");
        map.display();

        System.out.println("=== Remove user_102 ===");
        map.remove("user_102");
        map.display();
    }
    */
}
