// Author: Person 5
// Module: Search, Recommendation & Data Retrieval
// Data Structure: BST + HashMap

package search;

public class FoodBST {

    private class Node {
        FoodItem data;
        Node left, right;

        Node(FoodItem data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;

    public FoodBST() {
        root = null;
    }

    // Insert a food item into the tree (sorted by price)
    public void insert(FoodItem item) {
        root = insertRec(root, item);
    }

    private Node insertRec(Node node, FoodItem item) {
        if (node == null) return new Node(item);

        int cmp = Double.compare(item.getPrice(), node.data.getPrice());
        if (cmp < 0) {
            node.left = insertRec(node.left, item);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, item);
        } else {
            // same price, use name to decide
            if (item.getName().compareToIgnoreCase(node.data.getName()) < 0) {
                node.left = insertRec(node.left, item);
            } else {
                node.right = insertRec(node.right, item);
            }
        }
        return node;
    }

    // Search for a food item by name
    public FoodItem search(String name) {
        FoodItem result = searchRec(root, name);
        if (result == null) {
            System.out.println("Food item \"" + name + "\" not found.");
        }
        return result;
    }

    private FoodItem searchRec(Node node, String name) {
        if (node == null) return null;
        if (node.data.getName().equalsIgnoreCase(name)) {
            System.out.println("Found: " + node.data);
            return node.data;
        }
        FoodItem leftResult = searchRec(node.left, name);
        if (leftResult != null) return leftResult;
        return searchRec(node.right, name);
    }

    // Find all items in a price range [min, max]
    public void searchByPrice(double minPrice, double maxPrice) {
        System.out.println("Food items between RM" + String.format("%.2f", minPrice)
                + " and RM" + String.format("%.2f", maxPrice) + ":");
        int count = searchByPriceRec(root, minPrice, maxPrice);
        if (count == 0) {
            System.out.println("  (No items found in this price range)");
        }
    }

    private int searchByPriceRec(Node node, double minPrice, double maxPrice) {
        if (node == null) return 0;
        int count = 0;

        if (node.data.getPrice() > minPrice)
            count += searchByPriceRec(node.left, minPrice, maxPrice);

        if (node.data.getPrice() >= minPrice && node.data.getPrice() <= maxPrice) {
            System.out.println("  " + node.data);
            count++;
        }

        if (node.data.getPrice() < maxPrice)
            count += searchByPriceRec(node.right, minPrice, maxPrice);

        return count;
    }

    // Display all items sorted low to high
    public void inOrderTraversal() {
        System.out.println("=== All Food Items (Sorted by Price: Low to High) ===");
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.println("  " + node.data);
        inOrderRec(node.right);
    }

    // Display all items sorted high to low
    public void reverseInOrder() {
        System.out.println("=== All Food Items (Sorted by Price: High to Low) ===");
        reverseInOrderRec(root);
        System.out.println();
    }

    private void reverseInOrderRec(Node node) {
        if (node == null) return;
        reverseInOrderRec(node.right);
        System.out.println("  " + node.data);
        reverseInOrderRec(node.left);
    }

    // Delete a food item by name
    public void delete(String name) {
        boolean[] found = {false};
        root = deleteRec(root, name, found);
        if (!found[0]) {
            System.out.println("Food item \"" + name + "\" not found for deletion.");
        }
    }

    private Node deleteRec(Node node, String name, boolean[] found) {
        if (node == null) return null;

        if (node.data.getName().equalsIgnoreCase(name)) {
            found[0] = true;
            System.out.println("Deleted: " + node.data);

            if (node.left == null && node.right == null) return null;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // two children: replace with in-order successor
            Node successor = findMin(node.right);
            node.data = successor.data;
            node.right = removeMin(node.right);
            return node;
        }

        node.left = deleteRec(node.left, name, found);
        if (!found[0]) {
            node.right = deleteRec(node.right, name, found);
        }
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) return node.right;
        node.left = removeMin(node.left);
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public static void main(String[] args) {
        FoodBST bst = new FoodBST();

        bst.insert(new FoodItem("Nasi Lemak", 8.50, "Malay", 201));
        bst.insert(new FoodItem("Roti Canai", 3.00, "Malay", 201));
        bst.insert(new FoodItem("Char Kuey Teow", 10.00, "Chinese", 202));
        bst.insert(new FoodItem("Teh Tarik", 2.50, "Drinks", 201));
        bst.insert(new FoodItem("Mee Goreng", 7.00, "Malay", 203));
        bst.insert(new FoodItem("Chicken Rice", 6.50, "Chinese", 202));

        bst.inOrderTraversal();
        bst.reverseInOrder();

        System.out.println("=== Search for Nasi Lemak ===");
        bst.search("Nasi Lemak");

        System.out.println("\n=== Search for Pizza ===");
        bst.search("Pizza");

        System.out.println();
        bst.searchByPrice(3.00, 8.50);

        System.out.println();
        bst.delete("Roti Canai");

        System.out.println();
        bst.inOrderTraversal();
    }
}