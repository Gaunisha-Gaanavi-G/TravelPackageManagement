package org.TravelPackageManager.destinations;

import org.TravelPackageManager.models.Activity;
import org.TravelPackageManager.models.Destination;

import java.util.ArrayList;
import java.util.List;

public class Mysore extends Destination {

    private final static List<Activity> activityList = new ArrayList<>();

    public Mysore(){
        super.destinationName="Mysore";
        populateActivities();
        super.activitiesList=activityList;
    }

    private static void populateActivities(){
        activityList.add(new Activity("Photoshoot",100,"Get exclusive photos of your memorable day",10));
        activityList.add(new Activity("Campfire Night",100,"Get to experience a cool campfire night",50));
    }
}
