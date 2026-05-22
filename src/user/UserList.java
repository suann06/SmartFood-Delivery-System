// ============================================================
// Author: [Person 1 - Janice Lim Pei Yin]
// Module: User & Restaurant Management
// File:   UserList.java — Linked List to manage users
// ============================================================

package user;

public class UserList {

    // TODO: Create an inner class 'Node' with:
    //       - User data
    //       - Node next
    //       - Constructor


    // TODO: Declare head (Node) and size (int)


    // TODO: Constructor — initialize head = null, size = 0


    // --- Core Methods ---

    // TODO: addUser(User user) — add a new user to the end of the list

    // TODO: removeUser(int userId) — find and remove user by ID
    //       Handle: empty list, removing head, removing middle/end node

    // TODO: searchUser(int userId) — return the User if found, null otherwise

    // TODO: displayAll() — loop through and print every user

    // TODO: getSize() — return current size


    // --- Testing ---
    // Uncomment this to test your module independently:
    /*
    public static void main(String[] args) {
        UserList list = new UserList();
        list.addUser(new User(1, "Ali", "ali@mail.com", "KL", "012-345"));
        list.addUser(new User(2, "Siti", "siti@mail.com", "PJ", "013-456"));
        System.out.println("=== All Users ===");
        list.displayAll();
        System.out.println("=== Remove User 1 ===");
        list.removeUser(1);
        list.displayAll();
        System.out.println("=== Search User 2 ===");
        System.out.println(list.searchUser(2));
    }
    */
}
