package Controller;

import Controller.PropertyChangeEnabledTriviaMazeControls;
import Model.Door;
import Model.Maze;
import Model.Player;
import Model.Question;
import View.TriviaMazeMain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;


public class TriviaMaze implements PropertyChangeEnabledTriviaMazeControls {
    private int myRow;
    private int myColumns;
    private PropertyChangeSupport myPcs = new PropertyChangeSupport(this);
    private List<String> myQuestionsList;
    private Player myPlayer;
    private Door myDoor;
    private Maze myMaze;


    public TriviaMaze(final List<Question> theQuestionsList) {
        myQuestionsList = new ArrayList<String>();
       // myPcs = new PropertyChangeSupport(this);
        myPlayer = new Player();
    }

    public TriviaMaze() {
        myRow =  0;
        myColumns = 0;

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
        myRow = 0;
        myColumns = 0;
    }
    public void advanceNorth() {
        myRow--; // row goes up
    }
    public void advanceEast() {
        myColumns++; //go to the right
    }
    public void advanceSouth(){
        myRow++; // go down
    }
    public void advanceWest() {
        myColumns--; //go left
    }
    public void lockDoor() {
        myDoor.setLockedStatus(true);
    }
    public void end() {
        //game ends
        if(myColumns == 1) {
            end();
        }
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
        reset();
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
        myPcs.firePropertyChange(PROPERTY_PLAYER, null, new Player());
    }
}
