package View;

import Controller.TriviaMaze;
import Model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;



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


    public TriviaMazePanel(final int theSize, final TriviaMaze theMaze) {
        super();
        myMaze = theMaze;
        myMaze.makeMaze(theSize);
        mySize = theSize;
        setPreferredSize(new Dimension((theSize * ROOM_SIZE) + ((theSize + 1) * DOOR_SIZE),
                (theSize * ROOM_SIZE) + ((theSize + 1) * DOOR_SIZE)));
        setBackground(Color.BLACK);
        setFont(FONT);
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2 = (Graphics2D) theGraphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(STROKE);
        drawRooms(g2);
    }

    private void drawRooms(final Graphics2D theGraphics) {
        Room[][] theR = myMaze.getMaze();
        for (int y = 0; y < mySize; y++) {
            final int topY = y * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;

            for (int x = 0; x < mySize; x++) {
                final int leftX = x * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;
                final Room room = theR[x][y];
                theGraphics.setPaint(Color.DARK_GRAY);
                theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);
                drawDoors(theGraphics, room, leftX, topY);
                drawDebugInfo(theGraphics, leftX, topY);
            }
        }
        final String image = myMaze.getMyPlayer().getImageFileName();
        ImageIcon imgIcon = new ImageIcon(image);
        if(imgIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            imgIcon = new ImageIcon(getClass().getResource(image));
        }

        final Image img = imgIcon.getImage();
        theGraphics.drawImage(img,myMaze.getMyPlayer().getRow(), myMaze.getMyPlayer().getColumn(), this);
    }

    private void drawDoors(final Graphics2D theGraphics, final Room theRoom, final int theX, final int theY) {
        //
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
