package Controller;

import Model.*;
import View.QuestionPanel;
import View.TriviaMazePanel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Implementation the behaviors for the TriviaMaze
 *
 * @author Matthew Uzunoe-Chin, Dustin Feldt, Elias Arriolas
 * @version Spring 2024
 */
public class TriviaMaze implements PropertyChangeEnabledTriviaMazeControls, Serializable {
    /**
     * The number of rows.
     */
    private int myRow;

    /**
     * The number of columns.
     */
    private int myColumns;

    /**
     * Manager for Property Change Listeners.
     */
    private final PropertyChangeSupport myPcs;

    /**
     * The player object.
     */
    private Player myPlayer;

    /**
     * The  maze object.
     */
    private Maze myMaze;

    /**
     * The TriviaMazeProject
     */
    private TriviaMazePanel myTMP;

    /**
     * The QuestionFactory Object
     */
    private QuestionFactory myQF;


    /**
     * Constuctor for the TriviaMaze
     * @param theFactory single instance of Question Factory.
     */
    public TriviaMaze(QuestionFactory theFactory) {
        myPcs = new PropertyChangeSupport(this);
        myQF = theFactory;
        myRow =  0;
        myColumns = 0;
    }

    /**
     * set's the maze objects row and columns, will keep
     * a consistent updated location - used for serialization.
     */
    public void setMaze() {
        myMaze.setCurrentLocation(myRow, myColumns);
    }

    /**
     * gets the maze object.
     * @return myMaze object.
     */
    public Maze getMyMaze() {
        return myMaze;
    }

    /**
     * sets the trivia maze panel to the given
     * TMP object.
     * @param theT the trivia maze panel.
     * @throws IllegalArgumentException when object is null
     */
    public void mySetPanel(final TriviaMazePanel theT) {
        if(theT == null) {
            throw new IllegalArgumentException("Null object!");
        }
        myTMP = theT;
    }

    /**
     * gets the current player object.
     * @return the player object.
     */
    public Player getMyPlayer() {
        return myPlayer;
    }

    /**
     * Adds the property change.
     * @param theListener The PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    /**
     * Unlocks ALL doors - used when the potion is used.
     */
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

    /**
     * sets the maze to the given maze object.
     * @param theMaze the given maze object
     */
    public void setMaze(final Maze theMaze) {
        if(theMaze == null) {
            throw new IllegalArgumentException("Maze cannot be null!");
        }
        myMaze = theMaze;
    }

    /**
     * Gives the single question factory instance.
     * @return QuestionFactory instance.
     */
    public QuestionFactory getQF() {
        return myQF;
    }

    /**
     * checks the north location, prompting if the player
     * can advance north (up).
     * @return boolean if true or false
     */
    public boolean checkNorthLocation() {
        return myRow != 0;
    }


    /**
     * checks the south location, prompting if the player
     * can advance south (down).
     * @return boolean if true or false
     */
    public boolean checkSouthLocation() {
        return myRow != myMaze.getExitRow();
    }

    /**
     * checks the west location, prompting if the player
     * can advance west (left).
     * @return boolean if true or false
     */
    public boolean checkWestLocation() {
        return myColumns != 0;
    }

    /**
     * checks the east location, prompting if the player
     * can advance east (right).
     * @return boolean if true or false
     */
    public boolean checkEastLocation() {
        return myColumns != myMaze.getExitColumn();
    }

    /**
     * sets the row to the given row
     * @param theRow given int to set
     * @throws IllegalArgumentException when row is invalid.
     */
    public void setRow(final int theRow) {
        if(theRow < 0) {
            throw new IllegalArgumentException("negative row!!");
        } else {
            myRow = theRow;
        }
    }

    /**
     * sets the col to the given col
     * @param theCol given int to set
     * @throws IllegalArgumentException when column is invalid.
     */
    public void setCol(final int theCol) {
        if(theCol < 0) {
            throw new IllegalArgumentException("negative col!!");
        } else {
            myColumns = theCol;
        }
    }

    /**
     * gets the exit col (the max the player can go).
     * @return an int with the exit col.
     */
    public int getExitCol() {
        return myMaze.getExitColumn();
    }

