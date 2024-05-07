package Model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Door {
    private String myStatus;
    private boolean myOpenStatus;
    private boolean myLockedStatus;
    private Question myQuestion;

    Door() {
        myOpenStatus = false;
        myLockedStatus = false;
        myQuestion = Question.getInstance();

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
    public void assignQuestion() {
        myQuestion.generateQuestion();
    }

    public String setStatus() {
        return myStatus;
    }
}