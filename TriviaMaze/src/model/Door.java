package model;

public class Door {

    boolean myOpenStatus;
    boolean myLockedStatus;
    Question myQuestion;

    Door() {

    }

    public void setOpenStatus(boolean theOpenStatus) {
        this.myOpenStatus  = theOpenStatus;
    }
    public void setLockedStatus(boolean theLockedStatus) {
        this.myLockedStatus  = theLockedStatus;
    }
    public boolean isLocked() {
        return myLockedStatus;
    }
    public boolean isOpen() {
        return myOpenStatus;
    }
}