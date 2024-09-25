package enums;

public enum Theme {

    NUMBER("number"),
    LETTER("letter"),
    IMAGE("image");

    private String name;

    private Theme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
