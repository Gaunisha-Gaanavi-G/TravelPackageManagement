package org.TravelPackageManager.models;

import java.util.ArrayList;
import java.util.List;


/**
 * Activity Class having required params and its Getters and Setters.
 * <p><b>Usage: </b>This class is used to create Activity objects based on each Destination.
 *
 */
public class Activity {
    private final String activityName;
    private final int cost;
    private final String description;
    private final int capacity;
    public List<Passenger> enrolledPassengers = new ArrayList<>();

    public int remainingCapacity;

    public Activity(String activityName, int cost, String description, int capacity){
        this.activityName = activityName;
        this.cost = cost;
        this.capacity = capacity;
        this.description = description;
        this.remainingCapacity = capacity;
    }

    public String getActivityName() {
        return activityName;
    }

    public int getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public List<Passenger> getEnrolledPassengers() {
        return enrolledPassengers;
    }
}
