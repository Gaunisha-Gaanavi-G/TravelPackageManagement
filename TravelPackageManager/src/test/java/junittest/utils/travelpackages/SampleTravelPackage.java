package junittest.utils.travelpackages;

import junittest.utils.destinations.SampleDestination_NoActivities;
import org.TravelPackageManager.destinations.Delhi;
import org.TravelPackageManager.destinations.Shimla;
import org.TravelPackageManager.models.Destination;
import org.TravelPackageManager.models.Passenger;
import org.TravelPackageManager.models.TravelPackageImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleTravelPackage extends TravelPackageImpl{
    private static final String packageName = "SAMPLE PACKAGE";
    private static final int passengerCapacity = 25;
    private static final List<Destination> destinationList = new ArrayList<>(
            Arrays.asList(
                new SampleDestination_NoActivities()
            )
    );
    private static List<Passenger> passengersList = new ArrayList<>();

    public SampleTravelPackage() {
        super(packageName, passengerCapacity, destinationList);
    }
}