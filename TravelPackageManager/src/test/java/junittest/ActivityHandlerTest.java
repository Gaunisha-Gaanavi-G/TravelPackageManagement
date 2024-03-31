package junittest;


import junittest.utils.ConsoleOutputCapturer;
import junittest.utils.travelpackages.SampleTravelPackage;
import org.TravelPackageManager.models.Activity;
import org.TravelPackageManager.models.Passenger;
import org.TravelPackageManager.models.TravelPackageImpl;
import org.TravelPackageManager.travelpackages.ManaliTravelPackage;
import org.TravelPackageManager.utils.ActivityHandler;
import org.TravelPackageManager.utils.Membership;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;


public class ActivityHandlerTest {

    TravelPackageImpl travelPackage;
    ActivityHandler activityHandler;

    ConsoleOutputCapturer console;

    @Before
    public void init(){
        console = new ConsoleOutputCapturer();
        travelPackage = new ManaliTravelPackage();
        activityHandler = new ActivityHandler(Collections.singletonList(travelPackage));
    }


    @Test
    public void givenNoActivitiesInDestination_whenListActivitiesInDestination_thenDisplayNone() {
        travelPackage = new SampleTravelPackage();
        activityHandler = new ActivityHandler(Collections.singletonList(travelPackage));
        console.start();
        activityHandler.listActivitiesInDestinations(travelPackage);

        String expected = "\t\t ACTIVITIES IN SampleDestination_NoActivities ARE: \n";
        String actual = console.stop();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenActivitiesInDestination_whenListActivitiesInDestination_thenDisplayCorrectly() {

        console.start();
        activityHandler.listActivitiesInDestinations(travelPackage);

        String expected = "\t\t ACTIVITIES IN Delhi ARE: \n" +
                "\t\t1 Photoshoot - Get exclusive photos of your memorable day \t-- Cost: STANDARD Member(100) | GOLD Member(90) | PREMIUM Member(0) -- Maximum Capacity of: 10\n" +
                "\t\t2 Games - Get to play exciting games with you fellow passengers! \t-- Cost: STANDARD Member(100) | GOLD Member(90) | PREMIUM Member(0) -- Maximum Capacity of: 10\n" +
                "\t\t ACTIVITIES IN Shimla ARE: \n" +
                "\t\t3 Photoshoot - Get exclusive photos of your memorable day \t-- Cost: STANDARD Member(100) | GOLD Member(90) | PREMIUM Member(0) -- Maximum Capacity of: 10\n" +
                "\t\t4 Party at Beach - Get to play exciting games and party with you fellow passengers! \t-- Cost: STANDARD Member(100) | GOLD Member(90) | PREMIUM Member(0) -- Maximum Capacity of: 10\n";
        String actual = console.stop();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenPassengersandActivity_whenEnrollPassengerInActivity_thenAddPassengerToList() {
        String uuid = UUID.randomUUID().toString();
        Passenger passenger = new Passenger.PassengerBuilder("passenger1", uuid, Membership.GOLD).setBalance(100).build();
        Activity testActivity = new Activity("TestActivity", 100, "Sample Activity to test sanity", 1);
        activityHandler.enrollPassengerInActivity(testActivity, passenger);


        Assert.assertEquals(1, testActivity.getEnrolledPassengers().size());
        Assert.assertEquals("passenger1", testActivity.enrolledPassengers.get(0).getPassengerName());
        Assert.assertEquals(uuid, testActivity.enrolledPassengers.get(0).getPassengerNumber());
        Assert.assertEquals(Membership.GOLD, testActivity.enrolledPassengers.get(0).getMembership());

    }

    @Test
    public void displayPassengersEnrolledForActivityTest() {
        String uuid = UUID.randomUUID().toString();
        Passenger passenger = new Passenger.PassengerBuilder("passenger1", uuid, Membership.GOLD).setBalance(100).build();
        Activity testActivity = new Activity("TestActivity", 100, "Sample Activity to test sanity", 1);
        activityHandler.enrollPassengerInActivity(testActivity, passenger);

        console.start();
        activityHandler.displayPassengersEnrolledForActivity(testActivity);

        String expected = "passenger1\n";
        String actual = console.stop();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenInsufficientBalance_whenEnrollPassengerInActivity_thenThrowErrorMessage() {
        String uuid = UUID.randomUUID().toString();
        Passenger passenger = new Passenger.PassengerBuilder("passenger1", uuid, Membership.GOLD).setBalance(10).build();
        Activity testActivity = new Activity("TestActivity", 100, "Sample Activity to test sanity", 1);
        console.start();
        activityHandler.enrollPassengerInActivity(testActivity, passenger);

        String expected = "Insufficient balance!\n" +
                "------------------------------------\n" +
                "Your Balance is 10\n";
        String actual = console.stop();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenPassengerAlreadyEnrolled_whenEnrollPassengerInActivity_thenThrowErrorMessage() {
        String uuid = UUID.randomUUID().toString();
        Passenger passenger = new Passenger.PassengerBuilder("passenger1", uuid, Membership.GOLD).setBalance(100).build();
        Activity testActivity = new Activity("TestActivity", 100, "Sample Activity to test sanity", 1);
        testActivity.enrolledPassengers.add(passenger);


        console.start();
        activityHandler.enrollPassengerInActivity(testActivity, passenger);

        String expected = "You've already enrolled to this activity!\n" +
                "------------------------------------\n" +
                "Your Balance is 100\n";
        String actual = console.stop();

        Assert.assertEquals(expected, actual);
    }

}
