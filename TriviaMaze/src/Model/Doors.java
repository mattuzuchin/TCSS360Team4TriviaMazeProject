package Model;

import java.io.Serializable;

/**
 * @author Matthew Uzunoe-Chin, Elias Arriola, Dustin Feldt
 * @version Spring 2024
 * Implementation of 4 doors with different directions.
 */
public class Doors implements Serializable {
    /**
     * Field represents north Door.
     */
    private Door myNorthDoor;
    /**
     * Field represents east Door.
     */
    private Door myEastDoor;
    /**
     * Field represents south Door.
     */
    private Door mySouthDoor;
    /**
     * Field represents west Door.
     */
    private Door myWestDoor;

    /**
     * Constructor for Doors.
     */
    public Doors() {
        myNorthDoor = new Door(Direction.NORTH);
        myEastDoor = new Door(Direction.EAST);
        mySouthDoor = new Door(Direction.SOUTH);
        myWestDoor = new Door(Direction.WEST);
    }

    /**
     *
     * @return the north Door.
     */
    public Door getMyNorthDoor() {
        return myNorthDoor;
    }

    /**
     *
     * @return the south Door.
     */
    public Door getMySouthDoor() {
        return mySouthDoor;
    }

    /**
     *
     * @return the east Door.
     */
    public Door getMyEastDoor() {
        return myEastDoor;
    }

    /**
     *
     * @return the west Door.
     */
    public Door getMyWestDoor() {
        return myWestDoor;
    }

    /**
     * @return number of Doors locked.
     */
    public int checkNumber() {
        int check = 0;
        if(myWestDoor.isLocked()) {
            check++;
        }
        if (myNorthDoor.isLocked()) {
            check++;
        }
        if(mySouthDoor.isLocked()) {
            check++;
        }
        if (myEastDoor.isLocked()) {
            check++;
        }
        return check;
    }

    /**
     *
     * @param direction
     * @return the door in the given direction.
     */
    public Door getDoor(Direction direction) {
        switch (direction) {
            case NORTH:
                return myNorthDoor;
            case EAST:
                return myEastDoor;
            case SOUTH:
                return mySouthDoor;
            case WEST:
                return myWestDoor;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    /**
     *
     * @return string representation of Doors state.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("North door locked status: " + myNorthDoor.isLocked());
        sb.append(" ");
        sb.append("East door locked status: " + myEastDoor.isLocked());
        sb.append(" ");
        sb.append("South door locked status: " + mySouthDoor.isLocked());
        sb.append(" ");
        sb.append("West door locked status: " + myWestDoor.isLocked());
        return sb.toString();
    }
}