    /**
     * gets the exit row (the max the player can go).
     * @return an int with the exit row.
     */
    public int getExitRow() {
        return myMaze.getExitRow();
    }

    /**
     * Advances the player up.
     * @param thePanel given question panel to update the question upon movement.
     * @throws IllegalArgumentException when panel is null.
     */
    public void advanceNorth(final QuestionPanel thePanel) {
        if(thePanel == null) {
            throw new IllegalArgumentException("Panel is null!");
        }
        if(myRow < myMaze.getSize() && myRow != 0) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMyNorthDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myRow--;
            setMaze();
        }
    }
    /**
     * Advances the player right.
     * @param thePanel given question panel to update the question upon movement.
     * @throws IllegalArgumentException when panel is null.
     */
    public void advanceEast(final QuestionPanel thePanel) {
        if(thePanel == null) {
            throw new IllegalArgumentException("Panel is null!");
        }
        if(myColumns< myMaze.getSize() && myColumns != myMaze.getSize() - 1) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMyEastDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myColumns++; //go to the right
            setMaze();
        }

    }

    /**
     * Advances the player down.
     * @param thePanel given question panel to update the question upon movement.
     * @throws IllegalArgumentException when panel is null.
     */
    public void advanceSouth(final QuestionPanel thePanel){
        if(thePanel == null) {
            throw new IllegalArgumentException("Panel is null!");
        }
        if(myRow < myMaze.getSize() && myRow != myMaze.getSize() - 1) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMySouthDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myRow++; // go down
            setMaze();
        }
    }

    /**
     * Advances the player west.
     * @param thePanel given question panel to update the question upon movement.
     * @throws IllegalArgumentException when panel is null.
     */
    public void advanceWest(final QuestionPanel thePanel) {
        if(thePanel == null) {
            throw new IllegalArgumentException("Panel is null!");
        }
        if(myColumns < myMaze.getSize() && myColumns != 0) {
            Room room = myMaze.getRoom(myRow, myColumns);
            Door door = room.getDoors().getMyWestDoor();
            Question question = door.getMyAssignedQuestion();
            thePanel.setQuestion(question);
            myColumns--; //go left
            setMaze();
        }
    }

    /**
     * checkLock will check if the doors are locked at which direction.
     * @param theDirection the given direction.
     * @return true or false indicating it is locked or not
     * @throws IllegalArgumentException when direction is invalid
     */
    public boolean checkLock(final Direction theDirection) {
        if(theDirection == null) {
            throw new IllegalArgumentException("Direction is null");
        }
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

    /**
     * lockDoor will LOCK the door at the given direction.
     * @param theDir int indicating what direction should be locked.
     * @throws IllegalArgumentException when invalid number
     */
    public void lockDoor(final int theDir) {
        if(theDir < 0) {
            throw new IllegalArgumentException("Invalid Dir");
        }
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

    /**
     * getMaze gets the room array
     * @return a room 2d array for all room objects
     */
    public Room[][] getMaze() {
        return myMaze.getMyRooms();
    }

    /**
     * gets the current room at the current location
     * @return Room object at the current row/col
     */
    public Room getCurrentRoom() {
        return myMaze.getRoom(myRow,myColumns);
    }

    /**
     * mazes a new maze.
     * @param theSize size of maze
     * @throws IllegalArgumentException when size is  < 0
     */
    public void makeMaze(final int theSize) {
        if(theSize < 0) {
            throw new IllegalArgumentException("Value is less than 0");
        }
        myMaze = new Maze(theSize, myQF);
        myMaze.createMaze();
    }

    /**
     * setName sets the name to the player name.
     * @param theName given string name.
     * @throws IllegalArgumentException when empty string.
     */
    public void setName(final String theName) {
        if(theName.isEmpty()) {
            throw new IllegalArgumentException("empty name!");
        }
        myPlayer = new Player(theName);
    }

    /**
     * gets the current row
     * @return the row as an int
     */
    public int getRow() {
        return myRow;
    }

    /**
     * gets the current col
     * @return thw col as an int
     */
    public int getCol() {
        return myColumns;
    }

    /**
     * toString to represent the current state of the object.
     * @return a string of the current status.
     */
    @Override
    public String toString() {
        return myRow + " " + myColumns;
    }


}