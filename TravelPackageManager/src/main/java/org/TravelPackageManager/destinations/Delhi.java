package org.TravelPackageManager.destinations;

import org.TravelPackageManager.models.Activity;
import org.TravelPackageManager.models.Destination;

import java.util.ArrayList;
import java.util.List;

public class Delhi extends Destination {
    private final static List<Activity> activityList = new ArrayList<>();

    public Delhi(){
        populateActivities();
        super.destinationName="Delhi";
        super.activitiesList=activityList;
    }

    private static void populateActivities(){
        activityList.add(new Activity("Photoshoot",100,"Get exclusive photos of your memorable day",10));
        activityList.add(new Activity("Games",100,"Get to play exciting games with you fellow passengers!",10));
    }

}
