// Author: Person 5
// Module: Search, Recommendation & Data Retrieval
// Data Structure: BST + HashMap

package search;

public class DataHashMap {

    private class Entry {
        String key;
        Object value;
        Entry next;

        Entry(String key, Object value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public DataHashMap(int capacity) {
        buckets = new Entry[capacity];
        size = 0;
    }

    // hash function: sum of character values mod bucket count
    private int hash(String key) {
        if (key == null) return 0;
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            hashValue += key.charAt(i);
        }
        return Math.abs(hashValue) % buckets.length;
    }

    public void put(String key, Object value) {
        int index = hash(key);
        Entry head = buckets[index];

        // update if key already exists
        Entry current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        // add new entry at head of chain
        Entry newEntry = new Entry(key, value);
        newEntry.next = head;
        buckets[index] = newEntry;
        size++;
    }

    public Object get(String key) {
        int index = hash(key);
        Entry current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) return current.value;
            current = current.next;
        }
        return null;
    }

    public Object remove(String key) {
        int index = hash(key);
        Entry current = buckets[index];
        Entry prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }

        System.out.println("Key \"" + key + "\" not found.");
        return null;
    }

    public boolean containsKey(String key) {
        int index = hash(key);
        Entry current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) return true;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void display() {
        System.out.println("=== HashMap Contents ===");
        System.out.println("Total entries: " + size + " | Bucket count: " + buckets.length);
        System.out.println();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                System.out.print("Bucket " + i + ": ");
                Entry current = buckets[i];
                while (current != null) {
                    System.out.print("[" + current.key + " -> " + current.value + "]");
                    if (current.next != null) System.out.print(" -> ");
                    current = current.next;
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DataHashMap map = new DataHashMap(16);

        map.put("user_101", "Ali - KL - 012-3456789");
        map.put("user_102", "Siti - PJ - 013-4567890");
        map.put("user_105", "Raj - Shah Alam - 014-5678901");
        map.put("order_001", "Order #1 - Nasi Lemak x2 - RM17.00");
        map.put("order_002", "Order #2 - Char Kuey Teow x1 - RM10.00");

        System.out.println("user_101: " + map.get("user_101"));
        System.out.println("order_001: " + map.get("order_001"));
        System.out.println("unknown: " + map.get("unknown_key"));

        System.out.println("Contains user_102? " + map.containsKey("user_102"));
        System.out.println("Contains user_999? " + map.containsKey("user_999"));

        map.put("user_101", "Ali - KL - 012-9999999 (updated)");
        System.out.println("user_101 after update: " + map.get("user_101"));

        map.remove("order_002");
        System.out.println("order_002 after remove: " + map.get("order_002"));

        map.display();
        System.out.println("Total entries: " + map.size());
    }
}