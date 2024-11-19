package enums;

import exception.ValueNotFoundException;

import java.util.Arrays;
import java.util.Optional;

public enum Level {

    BEGINNER(1, "beginner"),
    INTERMEDIATE(2, "intermediate"),
    EXPERT(3, "expert");

    private final int VALUE;
    private String levelName;

    private Level(int VALUE, String levelName) {
        this.VALUE = VALUE;
        this.levelName = levelName;
    }

    public int getVALUE() {
        return VALUE;
    }

    public String getLevelName() {
        return levelName;
    }

    public static Optional<Level> findByValue(int value) throws ValueNotFoundException {
        Optional<Level> levelFound = Arrays.stream(values())
                .filter(level -> level.getVALUE() == value)
                .findFirst();
        if(levelFound.isEmpty()) {
            throw new ValueNotFoundException("This option is not valid.");
        }
        return levelFound;
    }
}
