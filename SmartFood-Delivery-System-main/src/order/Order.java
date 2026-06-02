// ============================================================
// Author: Su Ann
// Module: Order Processing System
// File: Order.java — Data model for an order
// ============================================================

package order;

import java.util.Arrays;

public class Order {

    private int orderId;
    private int customerId;
    private int restaurantId;
    private String[] items;
    private double totalPrice;
    private String status;

    // Constructor
    public Order(int orderId,
                 int customerId,
                 int restaurantId,
                 String[] items,
                 double totalPrice,
                 String status) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String[] getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {

        return "Order{" +
                "id=" + orderId +
                ", customer=" + customerId +
                ", restaurant=" + restaurantId +
                ", items=" + Arrays.toString(items) +
                ", total=RM" + totalPrice +
                ", status=" + status +
                "}";
    }
}