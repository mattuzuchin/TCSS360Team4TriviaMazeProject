package Controller;

import Model.*;
import View.QuestionPanel;
import View.TriviaMazePanel;

import java.awt.*;
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

    private int myCorrect;
    private int myIncorrect;

    public TriviaMaze() {
        myRow =  0;
        myColumns = 0;
    }


    public int getCorrect() {
        return myCorrect;
    }

    public Maze getMyMaze() {
        return myMaze;
    }
    public int getIncorrect() {
        return myIncorrect;
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

    public void addPropertyChangeListener(final String thePropertyName, final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }


    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }



    public void removePropertyChangeListener(final String thePropertyName,
                                      final PropertyChangeListener theListener) {

        myPcs.removePropertyChangeListener(thePropertyName, theListener);
    }

    public void setMaze(final Maze theMaze) {
        myMaze = theMaze;
    }
    public void setX(final int theX) {
        if(theX < 0) {
            throw new IllegalArgumentException("Values cannot be negative. You put: " + theX);
        }
        myRow = theX;
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
    public void setY(final int theY) {
        if(theY < 0) {
            throw new IllegalArgumentException("Values cannot be negative. You put: " + theY);
        }
        myColumns = theY;
    }

    public String toString() {
        return myRow + " " + myColumns;
    }

    public void reset() {
        myMaze.setPlayerStart(0,0);
        myColumns = 0;
        myRow = 0;
        myTMP.setCheck(false);
        myTMP.drawRooms((Graphics2D) myTMP.getGraphics());
    }
    public void advanceNorth(QuestionPanel thePanel) {
        if(myRow < myMaze.getSize() && myRow != 0) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoor().getMyNorthDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myRow--;
        }
    }
    public void advanceEast(QuestionPanel thePanel) {
        if(myColumns< myMaze.getSize() && myColumns != myMaze.getSize() - 1) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoor().getMyEastDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myColumns++; //go to the right
        }

    }
    public void advanceSouth(QuestionPanel thePanel){
        if(myRow < myMaze.getSize() && myRow != myMaze.getSize() - 1) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoor().getMySouthDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myRow++; // go down
        }
    }
    public void advanceWest(QuestionPanel thePanel) {
        if(myColumns < myMaze.getSize() && myColumns != 0) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoor().getMyWestDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myColumns--; //go left
        }
    }

    public boolean checkLock(Direction theDirection) {
        Room room;
        if(theDirection == Direction.NORTH) {
            room = myMaze.getRoom(myRow, myColumns);
            return room.getDoor().getMyNorthDoor().isLocked();
        } else if (theDirection == Direction.SOUTH) {
            room = myMaze.getRoom(myRow, myColumns);
            return room.getDoor().getMySouthDoor().isLocked();
        } else if (theDirection == Direction.EAST) {
            room = myMaze.getRoom(myRow, myColumns);
            return room.getDoor().getMyEastDoor().isLocked();
        } else {
            room = myMaze.getRoom(myRow, myColumns);
            return room.getDoor().getMyWestDoor().isLocked();
        }
    }
    public void lockDoor(final int theDir) {
        Room room = myMaze.getRoom(myRow, myColumns);
        if(theDir == 0) {
            room.getDoor().getMyNorthDoor().setLockedStatus(true);
            room = myMaze.getRoom(myRow - 1, myColumns);
            room.getDoor().getMySouthDoor().setLockedStatus(true);
        } else if (theDir == 1) {
            room.getDoor().getMyEastDoor().setLockedStatus(true);
            room = myMaze.getRoom(myRow , myColumns + 1);
            room.getDoor().getMyWestDoor().setLockedStatus(true);
        } else if (theDir == 2) {
            room.getDoor().getMySouthDoor().setLockedStatus(true);
            room = myMaze.getRoom(myRow + 1, myColumns);
            room.getDoor().getMyNorthDoor().setLockedStatus(true);
        } else {
            room.getDoor().getMyWestDoor().setLockedStatus(true);
            room = myMaze.getRoom(myRow, myColumns - 1);
            room.getDoor().getMyEastDoor().setLockedStatus(true);
        }
    }
    public Room[][] getMaze() {
        return myMaze.getMyRooms();
    }
    public void makeMaze(final int theSize) {
        myMaze = new Maze(theSize);
        myMaze.createMaze();
    }
    public void setName(String theName) {
        myPlayer = new Player(theName);
    }

    private boolean isValidIndex(final int theX, final int theY) {
        return theY < 0 && theX < 0;
    }
    public int getRow() {
        return myRow;
    }


    public int getCol() {
        return myColumns;
    }

    @Override
    public void start() {

    }

    public void resetPlayer() {
        myPlayer.setColumn(0);
        myPlayer.setRow(0);
    }
    @Override
    public int getHeight() {

        return 0;
    }

    @Override
    public int getWidth() {

        return 0;
    }

    private void fireGridChange() {
        myPcs.firePropertyChange(PROPERTY_GRID, null, myMaze.getMyRooms().clone());
    }

    private void firePlayerChange() {
        myPcs.firePropertyChange(PROPERTY_PLAYER, null, new Player("test"));
    }
}
