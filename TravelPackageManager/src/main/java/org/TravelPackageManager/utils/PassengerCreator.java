package org.TravelPackageManager.utils;

import org.TravelPackageManager.models.Passenger;

import java.util.UUID;

public class PassengerCreator {

    private Passenger passenger;

    /**
     * Create <tt>Passenger</tt> object for Standard Membership type.
     * <p><b>Object Creation:</b></p>
     * <pre>{@code
     * Passenger.PassengerBuilder().setBalance().build()
     * }</pre>
     *
     * The above call is used for creating the object. We are using Builder pattern for creating objects here.
     * <p>Look into {@link org.TravelPackageManager.models.Passenger} to see how the object is created.</p>
     * <p> Setting the balance since a STANDARD Member type should have a balance.</p>
     * @param name
     * @return Passenger object
     * @see Membership
     * @see Passenger
     */
    private Passenger createStandardPassenger(String name){
        passenger = new Passenger.PassengerBuilder(name, UUID.randomUUID().toString(),Membership.STANDARD).setBalance(Membership.STANDARD.balance).build();

        return passenger;
    }


    /**
     * Create <tt>Passenger</tt> object for GOLD Membership type.
     * <p><b>Object Creation:</b></p>
     * <pre>{@code
     * Passenger.PassengerBuilder().setBalance().build()
     * }</pre>
     *
     * The above call is used for creating the object. We are using Builder pattern for creating objects here.
     * <p>Look into {@link org.TravelPackageManager.models.Passenger} to see how the object is created.</p>
     * <p> Setting the balance since a GOLD Member type should have a balance.</p>
     * @param name
     * @return Passenger object
     * @see Membership
     * @see Passenger
     */
    private Passenger createGoldPassenger(String name){
        passenger = new Passenger.PassengerBuilder(name, UUID.randomUUID().toString(),Membership.GOLD).setBalance(Membership.GOLD.balance).build();

        return passenger;
    }


    /**
     * Create <tt>Passenger</tt> object for PREMIUM Membership type.
     * <p><b>Object Creation:</b></p>
     * <pre>{@code
     * Passenger.PassengerBuilder().build()
     * }</pre>
     *
     * The above call is used for creating the object. We are using Builder pattern for creating objects here.
     * <p>Look into {@link org.TravelPackageManager.models.Passenger} to see how the object is created.</p>
     * <p> Note that <tt>setBalance()</tt> is not called here since a PREMIUM member does not need a balance. everything is unlimited.</p>
     * @param name
     * @return Passenger object
     * @see Membership
     * @see Passenger
     */
    private Passenger createPremiumPassenger(String name){
        passenger = new Passenger.PassengerBuilder(name, UUID.randomUUID().toString(),Membership.PREMIUM).build();

        return passenger;
    }


    /**
     * Creating the passenger object by calling equivalent method based on the Membership type
     * <p><b>Cases:</b></p>
     * <li><b>Gold:</b> calls <tt>createGoldPassenger()</tt> for creating GOLD type Passenger object.</li>
     * <li><b>Standard:</b> calls <tt>createStandardPassenger()</tt> for creating STANDARD type Passenger object.</li>
     * <li><b>Premium:</b> calls <tt>createPremiumPassenger()</tt> for creating PREMIUM type Passenger object.</li>
     * @param passengerName
     * @param type
     * @return
     */
    public Passenger createPassenger(String passengerName, String type){

        switch (type.toLowerCase()){
            case "gold":
                return createGoldPassenger(passengerName);
            case "standard":
                return createStandardPassenger(passengerName);
            case "premium":
                return createPremiumPassenger(passengerName);
            default:
                System.out.println("Please give a valid value!");
        }
        return null;
    }
}
