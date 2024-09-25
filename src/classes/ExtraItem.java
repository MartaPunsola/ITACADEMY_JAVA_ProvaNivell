package classes;

import java.util.UUID;

public class ExtraItem {

    private String id;
    private double price;

    public ExtraItem(double price) {
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
