package com.bookingGo.utils;

import com.bookingGo.model.Car;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import java.util.*;

public class Utils {
    public final static String[] SUPPLIERS = new String[]{"dave", "eric", "jeff"};
    public static final String URL = "https://techtest.rideways.com/";

    public static int getRequiredCarType(int passengers) {
        if (passengers <= 4) {
            return 4;
        } else if (passengers <= 6) {
            return 6;
        }
        return 16;
    }

    public static Map<String, Integer> getCarTypeMap() {
        HashMap<String, Integer> carTypes = new HashMap<>();
        carTypes.put("STANDARD", 4);
        carTypes.put("EXECUTIVE", 4);
        carTypes.put("LUXURY", 4);
        carTypes.put("PEOPLE_CARRIER", 6);
        carTypes.put("LUXURY_PEOPLE_CARRIER", 6);
        carTypes.put("MINIBUS", 16);
        return carTypes;
    }

    public static Set<Car> sortResult(Map<String, Car> suppliersOffers) {
        Comparator<Car> comp = (Car c1, Car c2) -> (c1.getPrice().compareTo(c2.getPrice()));
        Set<Car> result = new TreeSet<>(comp);
        for (Map.Entry<String, Car> map : suppliersOffers.entrySet()) {
            result.add(map.getValue());
        }
        return result;
    }
}
