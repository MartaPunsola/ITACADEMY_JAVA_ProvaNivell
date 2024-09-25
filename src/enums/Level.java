package enums;

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

    public int getValue() {
        return VALUE;
    }

    public String getLevelName() {
        return levelName;
    }

    public static Level findByValue(int value) {
        for (Level level : values()) {
            if (level.getValue() == value) {
                return level;
            }
        }
        return null;
    }
}
