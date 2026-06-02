// ============================================================
// Author: [Person 5 - Write your name here]
// Module: Search & Recommendation
// File:   FoodBST.java — Binary Search Tree for food items
// ============================================================

package search;

public class FoodBST {

    // TODO: Create an inner class 'Node' with:
    //       - FoodItem data
    //       - Node left, right
    //       - Constructor


    // TODO: Declare root (Node)


    // TODO: Constructor — initialize root = null


    // --- Core Methods ---

    // TODO: insert(FoodItem item) — public method, calls recursive helper
    //       Sort by PRICE (item.getPrice()) so in-order traversal gives price-sorted results

    // TODO: insertRec(Node node, FoodItem item) — recursive insert
    //       If item.price < node.price → go left
    //       If item.price >= node.price → go right

    // TODO: search(String name) — search by food name
    //       Since BST is sorted by price, you'll need to traverse all nodes for name search
    //       (This is okay — explain in report that name search is O(n) but price search is O(log n))

    // TODO: searchByPrice(double minPrice, double maxPrice) — range search
    //       Use the BST structure to skip branches that are out of range

    // TODO: inOrderTraversal() — public method, calls recursive helper
    //       Prints items sorted by price (ascending): left → root → right

    // TODO: inOrderRec(Node node) — recursive in-order

    // TODO: reverseInOrder() — prints items sorted by price (descending)
    //       right → root → left

    // TODO: delete(String name) — remove a food item (optional but good for marks)
    //       Handle 3 cases: no children, one child, two children (find in-order successor)


    // --- Testing ---
    /*
    public static void main(String[] args) {
        FoodBST bst = new FoodBST();
        bst.insert(new FoodItem("Nasi Lemak", 8.50, "Malay", 201));
        bst.insert(new FoodItem("Roti Canai", 3.00, "Malay", 201));
        bst.insert(new FoodItem("Char Kuey Teow", 10.00, "Chinese", 202));
        bst.insert(new FoodItem("Teh Tarik", 2.50, "Drinks", 201));

        System.out.println("=== Sorted by Price (Ascending) ===");
        bst.inOrderTraversal();

        System.out.println("=== Sorted by Price (Descending) ===");
        bst.reverseInOrder();

        System.out.println("=== Search: Nasi Lemak ===");
        bst.search("Nasi Lemak");

        System.out.println("=== Price Range: RM3 - RM9 ===");
        bst.searchByPrice(3.00, 9.00);
    }
    */
}
