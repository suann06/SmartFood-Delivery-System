package user_restaurantmanagement;

public class User {
    private String userId;
    private String userName;
    private String address;

    public User(String userId, String userName, String address) {
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
