// ============================================================
// Author: [Person 4 - HARRESH]
// Module: Route Optimization
// File:   Location.java — Data model for a location/node
// ============================================================

package route;

public class Location {
   private int locationId;
    private String name;

    // Constructor
    public Location(int locationId, String name) {
        this.locationId = locationId;
        this.name = name;
    }

    // Getters and Setters
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString
    @Override
    public String toString() {
        return "Location{id=" + locationId + ", name='" + name + "'}";
    }
}
