package org.TravelPackageManager.utils;

import org.TravelPackageManager.models.Activity;
import org.TravelPackageManager.models.Destination;
import org.TravelPackageManager.models.Passenger;
import org.TravelPackageManager.models.TravelPackageImpl;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivityHandler {
    public List<TravelPackageImpl> travelPackageList;

    public ActivityHandler(List<TravelPackageImpl> travelPackageList){
        this.travelPackageList=travelPackageList;
    }

    UserInteractionHandler userInteractionHandler = new UserInteractionHandler();


    /**
     * Prints List of all Activities in all Destinations of the given Travel Package
     * @param travelPackage
     * @see Activity
     * @see Destination
     */
    public void listActivitiesInDestinations(TravelPackageImpl travelPackage){
        List<Destination> destinations = travelPackage.getDestinationList();
        AtomicInteger activityNumber= new AtomicInteger(1);
        for(Destination destination: destinations){
            System.out.println("\t\t ACTIVITIES IN "+destination.getDestinationName()+ " ARE: ");

            destination.getActivitiesList().forEach(activity->{
                System.out.println("\t\t"+ activityNumber.getAndIncrement() +
                        " "+activity.getActivityName() +
                        " - "+activity.getDescription()+
                        " \t-- Cost: STANDARD Member("+getCostBasedOnPassengerType(Membership.STANDARD,activity.getCost())+") | "+
                        "GOLD Member("+getCostBasedOnPassengerType(Membership.GOLD,activity.getCost())+") | "+
                        "PREMIUM Member("+getCostBasedOnPassengerType(Membership.PREMIUM,activity.getCost())+")"+
                        " -- Maximum Capacity of: "+activity.getCapacity());
            });
        }
    }

    /**
     * Prints all Passenger Names enrolled in given Activity
     * @param activity
     * @see Activity
     */
    public void displayPassengersEnrolledForActivity(Activity activity){
        for(Passenger p: activity.getEnrolledPassengers())
            System.out.println(p.getPassengerName());
    }


    /**
     * Helper method for displaying Destinations in the user chosen package
     * <p>Loop through the list of destinations and print it along with an incremental value for option</p>
     * @param travelPackage
     * @see TravelPackageImpl
     * @see Destination
     */
    private void displayDestinationsInPackage(TravelPackageImpl travelPackage) {
        List<Destination> listOfDestinations = travelPackage.getDestinationList();
        int destinationNumber=1;
        System.out.println("\tHERE ARE THE DESTINATIONS IN "+travelPackage.getPackageName());
        for(Destination destination: listOfDestinations)
            System.out.println("\t"+destinationNumber++ +" "+destination.getDestinationName());
    }


    /**
     * Method to display all activities in chosen destination.
     *
     * <p>Displays all Destinations in the given package, and gets the input for the desired package.</p>
     * <p>Prints all the Activities in the chosen package and re-directs the call to <tt>getUserChoiceOfActivity()</tt></p>
     *
     * @param travelPackage
     * @param passenger
     * @see TravelPackageImpl
     * @see Passenger
     */
    public void displayActivityChoice(TravelPackageImpl travelPackage,Passenger passenger) {

        int userInput=0;
        do{
            try {
                displayDestinationsInPackage(travelPackage);
                userInput = (int) userInteractionHandler.getInputFromUser("Choose a Destination to enroll in its activity" +
                        "\nEnter -1 to go back","integer");
                if(userInput<0) break;

                displayActivitiesInCurrentDestination(travelPackage.getDestinationList().get(userInput-1));
                getUserChoiceOfActivity(travelPackage.getDestinationList().get(userInput-1),passenger);

            }catch (InputMismatchException e){
                System.out.println("Invalid Input!");
            }
            catch (IOException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }while(userInput!=-1);

    }


    /**
     * Helper method for displaying activities of the user chosen destination
     *
     *<p>Displays a number for activity and the Activity Name</p>
     * @param destination
     * @see Destination
     * @see Activity
     */
    private void displayActivitiesInCurrentDestination(Destination destination) {
        System.out.println("Activites in "+destination.getDestinationName());

        int activityNumber=1;
        for( Activity activity: destination.getActivitiesList()){
            System.out.println(activityNumber++ +" "+activity.getActivityName()+"\n");
        }
    }


    /**
     * Helper method for enrolling passenger to the Activity.
     * <p>Gets user input for the desired activity option and redirects the call to <tt>enrollPassengerInActivity()</tt>
     * for enrolling the passenger to the activity</p>
     * @param destination
     * @param passenger
     */
    private void getUserChoiceOfActivity(Destination destination,Passenger passenger){

        int userChoiceOfActivity=0;
        do{
            try {
                System.out.println("------------------------------------");
                userChoiceOfActivity = (int) userInteractionHandler.getInputFromUser("Choose Activity to enroll:\n Enter -1 to go back","integer");

                if(userChoiceOfActivity==-1) break;

                enrollPassengerInActivity(destination.getActivitiesList().get(userChoiceOfActivity-1),passenger);


            }catch(InputMismatchException e){
                System.out.println("Invalid Input!");
            }catch (IOException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }while (userChoiceOfActivity!=-1);
    }



    /**
     * Enroll the provided Passenger to the Activity provided
     *
     * <p>If there is capacity for enrolling the user to the activity(<tt>activity.getRemainingCapacity()>=1</tt>),
     * then adding the passenger to the <tt>enrolledPassengers</tt>> list.
     * Get the cost of reduction from <tt>getCostBasedOnPassengerType()</tt> and reduce the passenger's balance based on <tt>Membership</tt> type.</p>
     *
     * <p>Reduce the capacity of activity by one after enrolling the passenger.</p>
     *
     *
     * <p>Error Handling:
     *  <ol>
     *      <li>If the user is already registered to the activity, log an error "You've already enrolled to this activity!"</li>
     *      <li>If there is no insufficient balance for the passenger, log an error "Insufficient balance!" </li>
     *  </ol>
     * </p>
     * @param activity
     * @param passenger
     * @see Passenger
     * @see Activity
     */
    public void enrollPassengerInActivity(Activity activity, Passenger passenger){
        if(activity.getRemainingCapacity()>=1){
            try {

                if(activity.getEnrolledPassengers()
                        .stream()
                        .noneMatch(x->x.getPassengerName().equals(passenger.getPassengerName()))){
                    activity.enrolledPassengers.add(passenger);

                    int costForActivity=getCostBasedOnPassengerType(passenger.getMembership(),activity.getCost());

                    if(passenger.balance>=costForActivity){
                        passenger.balance-=costForActivity;

                        activity.remainingCapacity--;

                        System.out.println("SUCCESSFULLY ENROLLED IN "+activity.getActivityName());

                    }else{
                        System.out.println("Insufficient balance!");
                    }

                }else{
                    System.out.println("You've already enrolled to this activity!");
                }

                System.out.println("------------------------------------");
                if(passenger.getMembership().equals(Membership.GOLD) || passenger.getMembership().equals(Membership.STANDARD))
                    System.out.println("Your Balance is "+passenger.getBalance());




            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println("Sorry the capacity is FULL!");
        }
    }


    /**
     * Helper method for calculating the cost to be reduced for the Passenger for each Activity
     *
     * Consitions:
     * <ol>
     *     <li>If <tt>Membership</tt> is PREMIUM return 0</li>
     *     <li>If <tt>Membership</tt> is GOLD return 10% of cost</li>
     *     <li>If <tt>Membership</tt> is GOLD return 10% of cost</li>
     * </ol>
     * @param type
     * @param cost
     * @return int - Cost to be reduced
     * @see Membership
     * @see Activity
     */
    int getCostBasedOnPassengerType(Membership type, int cost){
        if(type.equals(Membership.PREMIUM))
            return 0;
        else if(type.equals(Membership.GOLD))
            return cost-(cost/10);
        return cost;
    }

}
