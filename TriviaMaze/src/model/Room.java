package Model;


import java.io.Serializable;

public class Room implements Serializable {
    private Doors myDoors;
    private int myRow;
    private int myColumn;

    public Room(final int theRow, final int theColumn) {
        myDoors = new Doors();
        myRow = theRow;
        myColumn = theColumn;
    }


    public Doors getDoors() {
        return myDoors;
    }

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
