package Model;

import java.util.*;

public class Door {
    private Door myDoor;
    private String myStatus;

    private Map<String, ArrayList<String>> myChosenQ;
    private boolean myOpenStatus;
    private boolean myLockedStatus;


    public Door() {
        myOpenStatus = false;
        myLockedStatus = false;
        myDoor = this;
    }
    public void setOpenStatus(final boolean theOpenStatus) {
        myOpenStatus  = theOpenStatus;
    }
    public void setLockedStatus(final boolean theLockedStatus) {
        myLockedStatus  = theLockedStatus;
    }
    public boolean isLocked() {
        return myLockedStatus;
    }
    public boolean isOpen() {
        return myOpenStatus;
    }

    public Door getDoorInstance() {
        return myDoor;
    }
    public void assignQuestion() {
    }

    public String setStatus() {
        return myStatus;
    }
}