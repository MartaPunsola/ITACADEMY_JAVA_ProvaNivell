package model;

import enums.Theme;
import management.Calculation;


public class Clue extends RoomElement implements Calculation {

    private String name;
    private String estimatedTime;
    private Theme theme;

    private static final double IVA = 0.10;

    public Clue(double price, String name, String estimatedTime, Theme theme) {
        super(price);
        this.addIva();
        this.name = name;
        this.estimatedTime = estimatedTime;
        this.theme = theme;
    }

    public String getName() {
        return this.name;
    }

    public String getEstimatedTime() {
        return this.estimatedTime;
    }

    public Theme getTheme() {
        return this.theme;
    }

    @Override
    public double addIva() {
        super.setPrice((super.getPrice() * Clue.IVA) + super.getPrice());
        return super.getPrice();
    }
}
