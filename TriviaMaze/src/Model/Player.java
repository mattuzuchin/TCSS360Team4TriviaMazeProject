package Model;

import java.io.Serializable;

public class Player implements Serializable {
    private int myRow;
    private int myColumn;
    private String myName;

    public Player(String theName) {
        myRow=0;
        myColumn=0;
        myName = theName;
    }
    public void setName(final String theNewName) {
        if(theNewName.isEmpty()) {
            throw new IllegalArgumentException("No empty names!");
        } else {
            myName = theNewName;
        }
    }
    public void setRow(final int theRow) {
        if(theRow < 0) {
            throw new IllegalArgumentException("cannot be negative row: " + theRow);
        }
        myRow = theRow;
    }
    public void setColumn(final int theColumn) {
        if(theColumn < 0) {
            throw new IllegalArgumentException("cannot be negative column: " + theColumn);
        }
        myColumn = theColumn;
    }
    public int getMyRow() {
        return myRow;
    }
    public int getMyColumn() {
        return myColumn;
    }
    public String getName() {
        return myName;
    }


    public String toString() {
        return myName;
    }
}
