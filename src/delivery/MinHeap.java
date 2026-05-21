// ============================================================
// Author: [Person 3 - Koh Pei Yin]
// Module: Delivery Rider Assignment
// File:   MinHeap.java — Priority queue logic for optimal rider selection
// ============================================================

package delivery;

import java.util.ArrayList;

public class MinHeap {

    //Instance Variables
    private ArrayList<Rider> heap;

    //Constructor
    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    //Helper Methods for Index Tree Math
    private int getParentIndex(int i) { 
        return (i - 1) / 2; 
    }
    private int getLeftChildIndex(int i) { 
        return (2 * i) + 1; 
    }
    private int getRightChildIndex(int i) { 
        return (2 * i) + 2; 
    }

    //Core Heap Operations
    //Insert Rider (Heapify Up)
    public void insertRider(Rider rider) {
        heap.add(rider);
        int current = heap.size() - 1;

        // Bubble up if the newly inserted rider has a lower estimated time than its parent
        while (current > 0 && heap.get(current).getEstimatedTime() < heap.get(getParentIndex(current)).getEstimatedTime()) {
            swap(current, getParentIndex(current));
            current = getParentIndex(current);
        }
    }

    //Remove Minimum Rider (Extract Top Priority)
    public Rider removeMin() {
        if (heap.isEmpty()) {
            return null;
        }
        if (heap.size() == 1) {
            return heap.remove(0);
        }

        // Keep a reference to the root (fastest rider) to return at the end
        Rider rootRider = heap.get(0);
        
        // Move the last element to the root position
        heap.set(0, heap.remove(heap.size() - 1));
        
        // Call heapify on the root element to push it back down to its correct spot
        heapify(0);

        return rootRider;
    }

    //Heapify (Heapify Down)
    public void heapify(int index) {
        int smallest = index;
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);

        // Check if the left child exists and has a lower estimated delivery time
        if (left < heap.size() && heap.get(left).getEstimatedTime() < heap.get(smallest).getEstimatedTime()) {
            smallest = left;
        }

        // Check if the right child exists and has a lower estimated delivery time
        if (right < heap.size() && heap.get(right).getEstimatedTime() < heap.get(smallest).getEstimatedTime()) {
            smallest = right;
        }

        // If the smallest element is not the current element, swap them and continue cascading down
        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    //Additional Utility Methods
    //Element Swapper
    private void swap(int i, int j) {
        Rider temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    //Check if Empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    //Get Size
    public int size() {
        return heap.size();
    }
}