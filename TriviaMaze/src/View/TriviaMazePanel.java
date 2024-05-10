package View;

import Controller.TriviaMaze;
import Model.Room;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class TriviaMazePanel extends JPanel implements PropertyChangeListener, ChangeListener {

    private static final long serialVersionUID = 1L;

    private static final Font FONT = new Font("SanSerif", Font.BOLD, 9);

    private static final BasicStroke STROKE = new BasicStroke(3, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER, 2,
            new float[] {2, 2, 2, 2}, 0);

    private static final int ROOM_SIZE = 50;

    private static final int DOOR_SIZE = 5;

    private static final int DEBUG_OFFSET = 10;


    private final int mySize;

    private TriviaMaze myMaze;

    private boolean myDebugFlag;
    private Graphics myGraph;


    public TriviaMazePanel(final int theSize, final TriviaMaze theMaze, String theDif) {
        super();
        myMaze = theMaze;
        myMaze.makeMaze(theSize);
        mySize = theSize;
        if(theDif.equals("Easy")) {
            setPreferredSize(new Dimension(200,200));
        } else if(theDif.equals("Medium")) {
            setPreferredSize(new Dimension(300,300));
        } else if(theDif.equals("Hard")) {
            setPreferredSize(new Dimension(500,500));
        } else {
            setPreferredSize(new Dimension(700,700));
        }

        setBackground(Color.ORANGE);
        setFont(FONT);
    }
    private int myRow;
    private int myCol;
    public void setColor(final int theRow, final int theCol) {
        check = true;
        myRow = theRow;
        myCol = theCol;
        paintComponent(myGraph);

    }
    private boolean check = false;
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        if(!check) {
            final Graphics2D g2 = (Graphics2D) theGraphics;
            myGraph = theGraphics;
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
    public void setCheck(final boolean theB) {
        check = theB;
    }

    public void move() {
        int row = myMaze.getRow() * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;
        int col = myMaze.getCol() * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;
    }

    public void drawRooms(final Graphics2D theGraphics) {

        Room[][] theR = myMaze.getMaze();
        if(!check) {
        for (int y = 0; y < mySize; y++) {
            final int topY = y * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;

            for (int x = 0; x < mySize; x++) {
                final int leftX = x * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;
                if (x == 0 && y == 0) {
                    theGraphics.setPaint(Color.BLUE);
                    theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);
                    drawDebugInfo(theGraphics, leftX, topY);

                } else if (myMaze.getExitRow() == y && myMaze.getExitCol() == x) {
                    theGraphics.setPaint(Color.RED);
                    theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);
                    drawDebugInfo(theGraphics, leftX, topY);

                } else {
                    theGraphics.setPaint(Color.DARK_GRAY);
                    theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);
                    drawDebugInfo(theGraphics, leftX, topY);
                }
            }
        }

        } else {
            repaint();
            for (int y = 0; y < mySize; y++) {
                final int topY = y * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;

                for (int x = 0; x < mySize; x++) {
                    final int leftX = x * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;
                    if (y == myRow && x == myCol) {
                        theGraphics.setPaint(Color.BLUE);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);
                        drawDebugInfo(theGraphics, leftX, topY);

                    } else if (myMaze.getExitRow() == y && myMaze.getExitCol() == x) {
                        theGraphics.setPaint(Color.RED);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);
                        drawDebugInfo(theGraphics, leftX, topY);

                    } else {
                        theGraphics.setPaint(Color.DARK_GRAY);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);
                        drawDebugInfo(theGraphics, leftX, topY);
                    }
                }
        }

        }
//        final String image = myMaze.getMyPlayer().getImageFileName();
//        ImageIcon imgIcon = new ImageIcon(image);
//        if(imgIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
//            imgIcon = new ImageIcon(getClass().getResource(image));
//        }
//        final Image img = imgIcon.getImage();
//        theGraphics.drawImage(img,myMaze.getMyPlayer().getRow(), myMaze.getMyPlayer().getColumn(), this);
    }




    private void drawDebugInfo(final Graphics2D theGraphics, final int theX, final int theY) {
        if (myDebugFlag) {
            final Paint oldPaint = theGraphics.getPaint();
            theGraphics.setPaint(Color.BLACK);

            final int leftX = theX * ROOM_SIZE;
            final int topY = theY * ROOM_SIZE;
            theGraphics.drawString("(" + theX + ", " + theY + ")", leftX, topY+ DEBUG_OFFSET);
            theGraphics.setPaint(oldPaint);
        }
    }


    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {

    }


    @Override
    public void stateChanged(final ChangeEvent theEvent) {
        myDebugFlag = ((JToggleButton) theEvent.getSource()).isSelected();
        repaint();
    }
}
