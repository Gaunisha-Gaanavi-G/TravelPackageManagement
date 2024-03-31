package org.TravelPackageManager.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class UserInteractionHandler {
    public Object getInputFromUser(String msg, String obj) throws IOException, InstantiationException, IllegalAccessException {
        System.out.println(msg);
        Object res = readUserInput(obj);
        return res;
    }

    private Object readUserInput(String o) throws IOException
    {
        Object result=null;
        BufferedReader userInputBReader = new BufferedReader(new InputStreamReader(System.in));
        if(o.equalsIgnoreCase("String")){
            result = userInputBReader.readLine();
            return result;
        }
        else if(o.equalsIgnoreCase("Integer")){
            try{
                result = Integer.parseInt(userInputBReader.readLine());
                return result;
            }catch (NumberFormatException e){
                System.out.println("Not an Integer!");
                throw new InputMismatchException("");
            }
        }
        return o;
    }
}
