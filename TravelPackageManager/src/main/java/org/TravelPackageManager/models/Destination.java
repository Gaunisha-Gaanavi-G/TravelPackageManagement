package org.TravelPackageManager.models;

import java.util.List;

/**
 * Destination Class is abstract having required params and its Getters and Setters.
 * <p><b>Usage: </b>This class is used to create Destinations objects based on each Package.
 * <p> Subclasses will be the different destinations like(<tt>Goa</tt>,<tt>Delhi</tt>,etc.) with their required parameters.</p>
 * @see org.TravelPackageManager.destinations.Goa
 * @see org.TravelPackageManager.destinations.Delhi
 */
public abstract class Destination {
    protected String destinationName;
    protected List<Activity> activitiesList;


    public String getDestinationName() {
        return this.destinationName;
    }

    public List<Activity> getActivitiesList() {
        return this.activitiesList;
    }


}
