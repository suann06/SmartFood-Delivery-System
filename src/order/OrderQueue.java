// ============================================================
// Author: Su Ann
// Module: Order Processing System
// File: OrderQueue.java — FIFO Queue for processing orders
// ============================================================

package order;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderQueue {

    // Inner class Node
    private static class Node {
        Order order;
        Node next;

        Node(Order order) {
            this.order = order;
            this.next = null;
        }
    }

    // Variables
    private Node front;
    private Node rear;
    private int size;

    // Constructor
    public OrderQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Add order to rear of queue
    public void enqueue(Order order) {

        Node newNode = new Node(order);

        if (isEmpty()) {

            front = newNode;
            rear = newNode;

        } else {

            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    // Remove next order from queue
    public Order dequeue() {

        if (isEmpty()) {
            return null;
        }

        Order removedOrder = front.order;

        front = front.next;

        size--;

        if (front == null) {
            rear = null;
        }

        return removedOrder;
    }

    // View next order
    public Order peek() {

        if (isEmpty()) {
            return null;
        }

        return front.order;
    }

    // Check if queue is empty
    public boolean isEmpty() {

        return size == 0;
    }

    // Display queue
    public void displayQueue() {

        if (isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }

        System.out.println("Pending Orders:");

        Node current = front;

        while (current != null) {

            System.out.println(current.order);

            current = current.next;
        }
    }

    // Return queue size
    public int getSize() {
        return size;
    }

    // Testing
    /*public static void main(String[] args) {

        OrderQueue queue = new OrderQueue();

        queue.enqueue(
                new Order(
                        1,
                        101,
                        201,
                        new String[]{"Nasi Lemak"},
                        8.50,
                        "Pending"
                )
        );

        queue.enqueue(
                new Order(
                        2,
                        102,
                        202,
                        new String[]{"Roti Canai"},
                        3.00,
                        "Pending"
                )
        );

        System.out.println("=== Order Queue ===");
        queue.displayQueue();

        System.out.println("=== Processing next order ===");

        Order next = queue.dequeue();

        System.out.println("Processed: " + next);

        queue.displayQueue();
    }
    */
}