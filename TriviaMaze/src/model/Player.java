package model;

public class Player {
    private int myX;
    private int myY;
    private String myPlayerName;

    public Player(int theX, int theY,String theName) {
        myX = theX;
        myY = theY;
        myPlayerName = theName;
    }

    public void setX(int theX) {
        myX = theX;
    }

    public void setY(int theY) {
        myY = theY;
    }

    public void setPlayerName(String theName) {
        myPlayerName = theName;
    }

    public int getX() {
        return myX;
    }

    public int getY() {
        return myY;
    }

    public String getName() {
        return myPlayerName;
    }
}
