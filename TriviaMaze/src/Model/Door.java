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

    public boolean getStatus() {
        return myOpenStatus;
    }
}