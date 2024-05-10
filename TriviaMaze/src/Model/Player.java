package Model;

public class Player {
    private int myRow;
    private int myColumn;
    private String myName;

    public Player(String theName) {
        myRow=0;
        myColumn=0;
        myName = theName;
    }
    public void setRow(final int theRow) {
        if(theRow < 0) {
            throw new IllegalArgumentException("cannot be negative row: " + theRow);
        }
        myRow = theRow;
    }
    public void setColumn(final int theColumn) {
        if(theColumn < 0) {
            throw new IllegalArgumentException("cannot be negative column: " + theColumn);
        }
        myColumn = theColumn;
    }
    public void setName(final String theName) {
        if(theName.isEmpty()) {
            throw new IllegalArgumentException("needs a name: " + theName);
        }
        myName = theName;
    }
    public int getRow() {
        return myRow;
    }
    public int getColumn() {
        return myColumn;
    }
    public String getName() {
        return myName;
    }
    public void changeLocation(final Direction theDirection) {
        switch (theDirection) {
            case NORTH:
                setRow(getRow()-1);
            case EAST:
                setColumn(getColumn()+1);
            case SOUTH:
                setRow(getRow()+1);
            case WEST:
                setColumn(getColumn()-1);
        }


    }

    public String getImageFileName() {
        return "elf.png";
    }
    public String toString() {
        return myName;
    }
}
