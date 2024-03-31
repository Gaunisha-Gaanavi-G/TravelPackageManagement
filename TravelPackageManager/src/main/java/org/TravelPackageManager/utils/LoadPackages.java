package org.TravelPackageManager.utils;

import org.TravelPackageManager.models.TravelPackageImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

enum Packages {
    MANALI("ManaliTravelPackage"),
    SOUTH_INDIA_PACKAGE("SouthIndiaPackage");

    final String className;

    Packages(String className) {
        this.className = className;
    }

}

public class LoadPackages {
    static List<TravelPackageImpl> listOfTravelPackages = new ArrayList<>();

    private final static String classFilePathPrefix = "org.TravelPackageManager.travelpackages.";
    public static List<TravelPackageImpl> loadTravelPackageObjects() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for(Packages item: Packages.values()){
            Class<?> clazz = Class.forName(classFilePathPrefix+item.className);
            Constructor<?> ctor = clazz.getConstructor();
            listOfTravelPackages.add((TravelPackageImpl) ctor.newInstance());
        }
        return listOfTravelPackages;
    }
}
