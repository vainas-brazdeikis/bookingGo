package com.model;

import com.model.Car;

import java.util.Arrays;

public class Supplier {
    private String supplier_id;
    private String pickup;
    private String dropoff;
    private Car[] options;

    public Supplier() {

    }

    public Car[] getOptions() {
        return options;
    }

    public void setOptions(Car[] options) {
        Arrays.stream(options).forEach(x -> x.setOwnerOfCar(supplier_id));
        this.options = options;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDropoff() {
        return dropoff;
    }

    public void setDropoff(String dropoff) {
        this.dropoff = dropoff;
    }


}
