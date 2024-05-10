package Model;

public class Doors {
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("North Door status: " + myNorthDoor.isLocked());
        sb.append(" ");
        sb.append("South Door status: " + mySouthDoor.isLocked());
        sb.append(" ");
        sb.append("East Door status: " + myEastDoor.isLocked());
        sb.append(" ");
        sb.append("West Door status: " + myWestDoor.isLocked());
        return sb.toString();
    }
}
