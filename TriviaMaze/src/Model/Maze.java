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
    private int testCount;

    public Maze(final int theSize, final QuestionFactory theFactory) {
        testCount=0;
        FACTORY = theFactory;
        myPlayer = new Player("");
        mySize = theSize;
        myRooms = new Room[mySize][mySize];
        myRandom = new Random();
        myExitColumn = mySize -1;
        myExitRow = mySize - 1;
        myRowPotion = myRandom.nextInt(mySize - 1);
        myColPotion = myRandom.nextInt(mySize - 1);
        while(myRowPotion == 0 || myRowPotion == myExitRow) {
            myRowPotion = myRandom.nextInt(mySize - 1);
        }
        while(myColPotion == 0 || myColPotion == myExitColumn) {
            myColPotion = myRandom.nextInt(mySize - 1);
        }
        myPlayer.setRow(0);
        myPlayer.setColumn(0);
    }


    public void createMaze() {
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                myRooms[i][j] = new Room(i, j);
                testCount++;
            }
        }
    }
    public int getTotalRooms() {
        return testCount;
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


    public int placePotionRow() {
        return myRowPotion;
    }
    public int placePotionCol() {
        return myColPotion;
    }

    public Room getRoom(final int theX, final int theY) {
        return myRooms[theX][theY];
    }
    public Room[][] getMyRooms() {
        return myRooms;
    }
}
