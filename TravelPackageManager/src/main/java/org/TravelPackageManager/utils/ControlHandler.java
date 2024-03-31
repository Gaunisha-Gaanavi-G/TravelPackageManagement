package org.TravelPackageManager.utils;

import java.util.InputMismatchException;

public class ControlHandler {

    static TravelPackageEngine travelPackageEngine;
    static UserInteractionHandler userInteractionHandler;

    public ControlHandler(){
        travelPackageEngine = new TravelPackageEngine();
        userInteractionHandler = new UserInteractionHandler();
    }

    /**
     * Start of the program.
     */
    public void startEngine() {
        System.out.println("----------------------------------------------\n" +
                "Hi Welcome to Travel Package Manager!\n" +
                "Have a look at the packages which we offer!\n");

        displayOperations();
    }

    /**
     * Displays different operations which the user can do.
     * <p>Currently only one operation is there which is - "View Available Packages"</p>
     * <p> Gets user input and redirects the call to chooseOperation()</p>
     */
    public static void displayOperations() {
        int operationNumber=0;
        do{
            try{
                System.out.println("------------------------------------");

                operationNumber = (int) userInteractionHandler.getInputFromUser("Choose your desired Operation:\n" +
                        "1. View Available Packages\n" +
                        "Enter -1 to exit", "integer");

                if(operationNumber>0) chooseOperation(operationNumber);

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!");
            } catch (Exception e){
                System.out.println("An error occurred!");
            }


        }while(operationNumber!=-1);

        System.out.println("EXIT!!\nTHANK YOU :)");
        System.out.println("------------------------------------");

        System.exit(0);
    }


    /**
     * Redirects call to the according methods
     * <p>Conditions:
     *      <ol>
     *          <li>If chosen operation is 1, redirect call to <tt>listTravelPackages()</tt></li>
     *          <li>Else throw "Invalid output" error.</li>
     *      </ol>
     * </p>
     * @param operationNumber
     */
    private static void chooseOperation(int operationNumber) {
        if (operationNumber == 1) {
            travelPackageEngine.listTravelPackages();
        } else {
            System.out.println("Invalid input!");
        }
    }
}
