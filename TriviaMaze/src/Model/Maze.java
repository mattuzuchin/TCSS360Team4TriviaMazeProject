package Model;


import javax.swing.plaf.TableHeaderUI;
import java.io.Serializable;
import java.util.Random;

public class Maze implements Serializable {
    private int mySize;
    private Room[][] myRooms;
    private int myExitRow;
    private int myExitColumn;
    private Player myPlayer;
    private Random myRandom;
    private int myRowPotion;
    private int myColPotion;
    public static QuestionFactory FACTORY;

    public Maze(final int theSize, final QuestionFactory theFactory) {
        FACTORY = theFactory;
        myPlayer = new Player("");
        mySize = theSize;
        myRooms = new Room[mySize][mySize];
        myRandom = new Random();
        myRowPotion = myRandom.nextInt(mySize - 1);
        myColPotion = myRandom.nextInt(mySize - 1);
        myPlayer.setRow(0);
        myPlayer.setColumn(0);
        myExitColumn = mySize -1;
        myExitRow = mySize - 1;
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

//    public int placePotionRow() {
//        return myRowPotion;
//    }
//    public int placePotionCol() {
//        return myColPotion;
//    }

    public Room getRoom(final int theX, final int theY) {
        return myRooms[theX][theY];
    }
    public Room[][] getMyRooms() {
        return myRooms;
    }
}
