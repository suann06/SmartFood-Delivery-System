// ============================================================
// Author: [Person 1 - Janice Lim Pei Yin]
// Module: User & Restaurant Management
// File:   Restaurant.java — Data model for a restaurant
// ============================================================

package user;

public class Restaurant {
    private String restaurantId;
    private String restaurantName;
    private String location;

    public Restaurant(String restaurantId, String restaurantName, String location) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.location = location;
    }

    public String getRestaurantId() { 
        return restaurantId; 
    }
    public String getRestaurantName() {
        return restaurantName; 
    }
    public String getLocation() {
        return location; 
    }

    @Override
    public String toString() {
        return "ID: " + restaurantId + " | Name: " + restaurantName + " | Location: " + location;
    }
}
