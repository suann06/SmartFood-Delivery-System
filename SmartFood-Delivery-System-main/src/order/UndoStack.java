// ============================================================
// Author: Su Ann
// Module: Order Processing System
// File: UndoStack.java — LIFO Stack for undo feature (cart)
// ============================================================

package order;

public class UndoStack {

    // Inner class Node
    private static class Node {
        String item;
        Node next;

        Node(String item) {
            this.item = item;
            this.next = null;
        }
    }

    // Variables
    private Node top;
    private int size;

    // Constructor
    public UndoStack(){
        top = null;
        size = 0;
    }

    // --- Core Methods ---
    // Add item to stack
    public void push(String item){

        Node newNode = new Node(item);

        newNode.next = top;
        top = newNode;

        size++;
    }


    // Remove latest item
    public String pop(){

        if(isEmpty()){
            return "Cart is empty";
        }

        String removed = top.item;

        top = top.next;

        size--;

        return removed;
    }


    // View top item
    public String peek(){

        if(isEmpty()){
            return "Cart is empty";
        }

        return top.item;
    }


    // Check empty
    public boolean isEmpty(){

        return size == 0;
    }


    // Display cart
    public void displayCart(){

        if(isEmpty()){
            System.out.println("Cart is empty");
            return;
        }

        Node current = top;

        while(current != null){

            System.out.println(current.item);

            current = current.next;
        }
    }


    // Return cart size
    public int getSize(){

        return size;
    }


    // Testing
    /*public static void main(String[] args) {

        UndoStack cart = new UndoStack();

        cart.push("Nasi Lemak");
        cart.push("Teh Tarik");
        cart.push("Roti Canai");

        System.out.println("=== Cart ===");
        cart.displayCart();

        System.out.println("\n=== Undo last item ===");

        String removed = cart.pop();

        System.out.println("Removed: " + removed);

        cart.displayCart();
    }
    */
}
