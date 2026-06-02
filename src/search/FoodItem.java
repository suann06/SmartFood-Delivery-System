// Author: Person 5
// Module: Search, Recommendation & Data Retrieval
// Data Structure: BST + HashMap

package search;

public class FoodItem {

    private String name;
    private double price;
    private String category;
    private int restaurantId;

    public FoodItem(String name, double price, String category, int restaurantId) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.restaurantId = restaurantId;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getRestaurantId() { return restaurantId; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    @Override
    public String toString() {
        return String.format("RM%.2f - %s [%s] (Restaurant #%d)",
                price, name, category, restaurantId);
    }
}