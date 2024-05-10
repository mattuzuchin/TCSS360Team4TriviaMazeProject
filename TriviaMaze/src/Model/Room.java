package Model;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private Doors myDoor;
    private int myRow;
    private int myColumn;

    public Room(final int theRow, final int theColumn) {
        myDoor = new Doors();
        myRow = theRow;
        myColumn = theColumn;
    }
    public void setRow(final int theRow) {
        if(theRow < 0) {
            throw new IllegalArgumentException("row cannot be negative: " + theRow);
        }
        myRow = theRow;
    }
    public void setColumn(final int theColumn) {
        if(theColumn < 0) {
            throw new IllegalArgumentException("column cannot be negative: " + theColumn);
        }
        myRow = theColumn;
    }
    public int getRow() {
        return myRow;
    }
    public int getColumn() {
        return myColumn;
    }
    public Doors getDoor() {
        return myDoor;
    }
}
