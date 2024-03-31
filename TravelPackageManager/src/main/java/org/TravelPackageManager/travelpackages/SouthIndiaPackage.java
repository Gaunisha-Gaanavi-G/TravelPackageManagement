package org.TravelPackageManager.travelpackages;

import org.TravelPackageManager.destinations.Goa;
import org.TravelPackageManager.destinations.Hampi;
import org.TravelPackageManager.destinations.Mysore;
import org.TravelPackageManager.models.Destination;
import org.TravelPackageManager.models.TravelPackageImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SouthIndiaPackage extends TravelPackageImpl {
    public SouthIndiaPackage() {
        super(packageName, passengerCapacity, destinationList);
    }
    private static final String packageName = "SOUTH INDIA TRAVEL PACKAGE";
    private static final int passengerCapacity = 25;
    private static final List<Destination> destinationList;

    static {
        destinationList = new ArrayList<>(
                Arrays.asList(
                        new Goa(),
                        new Mysore(),
                        new Hampi()
                )
        );
    }


}
