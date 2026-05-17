// ============================================================
// Author: [Person 2 - Write your name here]
// Module: Order Processing System
// File:   OrderQueue.java — FIFO Queue for processing orders
// ============================================================

package order;

public class OrderQueue {

    // TODO: Create an inner class 'Node' with:
    //       - Order data
    //       - Node next


    // TODO: Declare front (Node), rear (Node), size (int)


    // TODO: Constructor — initialize front = null, rear = null, size = 0


    // --- Core Methods ---

    // TODO: enqueue(Order order) — add order to the rear of the queue
    //       If queue is empty, front and rear both point to new node

    // TODO: dequeue() — remove and return the order at the front
    //       Handle: empty queue case
    //       If queue becomes empty, set rear = null too

    // TODO: peek() — return front order without removing

    // TODO: isEmpty() — return true if size == 0

    // TODO: displayQueue() — print all orders from front to rear

    // TODO: getSize() — return current size


    // --- Testing ---
    /*
    public static void main(String[] args) {
        OrderQueue queue = new OrderQueue();
        queue.enqueue(new Order(1, 101, 201, new String[]{"Nasi Lemak"}, 8.50, "Pending"));
        queue.enqueue(new Order(2, 102, 202, new String[]{"Roti Canai"}, 3.00, "Pending"));
        System.out.println("=== Order Queue ===");
        queue.displayQueue();
        System.out.println("=== Processing next order ===");
        Order next = queue.dequeue();
        System.out.println("Processed: " + next);
        queue.displayQueue();
    }
    */
}
