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

    public int getSize(final String theIn) {
        if(theIn.equals("Easy")) {
            return 4;
        } else if (theIn.equals("Medium")) {
            return 5;
        } else if(theIn.equals("Hard")) {
            return 6;
        } else {
            return 7;
        }
    }
}