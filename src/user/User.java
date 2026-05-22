// ============================================================
// Author: [Person 1 - Janice Lim Pei Yin]
// Module: User & Restaurant Management
// File:   User.java — Data model for a customer
// ============================================================

package user;

public class User {
    private int userId;
    private String userName;
    private String address;

    public User(int userId, String userName, String address) {
        this.userId = userId;
        this.userName = userName;
        this.address = address;
    }

    public String getUserId() { 
        return userId; 
    }
    public String getUserName() { 
        return userName; 
    }
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "ID: " + userId + " | Name: " + userName + " | Address: " + address;
    }
}
