package Model;

import java.io.Serializable;

/**
 * @author Matthew Uzunoe-Chin, Elias Arriola, Dustin Feldt.
 * @version Spring 2024
 * Implementation of Player in TriviaMaze.
 */
public class Player implements Serializable {
    /**
     * Field represents player row location.
     */
    private int myRow;
    /**
     * Field represents player column location.
     */
    private int myColumn;
    /**
     * Field represents name of player.
     */
    private String myName;

    /**
     * Constructor for Player.
     * @param theName name of player as string
     */
    public Player(String theName) {
        myRow=0;
        myColumn=0;
        myName = theName;
    }

    /**
     * Field sets the name of the Player.
     * @param theNewName string for player name
     */
    public void setName(final String theNewName) {
        if(theNewName.isEmpty()) {
            throw new IllegalArgumentException("No empty names!");
        } else {
            myName = theNewName;
        }
    }

    /**
     * Sets the Player row location.
     * @param theRow set player row
     */
    public void setRow(final int theRow) {
        if(theRow < 0) {
            throw new IllegalArgumentException("cannot be negative row: " + theRow);
        }
        myRow = theRow;
    }

    /**
     * Sets the Player column location.
     * @param theColumn set player col
     */
    public void setColumn(final int theColumn) {
        if(theColumn < 0) {
            throw new IllegalArgumentException("cannot be negative column: " + theColumn);
        }
        myColumn = theColumn;
    }

    /**
     *
     * @return Player row location.
     */
    public int getMyRow() {
        return myRow;
    }

    /**
     *
     * @return Player column location.
     */
    public int getMyColumn() {
        return myColumn;
    }

    /**
     *
     * @return Name of Player.
     */
    public String getName() {
        return myName;
    }

    /**
     *
     * @return string representation of Player state.
     */
    @Override
    public String toString() {
        return "Name: " + getName() + " Row: " + getMyRow() + " Column: " + getMyColumn();
    }
}