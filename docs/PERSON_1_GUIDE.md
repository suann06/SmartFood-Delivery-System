# 👤 Person 1 — User & Restaurant Management

> **Data Structure:** Array / Linked List  
> **Marks:** 15  
> **Files:** `src/user/User.java`, `src/user/UserList.java`, `src/user/Restaurant.java`, `src/user/RestaurantList.java`

---

## 🎯 Your Goal

Build a module that stores and manages **customer** and **restaurant** information.  
Users and restaurants can be **added, removed, and displayed**.

---

## 📝 What You Need to Implement

### `User.java` — Data model for a customer
- Fields: `userId`, `name`, `email`, `address`, `phone`
- Constructor + getters/setters
- `toString()` method for display

### `Restaurant.java` — Data model for a restaurant
- Fields: `restaurantId`, `name`, `address`, `cuisine`, `rating`
- Constructor + getters/setters
- `toString()` method for display

### `UserList.java` — Linked List to manage users
- `addUser(User user)` — add to the list
- `removeUser(int userId)` — remove by ID
- `searchUser(int userId)` — find a user by ID
- `displayAll()` — print all users

### `RestaurantList.java` — Linked List to manage restaurants
- `addRestaurant(Restaurant r)` — add to the list
- `removeRestaurant(int restaurantId)` — remove by ID
- `searchRestaurant(int restaurantId)` — find by ID
- `displayAll()` — print all restaurants

---

## 💡 Why Linked List?

You need to explain this in your report:

| Operation | Array | Linked List |
|-----------|-------|-------------|
| Add to end | O(1) amortized | O(1) with tail pointer |
| Remove by ID | O(n) search + O(n) shift | O(n) search + O(1) remove |
| Search by ID | O(n) | O(n) |
| Dynamic size | Needs resizing | Naturally dynamic |

**Key argument:** Linked Lists don't require resizing and allow O(1) deletion once the node is found (no shifting like arrays). For a system where users and restaurants are frequently added/removed, a linked list is more flexible.

---

## 🧪 How to Test Your Module

Add a temporary `main()` in `UserList.java`:

```java
public static void main(String[] args) {
    UserList list = new UserList();
    list.addUser(new User(1, "Ali", "ali@mail.com", "KL", "012-345"));
    list.addUser(new User(2, "Siti", "siti@mail.com", "PJ", "013-456"));
    list.displayAll();
    list.removeUser(1);
    list.displayAll();
}
```

---

## 📊 Diagram You Need for the Report

Draw a **Linked List diagram** showing nodes connected with arrows:

```
[User 1 | next] → [User 2 | next] → [User 3 | next] → null
```

Show what happens when a node is **added** and **removed**.

---

## 🔗 How Your Module Connects to Others

- **Person 2** (Orders) will reference your `User` and `Restaurant` objects when creating orders
- **Person 5** (HashMap) may store your `User` objects for fast retrieval by ID
- In `Main.java`, your module runs first to set up the data
