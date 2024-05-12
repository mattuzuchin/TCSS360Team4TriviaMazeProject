package Model;


import java.io.Serializable;

public class Maze implements Serializable {
    private int mySize;
    private Room[][] myRooms;
    private int myExitRow;
    private int myExitColumn;
    private Player myPlayer;

    public Maze(final int theSize) {
        if (theSize<0) {
            throw new IllegalArgumentException("Size cannot be negative: " + theSize);
        }
        myPlayer = new Player("test");
        mySize = theSize;
        myRooms = new Room[mySize][mySize];
        myPlayer.setRow(0);
        myPlayer.setColumn(0);
        myExitColumn = mySize -1;
        myExitRow = mySize - 1;
    }


    public void createMaze() {
        for (int row = 0; row < mySize; row++) {
            for (int column = 0; column < mySize; column++) {
                myRooms[row][column] = new Room(row, column);
            }
        }
    }

    public void setPlayerStart(final int theRow, final int theColumn) {
        if (theRow<0) {
            throw new IllegalArgumentException("Row cannot be negative: " + theRow);
        }
        if (theColumn<0) {
            throw new IllegalArgumentException("Column cannot be negative; " + theColumn);
        }
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Maze size: " + getSize());
        sb.append(" ");
//        for (int i=0; i<mySize; i++) {
//            for (int j=0; j<mySize; j++) {
//                sb.append(myRooms[i][j].toString());
//            }
//
//        }
        sb.append("Exit row: " + getExitRow());
        sb.append(" ");
        sb.append("Exit column " + getExitColumn());
        sb.append(" ");
        sb.append(myPlayer.toString());
        return sb.toString();
    }
}
