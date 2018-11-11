package com.bookingGo.provider;

import com.bookingGo.model.Car;
import com.bookingGo.model.Supplier;
import com.bookingGo.utils.Utils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.util.*;

public class DataConstructor {

    private static DataConstructor constructor = null;
    private final int TIMEOUT = 2000;
    private final String PICK_UP = "pickup";
    private final String DROP_OFF = "dropoff";
    private final Map<String, Integer> carTypes;
    private Client client;
    private WebResource webResource;

    private Map<String, Car> suppliersOffers;

    private DataConstructor() {
        client = Client.create();
        client.setConnectTimeout(TIMEOUT);
        carTypes = Utils.getCarTypeMap();
        webResource = client.resource(Utils.URL);
    }

    public static DataConstructor getDataConstructor() {
        if (constructor == null) {
            constructor = new DataConstructor();
        }
        return constructor;
    }

    public Set<Car> getSuppliersOffers(double xPickUp, double yPickUp, double xDropOff, double yDropOff, Integer maxSeatNumber) {
        suppliersOffers = new HashMap<>();

        for (String supplier : Utils.SUPPLIERS) {
            Supplier newSupp = read(supplier, xPickUp, yPickUp, xDropOff, yDropOff);
            if (newSupp != null) {

                List<Car> cars = Arrays.asList(newSupp.getOptions());

                cars.stream().filter(x -> carTypes.get(x.getCar_type()).equals(maxSeatNumber))
                        .forEach(x -> suppliersOffers.put(x.getCar_type(), !suppliersOffers.containsKey(x.getCar_type()) || suppliersOffers.get(x.getCar_type()).getPrice() > x.getPrice()
                                ? x : suppliersOffers.get(x.getCar_type())));
            }

        }

        Set<Car> c = Utils.sortResult(suppliersOffers);
        System.out.println(c.size());
        return c;
    }

    private Supplier read(String supplierName, double xPickUp, double yPickUp, double xDropOff, double yDropOff) {

        Supplier supplier = null;
        try {
            ClientResponse response = webResource.path(supplierName)
                    .queryParam(PICK_UP, String.valueOf(xPickUp))
                    .queryParam(PICK_UP, String.valueOf(yPickUp))
                    .queryParam(DROP_OFF, String.valueOf(xDropOff))
                    .queryParam(DROP_OFF, String.valueOf(yDropOff)).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
            supplier = response.getEntity(Supplier.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return supplier;
    }
}
