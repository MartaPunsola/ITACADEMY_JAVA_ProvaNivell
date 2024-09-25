package classes;

import enums.Material;

public class DecorationItem extends ExtraItem {

    private String name;
    private Material material;

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

    private double addIva() {
        final double IVA = 0.21;
        super.setPrice((super.getPrice() * IVA) + super.getPrice());
        return super.getPrice();
    }
}
