package View;

import Controller.TriviaMaze;
import Model.Maze;
import Model.Room;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;


public class TriviaMazePanel extends JPanel implements PropertyChangeListener, ChangeListener {


    private static final Font FONT = new Font("SanSerif", Font.BOLD, 9);

    private static final BasicStroke STROKE = new BasicStroke(3, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER, 2,
            new float[] {2, 2, 2, 2}, 0);

    private static final int ROOM_SIZE = 50;

    private static final int DOOR_SIZE = 5;

    private final int mySize;

    private TriviaMaze myMaze;
    private boolean myCheck = false;
    private boolean myCheat;
    private int myRow;
    private int myCol;


    public TriviaMazePanel(final int theSize, final TriviaMaze theMaze, String theDif) {
        super();
        myCheat = false;
        myMaze = theMaze;
        myMaze.makeMaze(theSize);
        mySize = theSize;
        setPreferredSize(new Dimension(theSize * 56, theSize * 56));
        setBackground(Color.ORANGE);
        setFont(FONT);

    }
    public void setChecked() {
        myCheck = true;
    }
    public void setColor(final int theRow, final int theCol) {
       myCheck = true;
        myRow = theRow;
        myCol = theCol;
        repaint();
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        if(!myCheck) {
            final Graphics2D g2 = (Graphics2D) theGraphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setStroke(STROKE);
            drawRooms(g2);
        } else if (myCheat) {
            final Graphics2D g2 = (Graphics2D) theGraphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(STROKE);
            drawRooms(g2);
        } else {
            final Graphics2D g2 = (Graphics2D) theGraphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(STROKE);
            drawRooms(g2);
        }


    }

    public void useCheat() {
        myCheat = true;
    }

    public void drawRooms(final Graphics2D theGraphics) {
        if(!myCheck) {
            Room[][] theR = myMaze.getMaze();

            for (int y = 0; y < mySize; y++) {
                final int topY = y * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;

                for (int x = 0; x < mySize; x++) {
                    final int leftX = x * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;
                    if (x == 0 && y == 0) {
                        theGraphics.setPaint(Color.BLUE);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);


                    } else if (myMaze.getExitRow() == y && myMaze.getExitCol() == x) {
                        theGraphics.setPaint(Color.RED);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);


                    } else {
                        theGraphics.setPaint(Color.DARK_GRAY);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);

                    }
                }
            }

        } else if (myCheat) {
            repaint();
            for (int y = 0; y < mySize; y++) {
                final int topY = y * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;

                for (int x = 0; x < mySize; x++) {
                    final int leftX = x * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;
                    if (x == myMaze.getCol() && y == myMaze.getRow()) {
                        theGraphics.setPaint(Color.BLUE);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);


                    } else if (myMaze.getExitRow() == y && myMaze.getExitCol() == x) {
                        theGraphics.setPaint(Color.RED);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);


                    } else {
                        theGraphics.setPaint(Color.DARK_GRAY);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);

                    }
                }
            }
            myCheat = false;
        }  else {
            repaint();
            Room[][] checkRoom = myMaze.getMaze();

            for (int y = 0; y < mySize; y++) {
                final int topY = y * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;
                for (int x = 0; x < mySize; x++) {
                    final int leftX = x * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;
                    if (y == myRow && x == myCol) {
                        theGraphics.setPaint(Color.BLUE);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);


                    } else if (myMaze.getExitRow() == y && myMaze.getExitCol() == x) {
                        theGraphics.setPaint(Color.RED);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);


                    } else if(checkRoom[y][x].getDoors().checkNumber() == 0 ){
                        theGraphics.setPaint(Color.DARK_GRAY);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);

                    } else if(checkRoom[y][x].getDoors().checkNumber() == 1 ){
                        theGraphics.setPaint(Color.GREEN);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);

                    } else if(checkRoom[y][x].getDoors().checkNumber() == 2 ){
                        theGraphics.setPaint(Color.PINK);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);

                    } else if(checkRoom[y][x].getDoors().checkNumber() == 3 ){
                        theGraphics.setPaint(Color.WHITE);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);

                    } else if(checkRoom[y][x].getDoors().checkNumber() == 4 ){
                        theGraphics.setPaint(Color.CYAN);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);

                    }

                }
            }

        }

    }


    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {

    }


    @Override
    public void stateChanged(final ChangeEvent theEvent) {
        repaint();
    }
}
