package org.TravelPackageManager.travelpackages;

import org.TravelPackageManager.destinations.Delhi;
import org.TravelPackageManager.destinations.Shimla;
import org.TravelPackageManager.models.Destination;
import org.TravelPackageManager.models.TravelPackageImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManaliTravelPackage extends TravelPackageImpl {

    private static final String packageName = "MANALI TRAVEL PACKAGE";
    private static final int passengerCapacity = 25;
    private static final List<Destination> destinationList = new ArrayList<>(
            Arrays.asList(
                    new Delhi(),
                    new Shimla()
            )
    );

    public ManaliTravelPackage(){
        super(packageName,passengerCapacity,destinationList);
    }


}
