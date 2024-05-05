package Controller;

import Controller.PropertyChangeEnabledTriviaMazeControls;
import Model.Question;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;


public class TriviaMaze implements PropertyChangeEnabledTriviaMazeControls {
    private int myRow;
    private int myColumns;
    private PropertyChangeSupport myPcs;
    private List<Question> myQuestionsList;


    public TriviaMaze(final List<Question> theQuestionsList) {
        myQuestionsList = new ArrayList<Question>(theQuestionsList);
        myPcs = new PropertyChangeSupport(this);
    }

    public TriviaMaze() {

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

    public void start() {

    }

    public int getHeight() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }


    public void removePropertyChangeListener(final String thePropertyName,
                                      final PropertyChangeListener theListener) {

        myPcs.removePropertyChangeListener(thePropertyName, theListener);
    }

    public void setX(final int theX) {
        myRow = theX;
    }

    public void setY(final int theY) {
        myColumns = theY;
    }

    public String toString() {
        return myRow + " " + myColumns;
    }

    public void reset() {
        //should change, however, as of now this is the state
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

    }
    public void end() {
        //game ends
        if(myColumns == 1) {
            end();
        }
    }

    public int getRow() {
        return myRow;
    }


    public int getCol() {
        return myColumns;
    }
}
