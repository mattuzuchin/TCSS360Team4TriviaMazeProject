package view;

import model.Maze;
import model.Player;
import model.Room;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TriviaMazePanel extends JPanel implements PropertyChangeListener, ChangeListener {

    private static final Font FONT = new Font("SanSerif", Font.BOLD, 9);

    private static final BasicStroke STROKE = new BasicStroke(3, BasicStroke.CAP_BUTT,
                                                                BasicStroke.JOIN_MITER, 2,
                                                                new float[] {2, 2, 2, 2}, 0);

    private static final int ROOM_SIZE = 50;

    private static final int DOOR_SIZE = 5;

    private static final int DEBUG_OFFSET = 10;

    private final int mySize;

//    private final int myHeight;

    private Maze myMaze;

    private boolean myDebugFlag;

    private Player myPlayer;

    public TriviaMazePanel(final int theSize) {
        super();

        myMaze = new Maze();
        myPlayer = new Player();
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

        for (int y = 0; y < mySize; y++) {
            final int topY = y * (ROOM_SIZE * DOOR_SIZE);

            for (int x = 0; x < mySize; x++) {
                final int leftX = x * (ROOM_SIZE + DOOR_SIZE);
                final Room room = myMaze.getRooms[y][x];

                theGraphics.setPaint(Color.DARK_GRAY);
                theGraphics.fillRect(leftX + DOOR_SIZE, topY + DOOR_SIZE, ROOM_SIZE, ROOM_SIZE);
                drawDoors(theGraphics, room, leftX, topY);

                drawDebugInfo(theGraphics, x, y);
            }
        }
    }

    private void drawDoors(final Graphics2D theGraphics, final Room theRoom, final int theX, final int theY) {
        if (theRoom.getNorth != null) {
            final int doorStatus = theRoom.getNorth.getStatus;
            if (doorStatus = )
            theGraphics.setPaint(Color.WHITE);
            theGraphics.fillRect(theX, theY, ROOM_SIZE, DOOR_SIZE);
        }
        if (theRoom.getEast != null) {
            theGraphics.setPaint(Color.WHITE);
            theGraphics.fillRect(theX + ROOM_SIZE + DOOR_SIZE, theY, DOOR_SIZE, ROOM_SIZE);
        }
        if (theRoom.getSouth != null) {
            theGraphics.setPaint(Color.WHITE);
            theGraphics.fillRect(theX, theY + ROOM_SIZE + DOOR_SIZE, ROOM_SIZE, DOOR_SIZE);
        }
        if (theRoom.getWest != null) {
            theGraphics.setPaint(Color.WHITE);
            theGraphics.fillRect(theX, theY, DOOR_SIZE, ROOM_SIZE);
        }
    }

    /*
    Room class:
    door[4] doors, 0 = N, 1 = E, 2 = S, 3 = W
    point coordinates (correspond to index in Maze)

    Door class:
    int status, 0 = wall, 1 = closed, 2 = open, 3 = locked
    Question question

     */


    private void drawDebugInfo(final Graphics2D theGraphics, final int theX, final int theY) {
        if (myDebugFlag) {
            final Paint oldPaint = theGraphics.getPaint();
            theGraphics.setPaint(Color.BLACK);

            final int leftx = theX * ROOM_SIZE;
            final int topy = theY * ROOM_SIZE;
            theGraphics.drawString("(" + theX + ", " + theY + ")", leftx, topy + DEBUG_OFFSET);
            theGraphics.setPaint(oldPaint);
        }
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        switch (theEvent.getPropertyName()) {
            case PROP_MAZE:
                myMaze = (Maze) theEvent.getNewValue();
                repaint();
                break;
            case PROP_PLAYER:
                myPlayer = (Player) theEvent.getNewValue();
                repaint();
                break;
            default:
                break;
        }
    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param theEvent a ChangeEvent object
     */
    @Override
    public void stateChanged(final ChangeEvent theEvent) {
        myDebugFlag = ((JToggleButton) theEvent.getSource()).isSelected();
        repaint();
    }
}
