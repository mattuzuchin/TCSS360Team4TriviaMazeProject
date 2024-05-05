package Model;

import java.util.Random;

public enum Direction {
    NORTH('N'),


    WEST('W'),


    SOUTH('S'),


    EAST('E');

    private final char myLetter;
    private static final Random RANDOM = new Random();

    Direction(final char theLetter) {
        myLetter = theLetter;
    }

    public static Direction valueOf(final char theLetter) {
        Direction result = null;

        for (final Direction direction : Direction.values()) {
            if (direction.letter() == theLetter) {
                result = direction;
                break;
            }
        }

        return result;
    }

    public char letter() {
        return myLetter;
    }


    public Direction left() {
        Direction result = null;

        switch (this) {
            case NORTH:
                result = WEST;
                break;

            case WEST:
                result = SOUTH;
                break;

            case SOUTH:
                result = EAST;
                break;

            case EAST:
                result = NORTH;
                break;

            default:
                break;
        }

        return result;
    }


    public static Direction random() {
        return values()[RANDOM.nextInt(values().length)];
    }


    public Direction right() {
        Direction result = null;

        switch (this) {
            case NORTH:
                result = EAST;
                break;

            case WEST:
                result = NORTH;
                break;

            case SOUTH:
                result = WEST;
                break;

            case EAST:
                result = SOUTH;
                break;

            default:
                break;
        }

        return result;
    }

    /**
     * Returns the direction opposite this one.
     *
     * @return the direction opposite this one.
     */
    public Direction reverse() {
        return left().left();
    }

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
