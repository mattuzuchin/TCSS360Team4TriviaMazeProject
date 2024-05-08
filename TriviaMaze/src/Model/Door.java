package Model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Door {
    private Door myDoor;
    private String myStatus;

    private Map<String, ArrayList<String>> myChosenQ;
    private boolean myOpenStatus;
    private boolean myLockedStatus;
    private Question myQuestion;

    Door() {
        myOpenStatus = false;
        myLockedStatus = false;
        myQuestion = Question.getInstance();
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
    public Question getQuestion() {
        return myQuestion;
    }
    public Door getDoorInstance() {
        return myDoor;
    }
    public void assignQuestion() {
        myChosenQ = myQuestion.generateQuestion();
    }

    public String setStatus() {
        return myStatus;
    }
}