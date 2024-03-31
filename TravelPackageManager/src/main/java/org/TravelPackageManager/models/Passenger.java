package org.TravelPackageManager.models;


import org.TravelPackageManager.utils.Membership;


/**
 * <b>Pattern used: </b> Builder Pattern
 * <p><b>Definition: </b>Builder is a design pattern that lets you construct complex objects step by step.
 * The pattern allows you to produce different types and representations of an object using the same construction code.
 *
 * <p>Reference: <a href="https://refactoring.guru/design-patterns/builder">Builder Pattern - Refactoring guru</a>
 *
 * <p><b>Explanation: </b>
 *      <p> There are 2 classes. One is the outer class with private constructor which accepts <tt>PassengerBuilder</tt> as the parameter.
 *      Another inner class which is the <tt>PassengerBuilder</tt> class which is a static class, builds the object.
 *      <p>There is a condition to set the balance to the Passenger object based on the Membership type.
 *      So, move the conditional variable setter to the builder inner class and have the return type as
 *      the builder class's object so that the build method needs to be called by the returned reference of <tt>PassengerBuilder</tt> class.</p>
 *
 *      <p>The public constructor in <tt>PassengerBuilder</tt> will set the required params and we need to call the optional params wherever required.</p>
 *      <p>Have a build method which calls the constructor of the outer class which is <tt>Passenger</tt> class.</p>
 * @see org.TravelPackageManager.utils.PassengerCreator
 */
public class Passenger {
    private final String passengerName;
    private final String passengerNumber;
    public int balance;

    private Membership membership;

    private Passenger(PassengerBuilder builder){
        passengerName = builder.passengerName;
        passengerNumber = builder.passengerNumber;
        balance = builder.balance;
        membership = builder.membership;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public int getBalance() {
        return balance;
    }

    public String getPassengerNumber() {
        return passengerNumber;
    }

    public Membership getMembership() {
        return membership;
    }

    public static class PassengerBuilder{
        final String passengerName;
        final String passengerNumber;
        int balance;
        final Membership membership;
        public PassengerBuilder(String passengerName, String passengerNumber,Membership membership){
            this.passengerName=passengerName;
            this.passengerNumber=passengerNumber;
            this.membership = membership;
        }

        public PassengerBuilder setBalance(int balance){
            this.balance=balance;
            return this;
        }

        /**
         * Building the object using parent class constructor and returning the Passenger class object.
         * @return Passenger object
         */
        public Passenger build(){
            return new Passenger(this);
        }
    }
}
