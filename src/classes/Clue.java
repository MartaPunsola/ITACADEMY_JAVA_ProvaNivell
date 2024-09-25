package classes;

import enums.Theme;

public class Clue extends ExtraItem {

    private String name;
    private String estimatedTime;
    private Theme theme;

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

    private double addIva() {
        final double IVA = 0.10;
        super.setPrice((super.getPrice() * IVA) + super.getPrice());
        return super.getPrice();
    }
}
