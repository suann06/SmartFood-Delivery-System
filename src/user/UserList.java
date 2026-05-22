// ============================================================
// Author: [Person 1 - Janice Lim Pei Yin]
// Module: User & Restaurant Management
// File:   UserList.java — Linked List to manage users
// ============================================================

package user;

public class UserList {

    private class Node {
        User data;
        Node next;

        Node(User data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public UserList() {
        this.head = null;
        this.size = 0;
    }

    public void addUser(User user) {
        Node newNode = new Node(user);
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
        System.out.println("User added: " + user.getUserName());
    }

    public void removeUser(int userId) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.data.getUserId() == userId) {
            head = head.next;
            size--;
            System.out.println("User " + userId + " removed.");
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.data.getUserId() != userId) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
            size--;
            System.out.println("User " + userId + " removed.");
        } else {
            System.out.println("User ID " + userId + " not found.");
        }
    }

    public User searchUser(int userId) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.getUserId() == userId) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No users found.");
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
