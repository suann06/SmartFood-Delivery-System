// ============================================================
// Author: [Person 3 - Koh Pei Yin]
// Module: Delivery Rider Assignment
// File:   Rider.java — Data model for a delivery rider
// ============================================================

package delivery;
public class Rider {

    //Instance Variables
    private int riderId;
    private String name;
    private int estimatedTime; 
    private boolean isAvailable;

    //Constructor
    public Rider(int riderId, String name, int estimatedTime, boolean isAvailable) {
        this.riderId = riderId;
        this.name = name;
        this.estimatedTime = estimatedTime;
        this.isAvailable = isAvailable;
    }

    //Getters and Setters
    //Rider ID
    public int getRiderId() {
        return riderId;
    }
    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }

    //Rider Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //Estimated Time
    public int getEstimatedTime() {
        return estimatedTime;
    }
    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    //Availability
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    //Additional Methods
    public int getDeliveryTime() {
        return this.estimatedTime;
    }
    public String getRiderName() {
        return this.name;
    }

    //Overridden toString()
    @Override
    public String toString() {
        return "Rider{" +
                "id=" + riderId +
                ", name='" + name + '\'' +
                ", estimatedTime=" + estimatedTime + " min" +
                ",available=" + isAvailable +
                '}';
    }
}