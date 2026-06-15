// ============================================================
// Authors: [ALL TEAM MEMBERS]
// Module: System Integration - Interactive Console Driver
// File:   Main.java
//
// A polished, menu-driven CLI that wires every team module
// (Linked List, Stack, Queue, Min-Heap, Graph/Dijkstra, BST,
//  HashMap) into one live demo. Graders can feed custom input
// and watch each data structure respond in real time.
// ============================================================

package main;

import user.*;
import order.*;
import delivery.*;
import route.*;
import search.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // ---------------------------------------------------------
    //  Shared system state.
    //  The "real" data structures are the ones exercised by every
    //  add/remove/search call. The parallel `*View` lists exist only
    //  so we can render neat printf tables - none of the underlying
    //  modules expose their internal nodes for pretty printing.
    // ---------------------------------------------------------

    // Person 1 - User & Restaurant Management (Linked List)
    private static final UserList users = new UserList();
    private static final RestaurantList restaurants = new RestaurantList();
    private static final List<User> usersView = new ArrayList<>();           // mirror, for display only
    private static final List<Restaurant> restaurantsView = new ArrayList<>(); // mirror, for display only

    // Person 2 - Order Processing (Stack = cart, Queue = kitchen)
    private static final UndoStack cart = new UndoStack();
    private static final List<String> cartItems = new ArrayList<>();          // mirror so we can build Order arrays
    private static double cartTotal = 0.0;                                    // running total (stack stores names only)
    private static final OrderQueue kitchen = new OrderQueue();
    private static final List<Order> ordersView = new ArrayList<>();          // mirror, for display only
    private static int nextOrderId = 1001;

    // Person 3 - Delivery Rider Assignment (Min-Heap)
    private static final DeliveryAssignment dispatch = new DeliveryAssignment();
    private static final List<Rider> allRiders = new ArrayList<>();           // every rider ever added (heap consumes its own)

    // Person 4 - Route Optimization (Graph + Dijkstra)
    private static final Graph cityMap = new Graph();
    private static final Dijkstra pathfinder = new Dijkstra();
    private static final List<String> locations = new ArrayList<>();          // ordered node names for menu selection

    // Person 5 - Search (BST) & Data Retrieval (HashMap)
    private static final FoodBST menu = new FoodBST();
    private static final List<FoodItem> foodItems = new ArrayList<>();        // mirror + price lookup for the cart
    private static final DataHashMap dataStore = new DataHashMap(16);

    // Layout width for the ASCII chrome.
    private static final int WIDTH = 60;

    // ============================================================
    //  ENTRY POINT
    // ============================================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printBanner();
        loadDemoData();
        System.out.println("\n  Demo data loaded - every module is ready to test.\n");

        // Master loop: keep showing the menu until the user picks Exit.
        while (true) {
            printStatus();
            printMainMenu();

            int choice = readInt(sc, "  Enter choice> ", 0, 6);
            switch (choice) {
                case 1: manageUsers(sc);        break;
                case 2: manageOrders(sc);       break;
                case 3: manageDelivery(sc);     break;
                case 4: manageRoute(sc);        break;
                case 5: manageSearch(sc);       break;
                case 6: manageLookup(sc);       break;
                case 0:
                    System.out.println("\n  Shutting down SmartFood. Terima kasih!\n");
                    sc.close();
                    return;
                default:
                    System.out.println("  ! Invalid option.");
            }
        }
    }

    // ============================================================
    //  DEMO DATA - pre-populate so the system is instantly usable.
    //  Everything below is also fully editable through the menus.
    // ============================================================
    private static void loadDemoData() {
        // --- Customers (Linked List) ---
        addCustomer(101, "Ali",  "KL City Centre");
        addCustomer(102, "Siti", "Petaling Jaya");
        addCustomer(103, "Raj",  "Shah Alam");

        // --- Restaurants (Linked List) ---
        addRestaurant(201, "Mamak Corner",      "KL City Centre");
        addRestaurant(202, "CKT House",         "Petaling Jaya");
        addRestaurant(203, "Nasi Campur Baru",  "Shah Alam");

        // --- Menu (BST, keyed by price) ---
        addFood("Nasi Lemak",      8.50, "Malay",   201);
        addFood("Roti Canai",      3.00, "Malay",   201);
        addFood("Teh Tarik",       2.50, "Drinks",  201);
        addFood("Chicken Rice",    6.50, "Chinese", 202);
        addFood("Mee Goreng",      7.00, "Malay",   203);
        addFood("Char Kuey Teow", 10.00, "Chinese", 202);

        // --- Riders (Min-Heap, ordered by ETA) ---
        addRider(1, "Abang A", 15);
        addRider(2, "Abang B", 10);
        addRider(3, "Abang C", 20);

        // --- City map (weighted Graph) ---
        // Locations are registered in a fixed order so the route menu
        // can present them as a numbered list (robust, no typing).
        locations.add("Mamak Corner");
        locations.add("Junction 1");
        locations.add("Junction 2");
        locations.add("Junction 3");
        locations.add("Ali House");
        cityMap.addRoute("Mamak Corner", "Junction 1", 4);
        cityMap.addRoute("Mamak Corner", "Junction 2", 2);
        cityMap.addRoute("Junction 1",   "Junction 3", 5);
        cityMap.addRoute("Junction 2",   "Junction 1", 1);
        cityMap.addRoute("Junction 2",   "Junction 3", 8);
        cityMap.addRoute("Junction 3",   "Ali House",  3);

        // --- Fast-lookup records (HashMap) ---
        dataStore.put("user_101",  "Ali  | KL City Centre  | 012-345 6789");
        dataStore.put("user_102",  "Siti | Petaling Jaya  | 013-456 7890");
        dataStore.put("user_103",  "Raj  | Shah Alam      | 014-567 8901");
        dataStore.put("order_1001","Order #1001 | Nasi Lemak x2 | RM17.00");
        dataStore.put("order_1002","Order #1002 | Char Kuey Teow | RM10.00");

        // --- One live order so the FIFO queue is demo-ready ---
        Order seed = new Order(nextOrderId++, 101, 201,
                new String[]{"Nasi Lemak", "Teh Tarik"}, 11.00, "Pending");
        kitchen.enqueue(seed);
        ordersView.add(seed);
    }

    // ============================================================
    //  MODULE 1 - USER & RESTAURANT MANAGEMENT (Linked List)
    // ============================================================
    private static void manageUsers(Scanner sc) {
        while (true) {
            header("1. USER & RESTAURANT MANAGEMENT  [Linked List]");
            System.out.println("   1) Add Customer");
            System.out.println("   2) Add Restaurant");
            System.out.println("   3) Delete Customer  (by ID)");
            System.out.println("   4) Delete Restaurant(by ID)");
            System.out.println("   5) Display All Customers");
            System.out.println("   6) Display All Restaurants");
            System.out.println("   0) Back to Main Menu");
            switch (readInt(sc, "   Choose> ", 0, 6)) {
                case 1: {
                    int id = readInt(sc, "   New Customer ID> ");
                    String name = readLine(sc, "   Name> ");
                    String addr = readLine(sc, "   Address> ");
                    addCustomer(id, name, addr);
                    break;
                }
                case 2: {
                    int id = readInt(sc, "   New Restaurant ID> ");
                    String name = readLine(sc, "   Name> ");
                    String loc  = readLine(sc, "   Location> ");
                    addRestaurant(id, name, loc);
                    break;
                }
                case 3: removeCustomer(readInt(sc, "   Customer ID to delete> ")); break;
                case 4: removeRestaurant(readInt(sc, "   Restaurant ID to delete> ")); break;
                case 5: printCustomers(); break;
                case 6: printRestaurants(); break;
                case 0: return;
            }
        }
    }

    // ============================================================
    //  MODULE 2 - ORDER PROCESSING (Stack cart + Queue kitchen)
    // ============================================================
    private static void manageOrders(Scanner sc) {
        while (true) {
            header("2. ORDER PROCESSING  [Stack = Cart  |  Queue = Kitchen]");
            System.out.printf("   Cart: %d item(s) | RM%.2f   |   Pending orders: %d%n",
                    cart.getSize(), cartTotal, kitchen.getSize());
            System.out.println("   1) View Menu & Add to Cart");
            System.out.println("   2) Undo Last Item  (Stack pop, LIFO)");
            System.out.println("   3) View Cart");
            System.out.println("   4) Checkout  (push order into the Queue)");
            System.out.println("   5) Process Next Order  (Queue dequeue, FIFO)");
            System.out.println("   6) View Pending Orders");
            System.out.println("   0) Back to Main Menu");
            switch (readInt(sc, "   Choose> ", 0, 6)) {
                case 1: addToCart(sc);          break;
                case 2: undoLast();             break;
                case 3: viewCart();             break;
                case 4: checkout(sc);           break;
                case 5: processNextOrder();     break;
                case 6: printOrders();          break;
                case 0: return;
            }
        }
    }

    // ============================================================
    //  MODULE 3 - DELIVERY ASSIGNMENT (Min-Heap priority queue)
    // ============================================================
    private static void manageDelivery(Scanner sc) {
        while (true) {
            header("3. DELIVERY RIDER ASSIGNMENT  [Min-Heap]");
            System.out.println("   1) Add Rider");
            System.out.println("   2) View All Riders");
            System.out.println("   3) Match Best Rider  (extract-min)");
            System.out.println("   4) Available Riders in Heap");
            System.out.println("   0) Back to Main Menu");
            switch (readInt(sc, "   Choose> ", 0, 4)) {
                case 1: {
                    int id  = readInt(sc, "   Rider ID> ");
                    String n = readLine(sc, "   Rider Name> ");
                    int eta = readInt(sc, "   Estimated delivery time (min)> ", 1, 240);
                    addRider(id, n, eta);
                    break;
                }
                case 2: printRiders();        break;
                case 3: matchBestRider();     break;
                case 4:
                    System.out.printf("%n   >> Riders waiting in the heap: %d%n",
                            dispatch.getAvailableRidersCount());
                    break;
                case 0: return;
            }
        }
    }

    // ============================================================
    //  MODULE 4 - ROUTE OPTIMIZATION (Graph + Dijkstra)
    // ============================================================
    private static void manageRoute(Scanner sc) {
        while (true) {
            header("4. ROUTE OPTIMIZATION  [Graph + Dijkstra]");
            System.out.println("   1) View City Map (adjacency list)");
            System.out.println("   2) Find Shortest Route");
            System.out.println("   0) Back to Main Menu");
            switch (readInt(sc, "   Choose> ", 0, 2)) {
                case 1: printGraph();        break;
                case 2: findRoute(sc);       break;
                case 0: return;
            }
        }
    }

    // ============================================================
    //  MODULE 5 - SEARCH & RECOMMENDATION (BST)
    // ============================================================
    private static void manageSearch(Scanner sc) {
        while (true) {
            header("5. SEARCH & RECOMMENDATION  [Binary Search Tree]");
            System.out.println("   1) Search Food by Name");
            System.out.println("   2) Show Menu Sorted Ascending  (in-order, low -> high)");
            System.out.println("   3) Show Menu Sorted Descending (reverse in-order)");
            System.out.println("   4) Filter by Price Range");
            System.out.println("   0) Back to Main Menu");
            switch (readInt(sc, "   Choose> ", 0, 4)) {
                case 1: {
                    String name = readLine(sc, "   Food name to search> ");
                    System.out.println("\n   >> BST search for \"" + name + "\"...");
                    menu.search(name);   // module prints "Found: ..." or "... not found."
                    break;
                }
                case 2:
                    System.out.println("\n   >> In-order traversal = sorted menu, no separate sort needed (O(n)):");
                    menu.inOrderTraversal();
                    break;
                case 3:
                    System.out.println("\n   >> Reverse in-order = highest price first:");
                    menu.reverseInOrder();
                    break;
                case 4: {
                    double min = readDouble(sc, "   Min price (RM)> ");
                    double max = readDouble(sc, "   Max price (RM)> ");
                    System.out.println("\n   >> Range search prunes branches outside the range - O(log n + k):");
                    menu.searchByPrice(min, max);
                    break;
                }
                case 0: return;
            }
        }
    }

    // ============================================================
    //  MODULE 6 - INSTANT DATA LOOKUP (HashMap, O(1))
    // ============================================================
    private static void manageLookup(Scanner sc) {
        while (true) {
            header("6. INSTANT DATA LOOKUP  [HashMap - O(1)]");
            System.out.println("   1) Lookup by Key  (user_101, order_1001, ...)");
            System.out.println("   2) Check if Key Exists");
            System.out.println("   3) View All Buckets  (chaining visualised)");
            System.out.println("   0) Back to Main Menu");
            switch (readInt(sc, "   Choose> ", 0, 3)) {
                case 1: {
                    String key = readLine(sc, "   Key> ");
                    Object value = dataStore.get(key);
                    System.out.println();
                    if (value != null) {
                        System.out.printf("   %s  ->  %s%n", key, value);
                        System.out.println("   >> Retrieved in O(1) - constant time regardless of dataset size.");
                        System.out.println("   >> A Linked List scan would be O(n); the hash table avoids the walk.");
                    } else {
                        System.out.println("   ! Key \"" + key + "\" not found (still resolved in O(1)).");
                    }
                    break;
                }
                case 2: {
                    String key = readLine(sc, "   Key> ");
                    System.out.printf("%n   containsKey(\"%s\") -> %s%n",
                            key, dataStore.containsKey(key));
                    break;
                }
                case 3:
                    System.out.println("\n   >> 16 buckets, collisions resolved by separate chaining:");
                    dataStore.display();
                    break;
                case 0: return;
            }
        }
    }

    // ============================================================
    //  ORDER FLOW HELPERS
    // ============================================================
    private static void addToCart(Scanner sc) {
        printFoodTable();
        if (foodItems.isEmpty()) return;
        int pick = readInt(sc, "\n   Add item number to cart (0 to cancel)> ", 0, foodItems.size());
        if (pick == 0) return;
        FoodItem f = foodItems.get(pick - 1);

        // Push onto the LIFO stack; mirror the name + price for later checkout.
        cart.push(f.getName());
        cartItems.add(f.getName());
        cartTotal += f.getPrice();
        System.out.printf("   + Added \"%s\" (RM%.2f). Cart total: RM%.2f%n",
                f.getName(), f.getPrice(), cartTotal);
    }

    private static void undoLast() {
        if (cart.isEmpty()) {
            System.out.println("   ! Cart is empty - nothing to undo.");
            return;
        }
        // LIFO: the most recent push is the first to come off.
        String removed = cart.pop();
        cartItems.remove(cartItems.size() - 1);
        cartTotal -= priceOf(removed);
        System.out.printf("   - Undid \"%s\". Cart total: RM%.2f%n", removed, cartTotal);
    }

    private static void viewCart() {
        System.out.println("\n   Current Cart (top of stack first):");
        cart.displayCart();
        System.out.printf("   --------%n   Total: RM%.2f%n", cartTotal);
    }

    private static void checkout(Scanner sc) {
        if (cart.isEmpty()) {
            System.out.println("   ! Cart is empty - add items before checking out.");
            return;
        }
        if (usersView.isEmpty() || restaurantsView.isEmpty()) {
            System.out.println("   ! Need at least one customer and one restaurant first (Menu 1).");
            return;
        }
        // Choose who is ordering and from where.
        System.out.println("\n   Who is ordering?");
        printCustomers();
        int ci = readInt(sc, "   Pick customer number> ", 1, usersView.size());
        System.out.println("\n   From which restaurant?");
        printRestaurants();
        int ri = readInt(sc, "   Pick restaurant number> ", 1, restaurantsView.size());

        User cust = usersView.get(ci - 1);
        Restaurant rest = restaurantsView.get(ri - 1);

        // Build the order and hand it to the FIFO queue (kitchen).
        Order order = new Order(nextOrderId++, cust.getUserId(), rest.getRestaurantId(),
                cartItems.toArray(new String[0]), cartTotal, "Pending");
        kitchen.enqueue(order);
        ordersView.add(order);

        System.out.printf("%n   >> Order #%d enqueued for %s from %s - RM%.2f%n",
                order.getOrderId(), cust.getUserName(), rest.getRestaurantName(), cartTotal);
        System.out.println("   >> It will be processed in the order it was received (FIFO).");

        // Reset the cart for the next customer.
        while (!cart.isEmpty()) cart.pop();
        cartItems.clear();
        cartTotal = 0.0;
    }

    private static void processNextOrder() {
        if (kitchen.isEmpty()) {
            System.out.println("   ! No pending orders in the queue.");
            return;
        }
        // FIFO: the oldest order (front of queue) leaves first.
        Order next = kitchen.dequeue();
        ordersView.remove(0);
        next.setStatus("Completed");
        System.out.println("   >> Processing next order (FIFO dequeue):");
        printOrderRow(next);
    }

    // ============================================================
    //  DELIVERY HELPERS
    // ============================================================
    private static void matchBestRider() {
        if (dispatch.getAvailableRidersCount() == 0) {
            System.out.println("   ! No available riders in the heap. Add one first (Menu 3).");
            return;
        }
        int before = dispatch.getAvailableRidersCount();

        // assignRider() extracts the heap root (minimum ETA) in O(log n).
        Rider best = dispatch.assignRider();   // module prints the assignment confirmation
        System.out.printf("   >> Matched rider: %s (Rider #%d) - ETA %d min%n",
                best.getName(), best.getRiderId(), best.getEstimatedTime());

        System.out.println("\n   >> Performance comparison:");
        System.out.printf("      - A linear scan would inspect all %d available riders: O(n).%n", before);
        System.out.println("      - The min-heap pulled the minimum ETA straight from the root,");
        System.out.println("        then re-heapified in O(log n) - faster as the fleet grows.");
    }

    // ============================================================
    //  ROUTE HELPERS
    // ============================================================
    private static void findRoute(Scanner sc) {
        header("SHORTEST PATH - Dijkstra's Algorithm");
        printGraph();

        int n = locations.size();
        System.out.println("\n   Locations:");
        for (int i = 0; i < n; i++) {
            System.out.printf("     %d) %s%n", i + 1, locations.get(i));
        }
        int s = readInt(sc, "\n   Select START location> ", 1, n) - 1;
        int d = readInt(sc, "   Select END   location> ", 1, n) - 1;
        String src = locations.get(s);
        String dst = locations.get(d);

        System.out.printf("%n   Running Dijkstra from \"%s\" to \"%s\"...%n%n", src, dst);
        int distance = pathfinder.findShortestPath(cityMap, src, dst);   // prints the path + total

        System.out.println();
        if (distance < 0) {
            System.out.println("   ! No valid path exists between those locations.");
        } else {
            System.out.println("   >> Greedy shortest path found with non-negative edge weights.");
            System.out.println("   >> Complexity: O((V + E) log V) using an adjacency list + priority queue.");
        }
    }

    // ============================================================
    //  STATE MUTATORS (keep the real DS and the display mirror in sync)
    // ============================================================
    private static void addCustomer(int id, String name, String addr) {
        if (name.isEmpty()) name = "(unnamed)";
        if (addr.isEmpty()) addr = "(unknown)";
        User u = new User(id, name, addr);
        users.addUser(u);            // real Linked List
        usersView.add(u);            // display mirror
    }

    private static void removeCustomer(int id) {
        users.removeUser(id);        // real Linked List
        usersView.removeIf(u -> u.getUserId() == id);
        System.out.println("   >> Customer " + id + " removal processed.");
    }

    private static void addRestaurant(int id, String name, String loc) {
        if (name.isEmpty()) name = "(unnamed)";
        if (loc.isEmpty())  loc  = "(unknown)";
        Restaurant r = new Restaurant(id, name, loc);
        restaurants.addRestaurant(r);
        restaurantsView.add(r);
    }

    private static void removeRestaurant(int id) {
        restaurants.removeRestaurant(id);
        restaurantsView.removeIf(r -> r.getRestaurantId() == id);
        System.out.println("   >> Restaurant " + id + " removal processed.");
    }

    private static void addFood(String name, double price, String category, int restoId) {
        FoodItem f = new FoodItem(name, price, category, restoId);
        menu.insert(f);              // real BST
        foodItems.add(f);            // display mirror + cart price lookup
    }

    private static void addRider(int id, String name, int eta) {
        if (name.isEmpty()) name = "(unnamed)";
        Rider r = new Rider(id, name, eta, true);
        dispatch.insertRider(r);     // real Min-Heap (accepts only available riders)
        allRiders.add(r);
    }

    private static double priceOf(String name) {
        for (FoodItem f : foodItems) {
            if (f.getName().equalsIgnoreCase(name)) return f.getPrice();
        }
        return 0.0;
    }

    // ============================================================
    //  FORMATTED TABLES (printf)
    // ============================================================
    private static void printCustomers() {
        header("CUSTOMERS");
        if (usersView.isEmpty()) { System.out.println("   (none)"); return; }
        System.out.printf("   %-8s %-22s %-26s%n", "ID", "Name", "Address");
        System.out.println("   " + rep('-', 56));
        for (User u : usersView) {
            System.out.printf("   %-8d %-22.22s %-26.26s%n",
                    u.getUserId(), u.getUserName(), u.getAddress());
        }
    }

    private static void printRestaurants() {
        header("RESTAURANTS");
        if (restaurantsView.isEmpty()) { System.out.println("   (none)"); return; }
        System.out.printf("   %-8s %-24s %-22s%n", "ID", "Name", "Location");
        System.out.println("   " + rep('-', 54));
        for (Restaurant r : restaurantsView) {
            System.out.printf("   %-8d %-24.24s %-22.22s%n",
                    r.getRestaurantId(), r.getRestaurantName(), r.getLocation());
        }
    }

    private static void printFoodTable() {
        header("MENU");
        if (foodItems.isEmpty()) { System.out.println("   (none)"); return; }
        System.out.printf("   %-4s %-20s %-8s %-10s %-6s%n", "No.", "Item", "Price", "Category", "Resto");
        System.out.println("   " + rep('-', 50));
        for (int i = 0; i < foodItems.size(); i++) {
            FoodItem f = foodItems.get(i);
            System.out.printf("   %-4d %-20.20s RM%-6.2f %-10.10s #%-5d%n",
                    i + 1, f.getName(), f.getPrice(), f.getCategory(), f.getRestaurantId());
        }
    }

    private static void printRiders() {
        header("RIDERS");
        if (allRiders.isEmpty()) { System.out.println("   (none)"); return; }
        System.out.printf("   %-6s %-16s %-8s %-12s%n", "ID", "Name", "ETA", "Status");
        System.out.println("   " + rep('-', 44));
        for (Rider r : allRiders) {
            System.out.printf("   %-6d %-16.16s %-8d %-12s%n",
                    r.getRiderId(), r.getName(), r.getEstimatedTime(),
                    r.isAvailable() ? "Available" : "BUSY");
        }
    }

    private static void printOrders() {
        header("PENDING ORDERS (FIFO queue, front first)");
        if (ordersView.isEmpty()) { System.out.println("   (none)"); return; }
        System.out.printf("   %-8s %-9s %-9s %-28s %-8s%n", "Order", "Cust", "Resto", "Items", "Total");
        System.out.println("   " + rep('-', 64));
        for (Order o : ordersView) printOrderRow(o);
    }

    private static void printOrderRow(Order o) {
        System.out.printf("   %-8d %-9d %-9d %-28.28s RM%-6.2f%n",
                o.getOrderId(), o.getCustomerId(), o.getRestaurantId(),
                String.join(", ", o.getItems()), o.getTotalPrice());
    }

    private static void printGraph() {
        header("CITY MAP - Weighted Graph (Adjacency List)");
        System.out.printf("   %-16s %s%n", "Location", "Connected To (weight)");
        System.out.println("   " + rep('-', 52));
        for (String node : locations) {
            StringBuilder nb = new StringBuilder();
            List<Graph.Edge> edges = cityMap.getNeighbors(node);
            for (int i = 0; i < edges.size(); i++) {
                Graph.Edge e = edges.get(i);
                if (i > 0) nb.append(", ");
                nb.append(e.getDestination()).append(" (").append(e.getWeight()).append(")");
            }
            if (nb.length() == 0) nb.append("(dead end)");
            System.out.printf("   %-16.16s %s%n", node, nb.toString());
        }
    }

    // ============================================================
    //  CONSOLE CHROME & INPUT UTILITIES
    // ============================================================
    private static void printBanner() {
        System.out.println();
        System.out.println("   " + rep('=', 48));
        System.out.println("       SMARTFOOD DELIVERY & ORDER SYSTEM");
        System.out.println("        Interactive Console (CLI) v1.0");
        System.out.println("   " + rep('=', 48));
    }

    private static void printMainMenu() {
        System.out.println("\n   " + rep('-', WIDTH));
        System.out.println("   MAIN MENU");
        System.out.println("   " + rep('-', WIDTH));
        System.out.println("    1) User & Restaurant Management   [Linked List]");
        System.out.println("    2) Order Processing                [Stack + Queue]");
        System.out.println("    3) Delivery Rider Assignment       [Min-Heap]");
        System.out.println("    4) Route Optimization              [Graph + Dijkstra]");
        System.out.println("    5) Search & Recommendation         [BST]");
        System.out.println("    6) Instant Data Lookup             [HashMap]");
        System.out.println("    0) Exit");
        System.out.println("   " + rep('-', WIDTH));
    }

    private static void printStatus() {
        System.out.printf("%n   [ Users: %d | Restaurants: %d | Menu: %d | Riders available: %d | Orders: %d ]%n",
                usersView.size(), restaurantsView.size(), foodItems.size(),
                dispatch.getAvailableRidersCount(), kitchen.getSize());
    }

    private static void header(String title) {
        System.out.println("\n   " + rep('-', WIDTH));
        System.out.println("   " + title);
        System.out.println("   " + rep('-', WIDTH));
    }

    // Always read a full line, then parse - this sidesteps the classic
    // nextInt()/nextLine() newline bug and makes recovery trivial.
    private static String readLine(Scanner sc, String prompt) {
        System.out.print(prompt);
        // Guard against EOF (piped input / Ctrl+D) so we never spin forever.
        if (!sc.hasNextLine()) {
            System.out.println("\n   (End of input detected - exiting.)");
            System.exit(0);
        }
        return sc.nextLine().trim();
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(sc, prompt));
            } catch (NumberFormatException e) {
                System.out.println("   ! Not a whole number - please try again.");
            } catch (Exception e) {
                System.out.println("   ! Input error - please try again.");
            }
        }
    }

    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            int v = readInt(sc, prompt);
            if (v >= min && v <= max) return v;
            System.out.printf("   ! Out of range - enter a number between %d and %d.%n", min, max);
        }
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readLine(sc, prompt));
            } catch (NumberFormatException e) {
                System.out.println("   ! Not a valid amount - please try again (e.g. 8.50).");
            }
        }
    }

    // Java 8-safe string repeat (avoids String.repeat which needs Java 11+).
    private static String rep(char c, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(c);
        return sb.toString();
    }
}
