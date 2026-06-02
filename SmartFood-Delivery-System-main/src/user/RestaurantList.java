// ============================================================
// Author: [Person 1 - Janice Lim Pei Yin]
// Module: User & Restaurant Management
// File:   RestaurantList.java — Linked List to manage restaurants
// ============================================================

package user;

public class RestaurantList {

    private class Node {
        Restaurant data;
        Node next;

        Node(Restaurant data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public RestaurantList() {
        this.head = null;
        this.size = 0;
    }

    public void addRestaurant(Restaurant restaurant) {
        Node newNode = new Node(restaurant);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
        System.out.println("Restaurant added: " + restaurant.getRestaurantName());
    }

    public void removeRestaurant(int restaurantId) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.data.getRestaurantId() == restaurantId) {
            head = head.next;
            size--;
            System.out.println("Restaurant " + restaurantId + " removed.");
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.data.getRestaurantId() != restaurantId) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
            size--;
            System.out.println("Restaurant " + restaurantId + " removed.");
        } else {
            System.out.println("Restaurant ID " + restaurantId + " not found.");
        }
    }

    public Restaurant searchRestaurant(int restaurantId) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.getRestaurantId() == restaurantId) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No restaurants found.");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public int getSize() {
        return size;
    }
}
