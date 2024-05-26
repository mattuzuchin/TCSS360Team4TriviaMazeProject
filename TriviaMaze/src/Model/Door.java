package Model;

import java.io.Serializable;

/**
 * @author Matthew Uzunoe-Chin, Elias Arriola, Dustin Feldt
 * @version Spring 2024
 * Implementation of a Door behaviors.
 */
public class Door implements Serializable {
    /**
     * Field represents locked status of a Door.
     */
    private boolean myLockedStatus;
    /**
     * Field represents question assigned to Door.
     */
    private Question myAssignedQuestion;
    /**
     * Field represents direction of the Door.
     */
    private Direction myDirection;

    /**
     * Constructor for Door.
     * @param theDirection
     */
    public Door(Direction theDirection) {
        myLockedStatus = false;
        myAssignedQuestion = Maze.FACTORY.getQuestion();
        myDirection = theDirection;

    }

    /**
     * Sets the locked status of Door.
     * @param theLockedStatus
     */
    public void setLockedStatus(final boolean theLockedStatus) {
        myLockedStatus  = theLockedStatus;
    }

    /**
     *
     * @return locked status of the Door.
     */
    public boolean isLocked() {
        return myLockedStatus;
    }

    /**
     *
     * @return Question assigned to Door.
     */
    public Question getMyAssignedQuestion() {
        return myAssignedQuestion;
    }

    /**
     * Reassigns a question to the door.
     */
    public void reassignQuestion() {
        myAssignedQuestion = Maze.FACTORY.getQuestion();
    }

    /**
     * Sets a question for testing purposes.
     */
    public void setQuestion() {
        myAssignedQuestion = new Question("2+2 is?","1","2","3","4","4","1");
    }

    /**
     * @return String representation of Doors state.
     */
    public String toString() {
        return "Locked status: " + myLockedStatus + ", " + "Direction: " + myDirection;
    }
}