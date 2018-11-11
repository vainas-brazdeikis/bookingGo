package com.bookingGo.model;

public class Car {
    private String car_type;
    private Integer price;
    private String supplier_id;

    public String getOwnerOfCar() {
        return supplier_id;
    }

    public void setOwnerOfCar(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return car_type + " " + supplier_id + " " + price;
    }


}
