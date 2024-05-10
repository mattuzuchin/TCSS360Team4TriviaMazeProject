package Controller;

import Model.*;
import View.QuestionPanel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;


public class TriviaMaze implements PropertyChangeEnabledTriviaMazeControls {
    private int myRow;
    private int myColumns;
    private PropertyChangeSupport myPcs = new PropertyChangeSupport(this);

    private Player myPlayer;
    private Maze myMaze;

    public TriviaMaze() {
        myRow =  0;
        myColumns = 0;
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

    public void setX(final int theX) {
        if(theX < 0) {
            throw new IllegalArgumentException("Values cannot be negative. You put: " + theX);
        }
        myRow = theX;
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

    }
    public void advanceNorth(QuestionPanel thePanel) {
        if(myRow < myMaze.getSize() && myRow != 0) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMyNorthDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myRow--;
        }

    }
    public void advanceEast(QuestionPanel thePanel) {
        if(myColumns< myMaze.getSize() && myColumns != myMaze.getSize() - 1) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMyEastDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myColumns++; //go to the right
        }
    }
    public void advanceSouth(QuestionPanel thePanel){
        if(myRow < myMaze.getSize() && myRow != myMaze.getSize() - 1) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMySouthDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myRow++; // go down
        }
    }
    public void advanceWest(QuestionPanel thePanel) {
        if(myColumns < myMaze.getSize() && myColumns != 0) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMyWestDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myColumns--; //go left
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
        } else if (theDir == 1) {
            room.getDoors().getMyEastDoor().setLockedStatus(true);
        } else if (theDir == 2) {
            room.getDoors().getMySouthDoor().setLockedStatus(true);
        } else {
            room.getDoors().getMySouthDoor().setLockedStatus(true);
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

    private boolean isValidIndex(final int theRow, final int theColumn) {
        return theRow < 0 && theColumn < 0;
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
