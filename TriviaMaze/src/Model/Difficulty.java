package Model;

public enum Difficulty {

    EASY("Easy"),

    MEDIUM("Medium"),

    HARD("Hard"),

    EXTREME("Extreme");

    private final String myName;

    Difficulty(final String theName) {
        myName = theName;
    }

    public String getName() {
        return myName;
    }
}
