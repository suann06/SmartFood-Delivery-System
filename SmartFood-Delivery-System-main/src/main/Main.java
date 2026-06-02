// ============================================================
// Authors: [ALL TEAM MEMBERS — write everyone's name here]
// Module: System Integration
// File:   Main.java — Demonstrates the full system working together
// ============================================================

package main;

import order.*;

// TODO: Import all modules
// import user.*;
// import order.*;
// import delivery.*;
// import route.*;
// import search.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("  SmartFood Delivery & Order Management System");
        System.out.println("==============================================\n");

        // ─────────────────────────────────────────────
        // STEP 1: User & Restaurant Management (Person 1)
        // ─────────────────────────────────────────────
        System.out.println(">>> STEP 1: Setting up Users & Restaurants <<<\n");

        // TODO (Person 1): Create UserList, add sample users
        // TODO (Person 1): Create RestaurantList, add sample restaurants
        // TODO (Person 1): Display all users and restaurants
        // TODO (Person 1): Demo search and remove


        // ─────────────────────────────────────────────
        // STEP 2: Order Processing (Person 2)
        // ─────────────────────────────────────────────
        System.out.println("\n>>> STEP 2: Processing Orders <<<\n");

        // Demo UndoStack
        UndoStack cart = new UndoStack();

        cart.push("Nasi Lemak");
        cart.push("Teh Tarik");
        cart.push("Roti Canai");

        System.out.println("Current Cart:");
        cart.displayCart();

        System.out.println("\nUndo Last Item:");
        String removedItem = cart.pop();

        System.out.println("Removed: " + removedItem);

        cart.displayCart();


        // Create Order Queue
        OrderQueue queue = new OrderQueue();

        queue.enqueue(
                new Order(
                        1,
                        101,
                        201,
                        new String[]{"Nasi Lemak", "Teh Tarik"},
                        11.50,
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

        System.out.println("\nPending Orders:");
        queue.displayQueue();


        // Process next order
        System.out.println("\nProcessing Next Order:");

        Order nextOrder = queue.dequeue();

        System.out.println("Processed:");
        System.out.println(nextOrder);

        System.out.println("\nRemaining Queue:");

        queue.displayQueue();


        // ─────────────────────────────────────────────
        // STEP 3: Delivery Rider Assignment (Person 3)
        // ─────────────────────────────────────────────
        System.out.println("\n>>> STEP 3: Assigning Delivery Rider <<<\n");

        // TODO (Person 3): Add riders to DeliveryAssignment
        // TODO (Person 3): Assign best rider for the dequeued order


        // ─────────────────────────────────────────────
        // STEP 4: Route Optimization (Person 4)
        // ─────────────────────────────────────────────
        System.out.println("\n>>> STEP 4: Finding Shortest Delivery Route <<<\n");

        // TODO (Person 4): Build the graph with locations and routes
        // TODO (Person 4): Run Dijkstra from restaurant to customer
        // TODO (Person 4): Print the shortest path and distance


        // ─────────────────────────────────────────────
        // STEP 5: Search & Data Retrieval (Person 5)
        // ─────────────────────────────────────────────
        System.out.println("\n>>> STEP 5: Food Search & Fast Data Retrieval <<<\n");

        // TODO (Person 5): Build BST with food items, demo search and sorted display
        // TODO (Person 5): Build HashMap with user/order data, demo fast retrieval


        // ─────────────────────────────────────────────
        System.out.println("\n==============================================");
        System.out.println("  System Demo Complete!");
        System.out.println("==============================================");
    }
}
