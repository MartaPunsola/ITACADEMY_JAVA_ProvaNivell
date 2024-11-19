package model;

import enums.Material;
import management.Calculation;

public class DecorationItem extends RoomElement implements Calculation {

    private String name;
    private Material material;

    private static final double IVA = 0.21;

    public DecorationItem(double price, String name, Material material) {
        super(price);
        this.addIva();
        this.name = name;
        this.material = material;
    }

    public String getName() {
        return this.name;
    }

    public Material getMaterial() {
        return this.material;
    }

    @Override
    public double addIva() {
        super.setPrice((super.getPrice() * DecorationItem.IVA) + super.getPrice());
        return super.getPrice();
    }
}
