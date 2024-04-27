package model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Room {

    private final Point myCoordinates;

    private final Map<Direction, Door> myDoors;

    private final int mySize;

    public Room(final Point theCoordinates, final int theSize) {
        myCoordinates = theCoordinates;
        myDoors = new HashMap<>();
        mySize = theSize;
        setDoors();
    }

    private void setDoors() {
        if (myCoordinates.getY() != 0) {
            myDoors.put(Direction.NORTH, new Door());
        }
        if (myCoordinates.getX() != 0) {
            myDoors.put(Direction.WEST, new Door());
        }
        if (myCoordinates.getY() != mySize) {
            myDoors.put(Direction.SOUTH, new Door());
        }
        if (myCoordinates.getX() != mySize) {
            myDoors.put(Direction.EAST, new Door());
        }
    }

    public Map<Direction, Door> getDoors() {
        return myDoors;
    }
}
