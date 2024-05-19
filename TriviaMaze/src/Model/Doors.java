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
