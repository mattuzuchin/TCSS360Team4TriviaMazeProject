package Model;

import java.util.Random;

public enum Direction {
    NORTH('N'),


    WEST('W'),


    SOUTH('S'),


    EAST('E');

    private final char myLetter;

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

}
