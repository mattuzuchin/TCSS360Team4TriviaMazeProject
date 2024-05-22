package Controller;

import Model.*;
import View.QuestionPanel;
import View.TriviaMazePanel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;


public class TriviaMaze implements PropertyChangeEnabledTriviaMazeControls, Serializable {
    private int myRow;
    private int myColumns;
    private PropertyChangeSupport myPcs = new PropertyChangeSupport(this);

    private Player myPlayer;
    private Maze myMaze;
    private TriviaMazePanel myTMP;
    private QuestionFactory myQF;


    public TriviaMaze(QuestionFactory theFactory) {
        myQF = theFactory;
        myRow =  0;
        myColumns = 0;
    }

    public void setMaze() {
        myMaze.setCurrentLocation(myRow, myColumns);
    }


    public Maze getMyMaze() {
        return myMaze;
    }

    public void mySetPanel(final TriviaMazePanel theT) {
        myTMP = theT;
    }
    public Player getMyPlayer() {
        return myPlayer;
    }

    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    public void unlockAll() {
        Room[][] rooms = myMaze.getMyRooms();
       myQF.setInstance();
       myQF = myQF.getInstance();

        for(int row = 0; row < rooms.length;row++) {
            for(int col = 0; col < rooms[row].length; col++) {
                rooms[row][col].getDoors().getMyNorthDoor().setLockedStatus(false);
                rooms[row][col].getDoors().getMySouthDoor().setLockedStatus(false);
                rooms[row][col].getDoors().getMyWestDoor().setLockedStatus(false);
                rooms[row][col].getDoors().getMyEastDoor().setLockedStatus(false);
                rooms[row][col].getDoors().getMyEastDoor().reassignQuestion();
                rooms[row][col].getDoors().getMyNorthDoor().reassignQuestion();
                rooms[row][col].getDoors().getMySouthDoor().reassignQuestion();
                rooms[row][col].getDoors().getMyWestDoor().reassignQuestion();
            }
        }
    }

    public void setMaze(final Maze theMaze) {
        myMaze = theMaze;
    }

    public QuestionFactory getQF() {
        return myQF;
    }

    public boolean checkNorthLocation() {
        boolean check = true;
        if(myRow == 0)  {
            check = false;
        }
        return check;
    }
    public boolean checkSouthLocation() {
        boolean check = true;
        if(myRow == myMaze.getExitRow())  {
            check = false;
        }
        return check;
    }
    public boolean checkWestLocation() {
        boolean check = true;
        if(myColumns == 0)  {
            check = false;
        }
        return check;
    }

    public int getExitCol() {
        return myMaze.getExitColumn();
    }

    public int getExitRow() {
        return myMaze.getExitRow();
    }
    public boolean checkEastLocation() {
        boolean check = true;
        if(myColumns == myMaze.getExitColumn())  {
            check = false;
        }
        return check;
    }

    public String toString() {
        return myRow + " " + myColumns;
    }

    public void advanceNorth(QuestionPanel thePanel) {
        if(myRow < myMaze.getSize() && myRow != 0) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMyNorthDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myRow--;
            setMaze();
        }
    }
    public void advanceEast(QuestionPanel thePanel) {
        if(myColumns< myMaze.getSize() && myColumns != myMaze.getSize() - 1) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMyEastDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myColumns++; //go to the right
            setMaze();
        }

    }
    public void advanceSouth(QuestionPanel thePanel){
        if(myRow < myMaze.getSize() && myRow != myMaze.getSize() - 1) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMySouthDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myRow++; // go down
            setMaze();
        }
    }
    public void advanceWest(QuestionPanel thePanel) {
        if(myColumns < myMaze.getSize() && myColumns != 0) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMyWestDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myColumns--; //go left
            setMaze();
        }
    }

    public boolean checkLock(Direction theDirection) {
        Room room;
        if(theDirection == Direction.NORTH) {
            room = myMaze.getRoom(myRow, myColumns);
            return room.getDoors().getMyNorthDoor().isLocked();
        } else if (theDirection == Direction.SOUTH) {
            room = myMaze.getRoom(myRow, myColumns);
            return room.getDoors().getMySouthDoor().isLocked();
        } else if (theDirection == Direction.EAST) {
            room = myMaze.getRoom(myRow, myColumns);
            return room.getDoors().getMyEastDoor().isLocked();
        } else {
            room = myMaze.getRoom(myRow, myColumns);
            return room.getDoors().getMyWestDoor().isLocked();
        }
    }

    public void lockDoor(final int theDir) {
        Room room = myMaze.getRoom(myRow, myColumns);
        if(theDir == 0) {
            room.getDoors().getMyNorthDoor().setLockedStatus(true);
            room = myMaze.getRoom(myRow - 1, myColumns);
            room.getDoors().getMySouthDoor().setLockedStatus(true);
        } else if (theDir == 1) {
            room.getDoors().getMyEastDoor().setLockedStatus(true);
            room = myMaze.getRoom(myRow , myColumns + 1);
            room.getDoors().getMyWestDoor().setLockedStatus(true);
        } else if (theDir == 2) {
            room.getDoors().getMySouthDoor().setLockedStatus(true);
            room = myMaze.getRoom(myRow + 1, myColumns);
            room.getDoors().getMyNorthDoor().setLockedStatus(true);
        } else {
            room.getDoors().getMyWestDoor().setLockedStatus(true);
            room = myMaze.getRoom(myRow, myColumns - 1);
            room.getDoors().getMyEastDoor().setLockedStatus(true);
        }
    }


    public Room[][] getMaze() {
        return myMaze.getMyRooms();
    }

    public Room getCurrentRoom() {
        return myMaze.getRoom(myRow,myColumns);
    }
    public void makeMaze(final int theSize) {
        myMaze = new Maze(theSize, myQF);
        myMaze.createMaze();
    }
    public void setName(String theName) {
        myPlayer = new Player(theName);
    }

    public int getRow() {
        return myRow;
    }


    public int getCol() {
        return myColumns;
    }



}
