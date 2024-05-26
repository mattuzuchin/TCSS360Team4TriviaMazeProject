package Model;

/**
 * @author Matthew Uzunoe-Chin, Elias Arriola, Dustin Feldt
 * @version Spring 2024
 */
public enum Difficulty {
    /**
     * Field represents easy difficulty.
     */
    EASY("Easy"),
    /**
     * Field represents medium difficulty.
     */
    MEDIUM("Medium"),
    /**
     * Field represents hard difficulty.
     */
    HARD("Hard"),
    /**
     * Field represents extreme difficulty.
     */
    EXTREME("Extreme");
    /**
     * Field represents name of difficulty.
     */
    private final String myName;

    /**
     * assigns name to difficulty.
     * @param theName
     */
    Difficulty(final String theName) {
        myName = theName;
    }

    /**
     *
     * @return name of difficulty.
     */
    public String getName() {
        return myName;
    }

    /**
     *
     * @param theIn
     * @return size of maze corresponding to difficulty.
     */
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