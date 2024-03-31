package org.TravelPackageManager.destinations;

import org.TravelPackageManager.models.Activity;
import org.TravelPackageManager.models.Destination;

import java.util.ArrayList;
import java.util.List;

public class Hampi extends Destination {
    private final static List<Activity> activityList = new ArrayList<>();

    public Hampi(){
        populateActivities();
        super.destinationName="Hampi";
        super.activitiesList=activityList;
    }

    private static void populateActivities(){
        activityList.add(new Activity("Photoshoot",100,"Get exclusive photos of your memorable day",10));
        activityList.add(new Activity("Treasure Hunt",100,"Can you find the Treasure before anyone? Enroll to find out!",10));
    }
}
