package Model;


import java.io.Serializable;

public class Room implements Serializable {
    private Doors myDoor;
    private int myRow;
    private int myColumn;

    public Room(final int theRow, final int theColumn) {
        myDoor = new Doors();
        myRow = theRow;
        myColumn = theColumn;
    }


    public Doors getDoors() {
        return myDoor;
    }

}
