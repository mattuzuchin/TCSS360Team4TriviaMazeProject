package Model;


import java.awt.*;

public class Maze {
    private final Room[][] myRooms;

    private final Point myExit;

    private final int mySize;

    public Maze(final int theSize) {
        mySize = theSize;
        myExit = new Point(mySize - 1, mySize - 1);
        myRooms = new Room[theSize][theSize];

        createMaze();
    }

    private void createMaze() {
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                myRooms[i][j] = new Room(new Point(i, j), mySize);
            }
        }
    }

    public Room getRoom(final int theX, final int theY) {
        return myRooms[theX][theY];
    }

    public Room[][] getRooms() {
        return myRooms;
    }

    public Point getExit() {
        return myExit;
    }
}
