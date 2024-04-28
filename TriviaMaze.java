package Controller;

import java.util.List;

public class TriviaMaze {
    private int myX;
    private int myY;

    private List<Question> myQuestionsList;



    public int getX() {
        return myX;
    }

    public int getY() {
        return myY;
    }

    public void setX(final int theX) {
        myX = theX;
    }

    public void setY(final int theY) {
        myY = theY;
    }

    public String toString() {
        return myX + " " +  myY;
    }

    public void reset() {
        
    }
}
