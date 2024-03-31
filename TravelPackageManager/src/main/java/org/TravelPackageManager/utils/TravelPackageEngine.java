package org.TravelPackageManager.utils;

import org.TravelPackageManager.models.Destination;
import org.TravelPackageManager.models.TravelPackageImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.List;

public class TravelPackageEngine {

    private final static List<TravelPackageImpl> travelPackageList;

    static {
        try{
            travelPackageList = LoadPackages.loadTravelPackageObjects();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    ActivityHandler activityHandler = new ActivityHandler(travelPackageList);
    PassengerHandler passengerHandler = new PassengerHandler(travelPackageList);

    UserInteractionHandler userInteractionHandler = new UserInteractionHandler();
    TravelPackageImpl travelPackage;


    /**
     * Prints all the available Packages
     * <p>Gets user input for the desired package and redirects the call to <tt>displayOptionsInPackage()</tt></p>
     */
    public void listTravelPackages() {
        int pckgNumber=0;

        do{
            try {

                int packageNumber = 1;
                System.out.println("------------------------------------");
                System.out.println("NO | PACKAGE NAME");
                for(TravelPackageImpl item: travelPackageList){
                    System.out.println(packageNumber++ +" - "+item.getPackageName());
                }

                pckgNumber = (Integer) userInteractionHandler.getInputFromUser("Choose your desired package\nEnter -1 to exit", "integer");

                if(pckgNumber ==-1) break;
                travelPackage = travelPackageList.get(pckgNumber-1);

                displayOptionsInPackage();

            }catch(InputMismatchException e){
                System.out.println("Invalid Input!");
            }catch (Exception e){
                System.out.println("An error occurred!");
            }


        }while(pckgNumber>0);

        restartEngine();

    }


    /**
     * Prints the different options that can be done in the chosen package.
     * <ol>
     *     <li><b>Itinerary of Package</b> - Displays the complete information of the package</li>
     *     <li><b>Passenger List</b> - Displays the Passenger's name who have enrolled in this package along with their other details like
     *     Activities enrolled, Cost paid by them for enrolling, Membership type, etc.</li>
     *     <li><b>Enroll in!</b> - Enrolls the Passenger to the current package.</li>
     * </ol>
     * @see UserInteractionHandler
     */
    private void displayOptionsInPackage() {
        int userInput=0;
        do{
            try{
                System.out.println("------------------------------------");
                userInput = (Integer) userInteractionHandler.getInputFromUser("Choose your desired Operation in :" +
                        travelPackage.getPackageName()+"\n"+
                        "1. Itinerary of Package\n" +
                        "2. Passenger List\n" +
                        "3. Enroll in!\n" +
                        "Enter -1 to exit", "integer");

                chooseOptionsInPackage(userInput);

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!");
            } catch (IOException | RuntimeException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (Exception e){
                System.out.println("An error occurred!");
            }
        }while (userInput>0);
    }


    /**
     * Redirection of method calls according to the user's choice <br><br>
     * <b>Cases:</b>
     * <ol>
     *     <li><b><tt>viewCompleteInfoOfPackage()</tt></b>: The call is redirected to this method,
     *     where it displays the complete information of the package</li>
     *     <li><b><tt>passengerHandler.listPassengers()</tt></b>: The call is redirected to this method,
     *     where it displays the Passenger's name who have enrolled in this package along with their other details like
     *     Activities enrolled, Cost paid by them for enrolling, Membership type, etc.</li>
     *     <li><b><tt>passengerHandler.choosePassengerTypeForEnroll()</tt></b>: Enrolls the Passenger to the current package.</li>
     * </ol>
     * @param input
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     *
     * @see PassengerHandler
     */
    public void chooseOptionsInPackage(int input) throws IOException, InstantiationException, IllegalAccessException {
        switch(input){
            case 1:
                viewCompleteInfoOfPackage();
                break;
            case 2:
                passengerHandler.listPassengers(travelPackage);
                break;
            case 3:
                passengerHandler.choosePassengerTypeForEnroll(travelPackage);
                break;
            default:
                System.out.println("Invalid input!");
        }
    }


    /**
     * Method for Displaying complete information about the package.
     * <p>
     *     Displays the following:
     *     <li>Package Name</li>
     *     <li>List of Destinations in the package</li>
     *     <li> List of Activities in each destination in the package</li>
     * </p>
     */
    public void viewCompleteInfoOfPackage(){
        System.out.println("------------------------------------");
        System.out.println("COMPLETE INFORMATION OF PACKAGE:");
        System.out.println(travelPackage.getPackageName());
        displayDestinationsInPackage();
        activityHandler.listActivitiesInDestinations(travelPackage);
        System.out.println("------------------------------------");
    }


    /**
     * Helper method for displaying activities of the user chosen destination
     * <p>Displays a number for activity and the Activity Name</p>
     */
    private void displayDestinationsInPackage() {
        List<Destination> listOfDestinations = travelPackage.getDestinationList();
        int destinationNumber=1;
        System.out.println("\tHERE ARE THE DESTINATIONS IN "+travelPackage.getPackageName());
        for(Destination destination: listOfDestinations)
            System.out.println("\t"+destinationNumber++ +" "+destination.getDestinationName());
    }



    /**
     * Restart engine calls displayOperations to redirect the process to the start phase
     */
    private void restartEngine() {
        System.out.println("EXIT!!");
        ControlHandler.displayOperations();
    }
}
