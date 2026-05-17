// ============================================================
// Author: [Person 2 - Write your name here]
// Module: Order Processing System
// File:   UndoStack.java — LIFO Stack for undo feature (cart)
// ============================================================

package order;

public class UndoStack {

    // TODO: Create an inner class 'Node' with:
    //       - String item (food item name)
    //       - Node next


    // TODO: Declare top (Node), size (int)


    // TODO: Constructor — initialize top = null, size = 0


    // --- Core Methods ---

    // TODO: push(String item) — add item to the top of the stack

    // TODO: pop() — remove and return the item at the top (last added = undo)
    //       Handle: empty stack case

    // TODO: peek() — return top item without removing

    // TODO: isEmpty() — return true if size == 0

    // TODO: displayCart() — print all items from top to bottom

    // TODO: getSize() — return current size


    // --- Testing ---
    /*
    public static void main(String[] args) {
        UndoStack cart = new UndoStack();
        cart.push("Nasi Lemak");
        cart.push("Teh Tarik");
        cart.push("Roti Canai");
        System.out.println("=== Cart ===");
        cart.displayCart();
        System.out.println("=== Undo last item ===");
        String removed = cart.pop();
        System.out.println("Removed: " + removed);
        cart.displayCart();
    }
    */
}
