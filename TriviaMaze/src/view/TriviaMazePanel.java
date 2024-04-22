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

    }

    private void drawRooms(final Graphics2D theGraphics) {

        for (int y = 0; y < mySize; y++) {
            final int topY = y * (ROOM_SIZE * DOOR_SIZE);

            for (int x = 0; x < mySize; x++) {
                final int leftX = x * (ROOM_SIZE + DOOR_SIZE);
                final Room room = myMaze.getRooms[y][x];
                if (room.getNorth != null) {
                    theGraphics.setPaint(Color.WHITE);
                    theGraphics.fillRect(leftX, topY, ROOM_SIZE, DOOR_SIZE);
                }
                if (room.getEast != null) {
                    theGraphics.setPaint(Color.WHITE);
                    theGraphics.fillRect(leftX + ROOM_SIZE, topY, ROOM_SIZE, DOOR_SIZE);
                }

                theGraphics.setPaint(Color.DARK_GRAY);
                theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);


//                if (y != myHeight - 1) {
//                    //draw South door
//                    theGraphics.setPaint(Color.BLACK);
//                    theGraphics.fillRect(leftX, topY + ROOM_SIZE, ROOM_SIZE, DOOR_WIDTH);
//                }
//                if (x != myWidth - 1) {
//                    //draw East door
//                    theGraphics.setPaint(Color.BLACK);
//                    theGraphics.fillRect(leftX + ROOM_SIZE, topY, DOOR_WIDTH, ROOM_SIZE);
//                }
                drawDebugInfo(theGraphics, x, y);
            }
        }
    }

    private void drawDoors(final Graphics2D theGraphics) {

    }

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
