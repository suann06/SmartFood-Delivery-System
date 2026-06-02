// ============================================================
// Author: [Person 3 - Koh Pei Yin]
// Module: Delivery Rider Assignment
// File:   DeliveryAssignment.java — Assigns riders using Min Heap
// ============================================================

package delivery;

public class DeliveryAssignment {

    //Instance Variables
    private MinHeap riderHeap;

    //Constructor
    public DeliveryAssignment() {
        this.riderHeap = new MinHeap();
    }

    //Core Business Logic
    //Insert Rider to Queue
    public void insertRider(Rider rider) {
        if (rider != null && rider.isAvailable()) {
            riderHeap.insertRider(rider);
        }
    }

    //Assign Optimal Rider
    public Rider assignRider() {
        if (riderHeap.isEmpty()) {
            System.out.println("No available delivery riders in the queue at the moment.");
            return null;
        }

        // Extract the absolute best rider (minimum estimated delivery time)
        Rider assignedRider = riderHeap.removeMin();
        
        // Update their availability status so they aren't double booked
        if (assignedRider != null) {
            assignedRider.setAvailable(false);
            System.out.println("Successfully assigned Rider: " + assignedRider.getName() + 
                               " (Estimated Trip Time: " + assignedRider.getEstimatedTime() + " min)");
        }
        
        return assignedRider;
    }

    //Additional Utility Methods
    //Get Available Riders Count
    public int getAvailableRidersCount() {
        return riderHeap.size();
    }
}