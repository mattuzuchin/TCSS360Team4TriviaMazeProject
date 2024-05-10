package Model;

import java.io.Serializable;

public class Doors implements Serializable {
    private Door myNorthDoor;
    private Door myEastDoor;
    private Door mySouthDoor;
    private Door myWestDoor;

    public Doors() {
        myNorthDoor = new Door(Direction.NORTH);
        myEastDoor = new Door(Direction.EAST);
        mySouthDoor = new Door(Direction.SOUTH);
        myWestDoor = new Door(Direction.WEST);
    }

    public Door getMyNorthDoor() {
        return myNorthDoor;
    }
    public Door getMySouthDoor() {
        return mySouthDoor;
    }
    public Door getMyEastDoor() {
        return myEastDoor;
    }
    public Door getMyWestDoor() {
        return myWestDoor;
    }
}
