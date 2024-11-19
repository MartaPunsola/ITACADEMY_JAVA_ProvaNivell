package model;

import java.util.UUID;

public abstract class RoomElement {

    private String id;
    private double price;

    public RoomElement(double price) {
        this.id = UUID.randomUUID().toString();
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
