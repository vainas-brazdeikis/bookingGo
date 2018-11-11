package com.bookingGo;

import com.bookingGo.model.Car;
import com.bookingGo.provider.DataConstructor;
import com.bookingGo.utils.Utils;

import java.util.Scanner;
import java.util.Set;

public class App {
    private static DataConstructor data;
    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        data = DataConstructor.getDataConstructor();
        startApp();
    }

    private static void startApp() {
        String s = "";
        double xp, yp, xd, yd;
        int seats;
        System.out.println("Make input 'search' to start search");
        while (!s.equals("quit")) {
            s = sc.nextLine();
            if (s.equals("search")) {
                try {
                    System.out.println("Pick up x latitude");
                    xp = sc.nextDouble();

                    System.out.println("Pick up y latitude");
                    yp = sc.nextDouble();

                    System.out.println("Drop off x latitude");
                    xd = sc.nextDouble();

                    System.out.println("Drop off y latitude");
                    yd = sc.nextDouble();

                    System.out.println("Seats required");
                    seats = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Input mismatch");
                    continue;
                }
                Set<Car> cars = data.getSuppliersOffers(xp, yp, xd, yd, Utils.getRequiredCarType(seats));
                System.out.println("Search Results");
                for (Car c : cars) {
                    System.out.println(c.toString());
                }
            }
        }
        System.out.println("Search off");
    }


}