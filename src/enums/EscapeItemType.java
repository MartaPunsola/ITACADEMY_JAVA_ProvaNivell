package enums;

import exception.ValueNotFoundException;

import java.util.Arrays;

public enum EscapeItemType {

    ROOM(1, "room"),
    CLUE(2, "clue"),
    DECORATION(3, "decoration item");

    private final int VALUE;
    private String typeName;

    private EscapeItemType(int VALUE, String typeName) {
        this.VALUE = VALUE;
        this.typeName = typeName;
    }

    public int getVALUE() {
        return VALUE;
    }

    public String getTypeName() {
        return typeName;
    }

    public static EscapeItemType findByValue(int value) throws ValueNotFoundException {
        EscapeItemType typeFound = Arrays.stream(values())
                .filter(type -> type.getVALUE() == value)
                .findFirst().orElse(null);
        if(typeFound == null) {
            throw new ValueNotFoundException("This option is not valid.");
        }
        return typeFound;
    }
}
