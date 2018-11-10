package com.provider;

import com.model.Car;
import com.model.Supplier;
import com.utils.Utils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.*;

public class DataConstructor {

    private static DataConstructor constructor = null;
    private final String PICK_UP = "pickup";
    private final String DROP_OFF = "dropoff";
    private final Map<String, Integer> carTypes;
    private Client client;
    private WebTarget webTarget;
    private Map<String, Car> suppliersOffers;

    private DataConstructor() {
        client = ClientBuilder.newClient(Utils.getConncetionConfig());
        carTypes = Utils.getCarTypeMap();
        webTarget = client.target(Utils.URL);

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

        return Utils.sortResult(suppliersOffers);
    }

    private Supplier read(String supplierName, double xPickUp, double yPickUp, double xDropOff, double yDropOff) {
        WebTarget supplierWebTarget = webTarget.path(supplierName)
                .queryParam(PICK_UP, xPickUp)
                .queryParam(PICK_UP, yPickUp)
                .queryParam(DROP_OFF, xDropOff)
                .queryParam(DROP_OFF, yDropOff);

        Invocation.Builder invocationBuilder = supplierWebTarget.request(MediaType.APPLICATION_JSON);
        Supplier supplier = null;

        try {
            supplier = invocationBuilder.get(Supplier.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return supplier;
    }
}
