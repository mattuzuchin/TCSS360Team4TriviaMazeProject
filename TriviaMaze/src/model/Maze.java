package Model;


import java.io.Serializable;
import java.util.Random;

/**
 * @author Matthew Uzunoe-Chin, Elias Arriola, Dustin Feldt
 * @version Spring 2024
 * Implementation of a Maze.
 */
public class Maze implements Serializable {


    /**
     * Field represents the question Factory.
     */
    protected static QuestionFactory FACTORY;

    /**
     * Field represents the size of the maze.
     */

    private final int mySize;

    /**
     * Field represents a 2D Array of rooms.
     */
    private final Room[][] myRooms;

    /**
     * Field represents the exit row of the maze.
     */
    private final int myExitRow;

    /**
     * Field represents the exit column of the maze.
     */
    private final int myExitColumn;

    /**
     * Field represents the player in the maze.
     */
    private final Player myPlayer;

    /**
     * Field represents the row location of the potion.
     */
    private int myRowPotion;

    /**
     * Field represents the column location of the potion.
     */
    private int myColPotion;

    /**
     * Field represents a count of rooms for testing.
     */
    private int myTestCount;

    /**
     * Field represents row location of player.
     */
    private int myRow;

    /**
     * Field represents column location of player.
     */
    private int myCol;

    /**
     * Constructor for Maze.
     * @param theSize size of maze
     * @param theFactory single QF instance
     */
    public Maze(final int theSize, final QuestionFactory theFactory) {
        final Random random = new Random();
        FACTORY = theFactory;
        myPlayer = new Player("");
        mySize = theSize;
        myRooms = new Room[mySize][mySize];
        myExitColumn = mySize - 1;
        myExitRow = mySize - 1;
        myRowPotion = random.nextInt(mySize - 1);
        myColPotion = random.nextInt(mySize - 1);
        while (myRowPotion == 0 || myRowPotion == myExitRow) {
            myRowPotion = random.nextInt(mySize - 1);
        }
        while (myColPotion == 0 || myColPotion == myExitColumn) {
            myColPotion = random.nextInt(mySize - 1);
        }
        myPlayer.setRow(0);
        myPlayer.setColumn(0);
    }

    /**
     * Creates maze (2D array of rooms).
     */
    public void createMaze() {
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                myRooms[i][j] = new Room(i, j);
                myTestCount++;
            }
        }
    }

    /**
     *
     * @return size of the maze.
     */
    public int getSize() {
        return mySize;
    }

    /**
     *
     * @return exit row of the maze.
     */
    public int getExitRow() {
        return myExitRow;
    }

    /**
     *
     * @return exit column of the maze.
     */
    public int getExitColumn() {
        return myExitColumn;
    }

    /**
     *
     * @return total number of rooms in maze for testing.
     */
    public int getTotalRooms() {
        return myTestCount;
    }

    /**
     *
     * @return gets the row location of potion.
     */
    public int placePotionRow() {
        return myRowPotion;
    }

    /**
     *
     * @return sets the column location of potion.
     */
    public int placePotionCol() {
        return myColPotion;
    }

    /**
     *
     * @param theRow the row
     * @param theColumn the col
     * @return room at specified row and column.
     */
    public Room getRoom(final int theRow, final int theColumn) {
        return myRooms[theRow][theColumn];
    }

    /**
     *
     * @return 2D array of rooms.
     */
    public Room[][] getMyRooms() {
        return myRooms;
    }

    /**
     *
     * @return row location of player.
     */
    public int getMyRow() {
        return myRow;
    }

    /**
     *
     * @return column location of player.
     */
    public int getMyCol() {
        return myCol;
    }

    /**
     * sets the current location of player in maze.
     * @param theRow the current row
     * @param theCol the current col
     */
    public void setCurrentLocation(final int theRow, final int theCol) {

        myRow = theRow;
        myCol = theCol;
    }

    /**
     * Sets the name of the Player.
     * @param theName the name to be set.
     */
    public void setPlayerName(final String theName) {
        myPlayer.setName(theName);
    }

    /**
     * Returns the Player's name.
     * @return the String representation of the Player's name.
     */
    public String getPlayerName() {
        return myPlayer.getName();
    }
}