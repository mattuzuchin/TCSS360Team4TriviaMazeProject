package Model;

import java.io.Serializable;

public class Door implements Serializable {
    private boolean myLockedStatus;

    private Question myAssignedQuestion;


    private Direction myDirection;

    public Door(Direction theDirection) {
        myLockedStatus = false;
        myAssignedQuestion = Maze.FACTORY.getQuestion();
        myDirection = theDirection;

    }

    public void setLockedStatus(final boolean theLockedStatus) {
        myLockedStatus  = theLockedStatus;
    }
    public boolean isLocked() {
        return myLockedStatus;
    }
    public Question getMyAssignedQuestion() {
        return myAssignedQuestion;
    }
    public void reassignQuestion() {
        myAssignedQuestion = Maze.FACTORY.getQuestion();
    }

    //TESTING ONLY
    public void setQuestion() {
        myAssignedQuestion = new Question("2+2 is?","1","2","3","4","4","1");
    }
    public String toString() {
        return "Current status: " + myLockedStatus + ", " + "Direction: " + myDirection;
    }
}