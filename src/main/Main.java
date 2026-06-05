// ============================================================
// Authors: [ALL TEAM MEMBERS — write everyone's name here]
// Module: System Integration
// File:   Main.java — Demonstrates the full system working together
// ============================================================

package main;

import user.*;
import order.*;
import delivery.*;
import route.*;
import search.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("  SmartFood Delivery & Order Management System");
        System.out.println("==============================================\n");

        // ─────────────────────────────────────────────
        // STEP 1: User & Restaurant Management (Person 1)
        // ─────────────────────────────────────────────
        System.out.println(">>> STEP 1: Setting up Users & Restaurants <<<\n");

        UserList users = new UserList();
        users.addUser(new User(101, "Ali", "KL"));
        users.addUser(new User(102, "Siti", "PJ"));
        users.addUser(new User(103, "Raj", "Shah Alam"));

        System.out.println("\nAll Users:");
        users.displayAll();

        RestaurantList restaurants = new RestaurantList();
        restaurants.addRestaurant(new Restaurant(201, "Mamak Corner", "KL"));
        restaurants.addRestaurant(new Restaurant(202, "CKT House", "PJ"));
        restaurants.addRestaurant(new Restaurant(203, "Nasi Campur Baru", "Shah Alam"));

        System.out.println("\nAll Restaurants:");
        restaurants.displayAll();

        System.out.println("\nSearching for User 102:");
        User foundUser = users.searchUser(102);
        if (foundUser != null) System.out.println("Found: " + foundUser);

        System.out.println("\nRemoving User 103:");
        users.removeUser(103);

        System.out.println("\nUsers after removal:");
        users.displayAll();


        // ─────────────────────────────────────────────
        // STEP 2: Order Processing (Person 2)
        // ─────────────────────────────────────────────
        System.out.println("\n>>> STEP 2: Processing Orders <<<\n");

        // Demo UndoStack (cart)
        UndoStack cart = new UndoStack();
        cart.push("Nasi Lemak");
        cart.push("Teh Tarik");
        cart.push("Roti Canai");

        System.out.println("Current Cart:");
        cart.displayCart();

        System.out.println("\nUndo Last Item:");
        String removedItem = cart.pop();
        System.out.println("Removed: " + removedItem);

        System.out.println("\nCart After Undo:");
        cart.displayCart();

        // Create Order Queue
        OrderQueue queue = new OrderQueue();

        Order order1 = new Order(1, 101, 201,
                new String[]{"Nasi Lemak", "Teh Tarik"}, 11.50, "Pending");
        Order order2 = new Order(2, 102, 202,
                new String[]{"Roti Canai"}, 3.00, "Pending");
        Order order3 = new Order(3, 101, 203,
                new String[]{"Mee Goreng", "Teh Tarik"}, 9.50, "Pending");

        queue.enqueue(order1);
        queue.enqueue(order2);
        queue.enqueue(order3);

        System.out.println("\nPending Orders:");
        queue.displayQueue();

        // Process next order
        System.out.println("\nProcessing Next Order:");
        Order nextOrder = queue.dequeue();
        System.out.println("Processed: " + nextOrder);

        System.out.println("\nRemaining Queue:");
        queue.displayQueue();


        // ─────────────────────────────────────────────
        // STEP 3: Delivery Rider Assignment (Person 3)
        // ─────────────────────────────────────────────
        System.out.println("\n>>> STEP 3: Assigning Delivery Rider <<<\n");

        DeliveryAssignment delivery = new DeliveryAssignment();
        delivery.insertRider(new Rider(1, "Abang A", 15, true));
        delivery.insertRider(new Rider(2, "Abang B", 10, true));
        delivery.insertRider(new Rider(3, "Abang C", 20, true));

        System.out.println("Available riders in heap: " + delivery.getAvailableRidersCount());

        System.out.println("\nAssigning best rider (lowest delivery time):");
        Rider assigned = delivery.assignRider();

        System.out.println("\nRemaining available riders: " + delivery.getAvailableRidersCount());


        // ─────────────────────────────────────────────
        // STEP 4: Route Optimization (Person 4)
        // ─────────────────────────────────────────────
        System.out.println("\n>>> STEP 4: Finding Shortest Delivery Route <<<\n");

        Graph graph = new Graph();
        graph.addRoute("Mamak Corner", "Junction 1", 4);
        graph.addRoute("Mamak Corner", "Junction 2", 2);
        graph.addRoute("Junction 1", "Junction 3", 5);
        graph.addRoute("Junction 2", "Junction 1", 1);
        graph.addRoute("Junction 2", "Junction 3", 8);
        graph.addRoute("Junction 3", "Ali House", 3);

        System.out.println("Delivery Route Map:");
        graph.displayGraph();

        System.out.println("Finding shortest route from Mamak Corner to Ali House:");
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.findShortestPath(graph, "Mamak Corner", "Ali House");


        // ─────────────────────────────────────────────
        // STEP 5: Search & Data Retrieval (Person 5)
        // ─────────────────────────────────────────────
        System.out.println("\n>>> STEP 5: Food Search & Fast Data Retrieval <<<\n");

        // Build BST with food items from restaurants
        FoodBST bst = new FoodBST();
        bst.insert(new FoodItem("Nasi Lemak", 8.50, "Malay", 201));
        bst.insert(new FoodItem("Roti Canai", 3.00, "Malay", 201));
        bst.insert(new FoodItem("Char Kuey Teow", 10.00, "Chinese", 202));
        bst.insert(new FoodItem("Teh Tarik", 2.50, "Drinks", 201));
        bst.insert(new FoodItem("Mee Goreng", 7.00, "Malay", 203));
        bst.insert(new FoodItem("Chicken Rice", 6.50, "Chinese", 202));

        // Show sorted menu
        bst.inOrderTraversal();

        // Search for a food item
        System.out.println("=== Customer searches for Nasi Lemak ===");
        bst.search("Nasi Lemak");

        System.out.println("\n=== Customer filters food RM3.00 - RM8.00 ===");
        bst.searchByPrice(3.00, 8.00);

        // Build HashMap with user and order data for fast O(1) lookup
        DataHashMap dataMap = new DataHashMap(16);
        dataMap.put("user_101", "Ali - KL - 012-3456789");
        dataMap.put("user_102", "Siti - PJ - 013-4567890");
        dataMap.put("order_1", "Order #1 - Nasi Lemak x2 - RM17.00");
        dataMap.put("order_2", "Order #2 - Roti Canai x1 - RM3.00");

        System.out.println("\n=== Fast data lookup via HashMap ===");
        System.out.println("Lookup user_101: " + dataMap.get("user_101"));
        System.out.println("Lookup order_1:  " + dataMap.get("order_1"));
        System.out.println("Contains user_102? " + dataMap.containsKey("user_102"));

        System.out.println("\nAll stored data:");
        dataMap.display();


        // ─────────────────────────────────────────────
        System.out.println("==============================================");
        System.out.println("  System Demo Complete!");
        System.out.println("==============================================");
    }
}
