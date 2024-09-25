package enums;

public enum Material {

    WOOD("wood"),
    GLASS("glass"),
    PLASTIC("plastic"),
    METAL("metal");

    private String name;

    private Material(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
