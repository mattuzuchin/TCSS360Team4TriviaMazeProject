package Model;


import com.sun.source.tree.IfTree;

import java.awt.*;

public class Maze {
    private int mySize;
    private Room[][] myRooms;
    private int myExitRow;
    private int myExitColumn;
    private Player myPlayer;

    public Maze(final int theSize) {
        myPlayer = new Player("test");
        mySize = theSize;
        myRooms = new Room[mySize][mySize];
        myPlayer.setRow(0);
        myPlayer.setColumn(0);
    }


    public void createMaze() {
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                myRooms[i][j] = new Room(i, j);
            }
        }
    }

    public void setPlayerStart(final int theRow, final int theColumn) {
        myPlayer.setRow(theRow);
        myPlayer.setColumn(theColumn);
    }

    public void setExitRow(final int theExitRow) {
        if(theExitRow < 0) {
            throw new IllegalArgumentException("cannot be negative: " + theExitRow);
        }
        myExitRow = theExitRow;
    }

    public void setExitColumn(final int theExitColumn) {
        if(theExitColumn < 0) {
            throw new IllegalArgumentException("cannot be negative: " + theExitColumn);
        }
        myExitColumn = theExitColumn;
    }

    public void setSize(final int theSize) {
        if(theSize < 0) {
            throw new IllegalArgumentException("cannot be negative size: " + theSize);
        }
        mySize = theSize;
    }

    public int getSize() {
        return mySize;
    }

    public int getExitRow() {
        return myExitRow;
    }

    public int getExitColumn() {
        return myExitColumn;
    }

    public int getPlayerRow() {
        return myPlayer.getRow();
    }

    public int getPlayerColumn() {
        return myPlayer.getColumn();
    }

    public Player getPlayer() {
        return myPlayer;
    }

    public Room getRoom(final int theX, final int theY) {
        return myRooms[theX][theY];
    }
    public Room[][] getMyRooms() {
        return myRooms;
    }
}
