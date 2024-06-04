package Model;


import java.io.Serializable;

/**
 * @author Matthew Uzunoe-Chin, Elias Arriola, Dustin Feldt
 * @version Spring 2024
 * Implementation of a Room.
 */
public class Room implements Serializable {
    /**
     * Field represents the rooms Doors.
     */
    private final Doors myDoors;
    /**
     * Field represents the rooms row location.
     */
    private final int myRow;
    /**
     * Field represents the rooms column location.
     */
    private final int myColumn;

    /**
     * Constructor for the Room.
     * @param theRow set row
     * @param theColumn set col
     */
    public Room(final int theRow, final int theColumn) {
        myDoors = new Doors();
        myRow = theRow;
        myColumn = theColumn;
    }

    /**
     *
     * @return the rooms row.
     */
    public int getRow() {
        return myRow;
    }

    /**
     *
     * @return the room's column.
     */
    public int getColumn() {
        return myColumn;
    }

    /**
     *
     * @return the rooms Doors.
     */
    public Doors getDoors() {
        return myDoors;
    }

    /**
     *
     * @return String representation of the Rooms state.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Row: " + myRow);
        sb.append(" ");
        sb.append("Column: " + myColumn);
        if (myDoors.getMyNorthDoor().isLocked()) {
            sb.append(", ");
            sb.append("North door locked");
        }
        if (myDoors.getMyEastDoor().isLocked()) {
            sb.append(", ");
            sb.append("East door locked");
        }
        if (myDoors.getMySouthDoor().isLocked()) {
            sb.append(", ");
            sb.append("South door locked");
        }
        if (myDoors.getMyWestDoor().isLocked()) {
            sb.append(", ");
            sb.append("West door locked");
        }
        return sb.toString();
    }

}
