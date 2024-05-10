package model;

public enum Direction {

    /**
     * North (which is up on the screen).
     */
    NORTH("Up"),

    /**
     * West (which is left on the screen).
     */
    WEST("Left"),

    /**
     * South (which is down on the screen).
     */
    SOUTH("Down"),

    /**
     * East (which is right on the screen).
     */
    EAST("Right");

    private final String myDirection;

    Direction(final String theDirection) {
        myDirection = theDirection;
    }

    public String getDirection() {
        return myDirection;
    }

    /**
     * Returns the change in x-coordinate by moving one space in this direction
     *
     * @return the change in x-coordinate.
     */
    public int dx() {
        int result = 0;

        switch (this) {
            case WEST:
                result = -1;
                break;

            case EAST:
                result = 1;
                break;

            default:
        }

        return result;
    }

    /**
     * Returns the change in y-coordinate by moving one space in this direction
     *
     * @return the change in y-coordinate.
     */
    public int dy() {
        int result = 0;

        switch (this) {
            case SOUTH:
                result = 1;
                break;

            case NORTH:
                result = -1;
                break;

            default:
        }

        return result;
    }
}
