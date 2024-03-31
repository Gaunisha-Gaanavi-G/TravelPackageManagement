package org.TravelPackageManager.utils;

import org.TravelPackageManager.models.Activity;
import org.TravelPackageManager.models.Destination;
import org.TravelPackageManager.models.Passenger;
import org.TravelPackageManager.models.TravelPackageImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class PassengerHandler {
    private static List<TravelPackageImpl> travelPackageList;
    PassengerHandler(List<TravelPackageImpl> travelPackageList){
        PassengerHandler.travelPackageList=travelPackageList;
    }

    UserInteractionHandler userInteractionHandler = new UserInteractionHandler();
    ActivityHandler activityHandler = new ActivityHandler(travelPackageList);


    /**
     * Prints all Passengers enrolled in the given Package.
     * <p>Loop through the <tt>Passenger</tt> List and display Passenger name, Passenger balance, Membership type.
     * Redirects the call to <tt>getEnrolledActivities()</tt> which prints the activities enrolled by them.</p>
     * @param travelPackage
     * @see TravelPackageImpl
     * @see Passenger
     */
    public void listPassengers( TravelPackageImpl travelPackage){
        if(travelPackage.getPassengersList()!=null) {
            for (Passenger p : travelPackage.getPassengersList()) {
                System.out.println("Name: "+p.getPassengerName() +
                        " | PassengerNumber: "+p.getPassengerNumber()+
                        " | PassengerBalance: "+p.getBalance()+
                        " | Membership: "+p.getMembership());
                getEnrolledActivities(p,travelPackage);
            }
        }
        else{
            System.out.println("There are no passengers yet. Please enroll in a Travel package first!");
        }
    }


    /**
     * Helper method for fetching the enrolled activities in the given package for a given Passenger.
     * <p>Loop through the Destination List of Travel package and perform a nested loop for Activities n activity list</p>
     * <p> Loop through the Passengers in <tt>getEnrolledPassengers()</tt> using Stream</p>
     * <p>Condition: <br>
     *          <pre>{@code  x->x.getPassengerName().equals(p.getPassengerName()) }</pre>
     *          If the any of the Passenger name in List of Passenger from <tt>getEnrolledPassengers()</tt> matches
     *          with the given Passenger's name, then print the necessary details of the Activity like
     *          Activity name, Cost paid by the user
     *          </p>
     * @param p
     * @param travelPackage
     */
    private void getEnrolledActivities(Passenger p,TravelPackageImpl travelPackage) {
        for(Destination destination: travelPackage.getDestinationList()){
            for(Activity activity: destination.getActivitiesList()){
                if(activity.getEnrolledPassengers()
                        .stream()
                        .anyMatch(x->x.getPassengerName().equals(p.getPassengerName()))){
                    System.out.println("Destination: "+destination.getDestinationName());

                    int cost=0;
                    if(p.getMembership().equals(Membership.STANDARD)){
                        cost = activityHandler.getCostBasedOnPassengerType(Membership.STANDARD,activity.getCost());
                    }
                    else if(p.getMembership().equals(Membership.GOLD)){
                        cost = activityHandler.getCostBasedOnPassengerType(Membership.GOLD,activity.getCost());
                    }

                    System.out.println("ActivityName: " +activity.getActivityName()+ " (Price Paid: " + cost+")");
                    System.out.println("------------------------------------");

                }

            }

        }
    }


    /**
     * Enrolls the passengers and ask user to the enroll for Activities in different destinations.
     * <p>Steps:<ol>
     *     <li>Get user input for the desired Membership type.</li>
     *     <li>Get passenger Name</li>
     *     <li>Enroll passengers to the PassengerList by redirecting call to <tt>addPassenger_totalPassengersList()</tt></li>
     *     <li>Get input from user for choosing activity in the packages. If yes redirect call to <tt>displayActivityChoice()</tt>.
     *     Else Break the loop and go back to getting the input from user for next Passenger</li>
     * </ol> </p>
     * @param travelPackage
     * @see TravelPackageImpl
     * @see Activity
     * @see PassengerHandler
     */
    void choosePassengerTypeForEnroll(TravelPackageImpl travelPackage) {
        int passengerCount=1;
        String passengerChoice="";
        do{
            System.out.println("------------------------------------");
            String membershipMsg = "Choose your desired membership for Passenger " + passengerCount + "\n" +
                    "Standard : You can enroll for activities within a balance of 1000\n" +
                    "Gold : You can enroll for activities within a balance of 2000\n" +
                    "Premium : Unlimited activities!!!\n" +
                    "Enter -1 to go back";


            try {
                passengerChoice = (String) userInteractionHandler.getInputFromUser(membershipMsg, "string");

                String finalPassengerChoice = passengerChoice;
                if(passengerChoice.equalsIgnoreCase("-1"))
                    break;
                if(Arrays.stream(Membership.values()).noneMatch(membership -> membership.name().equalsIgnoreCase(finalPassengerChoice))) throw new InputMismatchException("");

                String passengerName = (String) userInteractionHandler.getInputFromUser("Please enter Passenger" + passengerCount++ + " name: ", "string");

                Passenger enrolledPassenger = addPassenger_totalPassengersList(passengerName, passengerChoice,travelPackage);

                System.out.println("------------------------------------");

                passengerChoice = (String) userInteractionHandler.getInputFromUser("Do you want to enroll for Activities?\n" +
                        "Please enter Yes/No", "string");

                if(passengerChoice.equalsIgnoreCase("yes")){
                    System.out.println("Great! Let's Enroll to an Activity. Here are the Activities we offer in different Destinations under your package:");
                    activityHandler.displayActivityChoice(travelPackage,enrolledPassenger);
                }
                else break;

            }catch(InputMismatchException e){
                System.out.println("Invalid Input!");
            }catch (Exception e){
                System.out.println("An error occurred!");
            }

        }while (!passengerChoice.equalsIgnoreCase("-1"));

        restartEngine();

    }


    /**
     * Helper method for adding passenger to the List of Passengers in <tt>TravelPackageImpl</tt>.
     * <p>If Passenger List is empty, create new ArrayList.</p>
     * <p>Condition:
     *          <li>If passenger capacity of the travel package is greater than the passenger list size,
     *          then add the passenger to the list.</li>
     *          <li>Else log an error message "Sorry Capacity is Full!"</li>
     * </p>
     * @param passengerName
     * @param passengerChoice
     * @param travelPackage
     * @return Passenger
     * @see Passenger
     * @see TravelPackageImpl
     */
    private Passenger addPassenger_totalPassengersList(String passengerName, String passengerChoice,TravelPackageImpl travelPackage){

        PassengerCreator passengerCreator = new PassengerCreator();
        Passenger passenger = passengerCreator.createPassenger(passengerName,passengerChoice);

        System.out.println("Enrolling you to the package...\n");

        if(travelPackage.getPassengersList()==null || travelPackage.getPassengersList().isEmpty()) travelPackage.setPassengersList(new ArrayList<>());

        if(travelPackage.getPassengerCapacity()>travelPackage.getPassengersList().size()){
            List<Passenger> tempList = travelPackage.getPassengersList();
            tempList.add(passenger);
            travelPackage.setPassengersList(tempList);

            System.out.println("\nSUCCESSFULLY enrolled "+passengerName+" to "+travelPackage.getPackageName()+" Package!");
            System.out.println("------------------------------------");

        }else{
            System.out.println("Sorry Capacity is Full! ");
        }

        return passenger;
    }


    /**
     * Restart engine calls displayOperations to redirect the process to the start phase
     */
    private void restartEngine() {
        System.out.println("EXIT!!");
        ControlHandler.displayOperations();
    }
}
