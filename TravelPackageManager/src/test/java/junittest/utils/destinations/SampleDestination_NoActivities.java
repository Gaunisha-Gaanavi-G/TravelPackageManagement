package junittest.utils.destinations;

import org.TravelPackageManager.models.Activity;
import org.TravelPackageManager.models.Destination;

import java.util.ArrayList;
import java.util.List;

public class SampleDestination_NoActivities extends Destination {
    private final static List<Activity> activityList = new ArrayList<>();

    public SampleDestination_NoActivities(){
        super.destinationName="SampleDestination_NoActivities";
        populateActivities();
        super.activitiesList=activityList;
    }

    private static void populateActivities(){

    }
}
