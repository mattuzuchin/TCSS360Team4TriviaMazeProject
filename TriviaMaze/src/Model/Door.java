package Model;

import java.io.Serializable;

public class Door implements Serializable {
    private boolean myLockedStatus;

    private Question myAssignedQuestion;


    private Direction myDirection;


    public Door(Direction theDirection) {
        myLockedStatus = false; //false means unlocked, true is locked!
        myAssignedQuestion = QuestionFactory.getInstance().getQuestion();
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

    public String toString() {
        return "Current status: " + myLockedStatus + ", " + "Direction: " + myDirection;
    }
}