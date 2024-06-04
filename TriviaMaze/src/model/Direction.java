package Model;

/**
 * @author Matthew Uznoe-Chin, Elias Arriola, Dustin Feldt
 * @version Spring 2024
 * Enum of directions.
 */
public enum Direction {

    /**
     * field represents north direction
     */
    NORTH('N'),

    /**
     * field represents west direction.
     */
    WEST('W'),

    /**
     * field represents south direction.
     */
    SOUTH('S'),

    /**
     * field represents east direction.
     */
    EAST('E');

    /**
     * field represents direction assigned single character.
     */
    private final char myLetter;

    /**
     * assigns character to Direction.
     * @param theLetter letter of direction
     */
    Direction(final char theLetter) {
        myLetter = theLetter;
    }

    /**
     *
     * @param theLetter letter for direction
     * @return direction of given character.
     */
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

    /**
     *
     * @return character of Direction.
     */
    public char letter() {
        return myLetter;
    }
}