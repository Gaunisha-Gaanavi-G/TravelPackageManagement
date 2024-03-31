package org.TravelPackageManager;

import org.TravelPackageManager.utils.ControlHandler;

/**
 * Beginning of the Program is here. Running the Main method will internally redirect a call to <tt>controlHandler.startEngine()</tt>
 * @see ControlHandler
 */
public class Main {

    public static void main(String[] args) {
        ControlHandler controlHandler = new ControlHandler();
        controlHandler.startEngine();
    }


}