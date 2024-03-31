package org.TravelPackageManager.models;

import java.util.List;


/**
 * TravelPackageImp abstract Class having required params and its Getters and Setters.
 * <p><b>Usage: </b>This class is used to create the Travel package objects.
 * <p>Having a parametrised constructor for the final variable, the passengerList variable is not final
 * since there will be a constance variation in the List as the Passengers get added.
 *
 */
public abstract class TravelPackageImpl {
    private final String packageName;
    private final int passengerCapacity;
    private final List<Destination> destinationList;
    private List<Passenger> passengersList;

    public TravelPackageImpl(String packageName, int passengerCapacity, List<Destination> destinationList){
        this.packageName = packageName;
        this.passengerCapacity = passengerCapacity;
        this.destinationList = destinationList;
    }

    public String getPackageName() {
        return packageName;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public List<Destination> getDestinationList() {
        return destinationList;
    }

    public void setPassengersList(List<Passenger> passengersList) {
        this.passengersList = passengersList;
    }

    public List<Passenger> getPassengersList() {
        return passengersList;
    }
}
