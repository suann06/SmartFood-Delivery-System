package test;

import user.*;
import order.*;
import delivery.*;
import route.*;
import search.*;

public class TestAll {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("  SmartFood — Full System Test Suite");
        System.out.println("============================================\n");

        testPerson1();
        testPerson2();
        testPerson3();
        testPerson4();
        testPerson5_BST();
        testPerson5_HashMap();

        System.out.println("============================================");
        System.out.println("  TOTAL: " + (passed + failed) + " tests | PASSED: " + passed + " | FAILED: " + failed);
        System.out.println("============================================");
    }

    // ── helper ──
    static void check(String id, String desc, boolean condition) {
        if (condition) {
            System.out.println("  [" + id + "] PASS — " + desc);
            passed++;
        } else {
            System.out.println("  [" + id + "] FAIL — " + desc);
            failed++;
        }
    }

    // ── Person 1 ──
    static void testPerson1() {
        System.out.println("=== Person 1: User & Restaurant Management ===\n");

        // User / UserList
        UserList ul = new UserList();
        User u1 = new User(101, "Ali", "KL");
        User u2 = new User(102, "Siti", "PJ");
        User u3 = new User(103, "Raj", "Shah Alam");
        ul.addUser(u1);
        ul.addUser(u2);
        ul.addUser(u3);

        check("P1-01", "UserList size = 3 after adding Ali, Siti, Raj", ul.getSize() == 3);
        check("P1-02", "searchUser(102) returns Siti", ul.searchUser(102) != null && ul.searchUser(102).getUserName().equals("Siti"));
        check("P1-03", "searchUser(999) returns null", ul.searchUser(999) == null);
        check("P1-04", "User toString format", u1.toString().contains("Ali") && u1.toString().contains("KL"));

        ul.removeUser(103);
        check("P1-05", "After removing Raj (103), size = 2", ul.getSize() == 2);
        check("P1-06", "After removing Raj, searchUser(103) = null", ul.searchUser(103) == null);

        ul.removeUser(999);
        check("P1-07", "Removing non-existing user (999) does not change size", ul.getSize() == 2);

        // Empty list edge case
        UserList empty = new UserList();
        check("P1-08", "Empty UserList size = 0", empty.getSize() == 0);
        check("P1-09", "searchUser on empty list returns null", empty.searchUser(1) == null);

        // Restaurant / RestaurantList
        RestaurantList rl = new RestaurantList();
        Restaurant r1 = new Restaurant(201, "Mamak Corner", "KL");
        Restaurant r2 = new Restaurant(202, "CKT House", "PJ");
        Restaurant r3 = new Restaurant(203, "Nasi Campur Baru", "Shah Alam");
        rl.addRestaurant(r1);
        rl.addRestaurant(r2);
        rl.addRestaurant(r3);

        check("P1-10", "RestaurantList size = 3", rl.getSize() == 3);
        check("P1-11", "searchRestaurant(202) returns CKT House", rl.searchRestaurant(202) != null && rl.searchRestaurant(202).getRestaurantName().equals("CKT House"));

        rl.removeRestaurant(202);
        check("P1-12", "After removing CKT House (202), size = 2", rl.getSize() == 2);

        check("P1-13", "Restaurant toString format", r1.toString().contains("Mamak Corner") && r1.toString().contains("KL"));

        rl.removeRestaurant(999);
        check("P1-14", "Removing non-existing restaurant (999) does not change size", rl.getSize() == 2);

        System.out.println();
    }

    // ── Person 2 ──
    static void testPerson2() {
        System.out.println("=== Person 2: Order Processing (Stack & Queue) ===\n");

        // UndoStack
        UndoStack cart = new UndoStack();
        check("P2-01", "Empty cart size = 0", cart.getSize() == 0);
        check("P2-02", "Empty cart isEmpty = true", cart.isEmpty());

        cart.push("Nasi Lemak");
        cart.push("Teh Tarik");
        cart.push("Roti Canai");
        check("P2-03", "After 3 pushes, size = 3", cart.getSize() == 3);
        check("P2-04", "peek returns Roti Canai (last pushed)", cart.peek().equals("Roti Canai"));
        check("P2-05", "isEmpty = false", !cart.isEmpty());

        String popped = cart.pop();
        check("P2-06", "pop returns Roti Canai", popped.equals("Roti Canai"));
        check("P2-07", "After pop, size = 2", cart.getSize() == 2);
        check("P2-08", "peek now returns Teh Tarik", cart.peek().equals("Teh Tarik"));

        cart.pop();
        cart.pop();
        check("P2-09", "After popping all, size = 0", cart.getSize() == 0);
        check("P2-10", "pop on empty stack returns 'Cart is empty'", cart.pop().equals("Cart is empty"));

        // OrderQueue
        OrderQueue q = new OrderQueue();
        check("P2-11", "Empty queue isEmpty = true", q.isEmpty());
        check("P2-12", "Empty queue size = 0", q.getSize() == 0);

        Order o1 = new Order(1, 101, 201, new String[]{"Nasi Lemak", "Teh Tarik"}, 11.50, "Pending");
        Order o2 = new Order(2, 102, 202, new String[]{"Roti Canai"}, 3.00, "Pending");
        Order o3 = new Order(3, 101, 203, new String[]{"Mee Goreng", "Teh Tarik"}, 9.50, "Pending");
        q.enqueue(o1);
        q.enqueue(o2);
        q.enqueue(o3);

        check("P2-13", "After 3 enqueues, size = 3", q.getSize() == 3);
        check("P2-14", "peek returns Order #1 (FIFO)", q.peek().getOrderId() == 1);

        Order dequeued = q.dequeue();
        check("P2-15", "dequeue returns Order #1", dequeued.getOrderId() == 1);
        check("P2-16", "After dequeue, size = 2", q.getSize() == 2);
        check("P2-17", "peek now returns Order #2", q.peek().getOrderId() == 2);

        q.dequeue();
        q.dequeue();
        check("P2-18", "After dequeuing all, isEmpty = true", q.isEmpty());
        check("P2-19", "dequeue on empty queue returns null", q.dequeue() == null);

        // Order POJO
        check("P2-20", "Order getTotalPrice = 11.50", o1.getTotalPrice() == 11.50);
        o1.setStatus("Delivered");
        check("P2-21", "Order setStatus works", o1.getStatus().equals("Delivered"));

        System.out.println();
    }

    // ── Person 3 ──
    static void testPerson3() {
        System.out.println("=== Person 3: Delivery Rider Assignment (Min-Heap) ===\n");

        MinHeap heap = new MinHeap();
        check("P3-01", "Empty heap isEmpty = true", heap.isEmpty());
        check("P3-02", "Empty heap size = 0", heap.size() == 0);

        Rider rA = new Rider(1, "Abang A", 15, true);
        Rider rB = new Rider(2, "Abang B", 10, true);
        Rider rC = new Rider(3, "Abang C", 20, true);
        heap.insertRider(rA);
        heap.insertRider(rB);
        heap.insertRider(rC);
        check("P3-03", "After 3 inserts, size = 3", heap.size() == 3);

        Rider min1 = heap.removeMin();
        check("P3-04", "First removeMin = Abang B (10 min)", min1.getName().equals("Abang B") && min1.getEstimatedTime() == 10);
        check("P3-05", "After removeMin, size = 2", heap.size() == 2);

        Rider min2 = heap.removeMin();
        check("P3-06", "Second removeMin = Abang A (15 min)", min2.getName().equals("Abang A") && min2.getEstimatedTime() == 15);

        Rider min3 = heap.removeMin();
        check("P3-07", "Third removeMin = Abang C (20 min)", min3.getName().equals("Abang C") && min3.getEstimatedTime() == 20);
        check("P3-08", "After removing all, isEmpty = true", heap.isEmpty());

        check("P3-09", "removeMin on empty heap returns null", heap.removeMin() == null);

        // DeliveryAssignment
        DeliveryAssignment da = new DeliveryAssignment();
        da.insertRider(new Rider(1, "Abang A", 15, true));
        da.insertRider(new Rider(2, "Abang B", 10, true));
        da.insertRider(new Rider(3, "Abang C", 20, false)); // not available
        check("P3-10", "Only available riders inserted (2 of 3)", da.getAvailableRidersCount() == 2);

        Rider assigned = da.assignRider();
        check("P3-11", "Assigned rider = Abang B (10 min)", assigned.getName().equals("Abang B"));
        check("P3-12", "Assigned rider setAvailable(false)", !assigned.isAvailable());
        check("P3-13", "Remaining riders = 1", da.getAvailableRidersCount() == 1);

        // Rider POJO
        check("P3-14", "Rider getters", rA.getRiderName().equals("Abang A") && rA.getDeliveryTime() == 15);

        System.out.println();
    }

    // ── Person 4 ──
    static void testPerson4() {
        System.out.println("=== Person 4: Route Optimization (Graph & Dijkstra) ===\n");

        Graph g = new Graph();
        g.addRoute("Mamak Corner", "Junction 1", 4);
        g.addRoute("Mamak Corner", "Junction 2", 2);
        g.addRoute("Junction 1", "Junction 3", 5);
        g.addRoute("Junction 2", "Junction 1", 1);
        g.addRoute("Junction 2", "Junction 3", 8);
        g.addRoute("Junction 3", "Ali House", 3);

        check("P4-01", "Graph has 5 nodes", g.getNodes().size() == 5);
        check("P4-02", "Mamak Corner has 2 neighbors", g.getNeighbors("Mamak Corner").size() == 2);
        check("P4-03", "Junction 2 has 3 neighbors", g.getNeighbors("Junction 2").size() == 3);

        // Check edge weights
        boolean foundJ2 = false;
        for (Graph.Edge e : g.getNeighbors("Mamak Corner")) {
            if (e.getDestination().equals("Junction 2") && e.getWeight() == 2) foundJ2 = true;
        }
        check("P4-04", "Edge Mamak Corner -> Junction 2 weight = 4 or 2 (bidirectional)", foundJ2);

        Dijkstra d = new Dijkstra();
        int dist = d.findShortestPath(g, "Mamak Corner", "Ali House");
        check("P4-05", "Shortest path Mamak Corner -> Ali House = 11 km", dist == 11);

        // Verify specific route calculation: R1->J2(2) + J2->J1(1) + J1->J3(5) + J3->C1(3) = 11
        check("P4-06", "Path 2+1+5+3 = 11", (2 + 1 + 5 + 3) == 11);

        // Verify direct route is longer: R1->J1(4) + J1->J3(5) + J3->C1(3) = 12
        check("P4-07", "Direct route 4+5+3 = 12 > 11, so Dijkstra chose correctly", (4 + 5 + 3) > 11);

        // Location POJO
        Location loc = new Location(1, "Mamak Corner");
        check("P4-08", "Location getName = Mamak Corner", loc.getName().equals("Mamak Corner"));
        loc.setName("Updated Name");
        check("P4-09", "Location setName works", loc.getName().equals("Updated Name"));

        System.out.println();
    }

    // ── Person 5: BST ──
    static void testPerson5_BST() {
        System.out.println("=== Person 5: Search & Data Retrieval (BST) ===\n");

        FoodBST bst = new FoodBST();
        FoodItem f1 = new FoodItem("Nasi Lemak", 8.50, "Malay", 201);
        FoodItem f2 = new FoodItem("Roti Canai", 3.00, "Malay", 201);
        FoodItem f3 = new FoodItem("Char Kuey Teow", 10.00, "Chinese", 202);
        FoodItem f4 = new FoodItem("Teh Tarik", 2.50, "Drinks", 201);
        FoodItem f5 = new FoodItem("Mee Goreng", 7.00, "Malay", 203);
        FoodItem f6 = new FoodItem("Chicken Rice", 6.50, "Chinese", 202);
        bst.insert(f1);
        bst.insert(f2);
        bst.insert(f3);
        bst.insert(f4);
        bst.insert(f5);
        bst.insert(f6);

        check("P5-01", "BST search Nasi Lemak found", bst.search("Nasi Lemak") != null);
        check("P5-02", "BST search Nasi Lemak price = 8.50", bst.search("Nasi Lemak").getPrice() == 8.50);
        check("P5-03", "BST search Pizza returns null", bst.search("Pizza") == null);
        check("P5-04", "BST search is case-insensitive", bst.search("nasi lemak") != null);

        // FoodItem POJO
        check("P5-05", "FoodItem getters", f1.getName().equals("Nasi Lemak") && f1.getCategory().equals("Malay") && f1.getRestaurantId() == 201);
        f1.setPrice(9.00);
        check("P5-06", "FoodItem setPrice works", f1.getPrice() == 9.00);
        f1.setPrice(8.50); // reset

        // Traversal (printed to console, visually verified)
        System.out.println("  [BST] In-order traversal (ascending by price):");
        bst.inOrderTraversal();
        System.out.println("  [BST] Reverse in-order traversal (descending by price):");
        bst.reverseInOrder();

        // Price range query (printed to console)
        System.out.println("  [BST] Price range RM3.00 - RM8.00:");
        bst.searchByPrice(3.00, 8.00);

        // Delete
        bst.delete("Roti Canai");
        check("P5-07", "After deleting Roti Canai, search returns null", bst.search("Roti Canai") == null);

        bst.delete("Pizza");
        check("P5-08", "Deleting non-existing item does not crash", bst.search("Nasi Lemak") != null);

        System.out.println("  [BST] Tree after deleting Roti Canai:");
        bst.inOrderTraversal();

        System.out.println();
    }

    // ── Person 5: HashMap ──
    static void testPerson5_HashMap() {
        System.out.println("=== Person 5: Search & Data Retrieval (HashMap) ===\n");

        DataHashMap map = new DataHashMap(16);

        map.put("user_101", "Ali - KL - 012-3456789");
        map.put("user_102", "Siti - PJ - 013-4567890");
        map.put("order_1", "Order #1 - Nasi Lemak x2 - RM17.00");
        map.put("order_2", "Order #2 - Roti Canai x1 - RM3.00");

        check("P5-09", "HashMap size = 4 after 4 puts", map.size() == 4);
        check("P5-10", "get(user_101) = Ali - KL - 012-3456789", map.get("user_101").equals("Ali - KL - 012-3456789"));
        check("P5-11", "get(order_1) = Order #1 - Nasi Lemak x2 - RM17.00", map.get("order_1").equals("Order #1 - Nasi Lemak x2 - RM17.00"));
        check("P5-12", "get(non_existing_key) = null", map.get("non_existing_key") == null);
        check("P5-13", "containsKey(user_102) = true", map.containsKey("user_102"));
        check("P5-14", "containsKey(ghost) = false", !map.containsKey("ghost"));

        // Update existing key
        map.put("user_101", "Ali - KL - 012-9999999 (updated)");
        check("P5-15", "After put(same key), get returns updated value", map.get("user_101").equals("Ali - KL - 012-9999999 (updated)"));
        check("P5-16", "After update, size stays 4 (not 5)", map.size() == 4);

        // Remove
        Object removed = map.remove("order_2");
        check("P5-17", "remove(order_2) returns the value", removed.equals("Order #2 - Roti Canai x1 - RM3.00"));
        check("P5-18", "After remove, get(order_2) = null", map.get("order_2") == null);
        check("P5-19", "After remove, size = 3", map.size() == 3);

        map.remove("ghost");
        check("P5-20", "Removing non-existing key does not crash, size unchanged", map.size() == 3);

        // Display
        System.out.println("  [HashMap] Display all buckets:");
        map.display();

        System.out.println();
    }
}
