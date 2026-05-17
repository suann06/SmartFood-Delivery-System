// ============================================================
// Author: [Person 3 - Write your name here]
// Module: Delivery Rider Assignment
// File:   MinHeap.java — Min Heap for selecting best rider
// ============================================================

package delivery;

public class MinHeap {

    // TODO: Declare:
    //       - Rider[] heap (array to store riders)
    //       - int size (current number of elements)
    //       - int capacity (max size of array)


    // TODO: Constructor — initialize array with given capacity, size = 0


    // --- Helper Methods ---

    // TODO: parent(int i) — return index of parent: (i - 1) / 2

    // TODO: leftChild(int i) — return index of left child: 2 * i + 1

    // TODO: rightChild(int i) — return index of right child: 2 * i + 2

    // TODO: swap(int i, int j) — swap two elements in the array


    // --- Core Methods ---

    // TODO: insert(Rider rider) — add rider to end, then bubbleUp
    //       Handle: heap full case

    // TODO: bubbleUp(int index) — move element up until heap property is restored
    //       While index > 0 and heap[index].estimatedTime < heap[parent].estimatedTime → swap and move up

    // TODO: extractMin() — remove and return root (best rider), replace with last, then heapifyDown
    //       Handle: empty heap case

    // TODO: heapifyDown(int index) — move element down until heap property is restored
    //       Find smallest among node and its children, swap if needed, repeat

    // TODO: peek() — return root rider without removing

    // TODO: size() — return current size

    // TODO: isEmpty() — return true if size == 0


    // --- Testing ---
    /*
    public static void main(String[] args) {
        MinHeap heap = new MinHeap(10);
        heap.insert(new Rider(1, "Ahmad", 15, true));
        heap.insert(new Rider(2, "Mei Ling", 8, true));
        heap.insert(new Rider(3, "Raj", 12, true));

        System.out.println("Best rider: " + heap.peek());        // Mei Ling (8 min)
        System.out.println("Assigned: " + heap.extractMin());     // Mei Ling removed
        System.out.println("Next best: " + heap.peek());          // Raj (12 min)
    }
    */
}
