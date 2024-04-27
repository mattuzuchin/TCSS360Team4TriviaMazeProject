package model;

public enum Direction {

    /**
     * North (which is up on the screen).
     */
    NORTH,

    /**
     * West (which is left on the screen).
     */
    WEST,

    /**
     * South (which is down on the screen).
     */
    SOUTH,

    /**
     * East (which is right on the screen).
     */
    EAST;

    /**
     * Returns the change in x-coordinate by moving one space in this direction
     * (for example, WEST would be -1, and NORTH would be 0).
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
     * (for example, WEST would be 0, and NORTH would be -1).
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
