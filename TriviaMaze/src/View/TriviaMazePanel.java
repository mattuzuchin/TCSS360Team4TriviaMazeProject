package View;

import Model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
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

    private Maze myMaze;

    private boolean myDebugFlag;

    private Player myPlayer;

    public TriviaMazePanel(final int theSize) {
        super();
        myPlayer = new Player();
        myMaze = new Maze(theSize);
        mySize = theSize;

        setPreferredSize(new Dimension((theSize * ROOM_SIZE) + ((theSize + 1) * DOOR_SIZE),
                (theSize * ROOM_SIZE) + ((theSize + 1) * DOOR_SIZE)));
        setBackground(Color.BLACK);
        // draw door corner square
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
            final int topY = y * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;

            for (int x = 0; x < mySize; x++) {
                final int leftX = x * (ROOM_SIZE + DOOR_SIZE) + DOOR_SIZE;
                final Room room = myMaze.getRoom(x, y);

                theGraphics.setPaint(Color.DARK_GRAY);
                theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);
                drawDoors(theGraphics, room, leftX, topY);

                drawDebugInfo(theGraphics, leftX, topY);
            }
        }
    }

    private void drawDoors(final Graphics2D theGraphics, final Room theRoom, final int theX, final int theY) {
        final Map<Direction, Door> doors = new HashMap<>();

        int width = 0;
        int height = 0;

        for (Direction d : doors.keySet()) {
            int newX = theX;
            int newY = theY;

            switch (d) {
                case Direction.NORTH:
                    newY -= DOOR_SIZE;
                    width = ROOM_SIZE;
                    height = DOOR_SIZE;
                    break;
                case Direction.EAST:
                    newX += ROOM_SIZE;
                    width = DOOR_SIZE;
                    height = ROOM_SIZE;
                    break;
                case Direction.SOUTH:
                    newY += ROOM_SIZE;
                    width = ROOM_SIZE;
                    height = DOOR_SIZE;
                    break;
                case Direction.WEST:
                    newX -= DOOR_SIZE;
                    width = DOOR_SIZE;
                    height = ROOM_SIZE;
                    break;
                default:
            }

//            switch (doors.get(d).setStatus()) {
//                case CLOSED:
//                    theGraphics.setPaint(Color.DARK_GRAY);
//                    theGraphics.fillRect(newX, newY, width, height);
//                    break;
//                case OPEN:
//                    theGraphics.setPaint(Color.LIGHT_GRAY);
//                    theGraphics.fillRect(newX, newY, width, height);
//                    break;
//                case LOCKED:
//                    theGraphics.setPaint(Color.BLACK);
//                    theGraphics.fillRect(newX, newY, width, height);
//                    break;
//                default: // Wall, not painted
//            }
        }
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
//        switch (theEvent.getPropertyName()) {
//            case PROP:
//                myMaze = (Maze) theEvent.getNewValue();
//                repaint();
//                break;
//            case PROP_PLAYER:
//                myPlayer = (Player) theEvent.getNewValue();
//                repaint();
//                break;
//            default:
//                break;
//        }
    }


    @Override
    public void stateChanged(final ChangeEvent theEvent) {
        myDebugFlag = ((JToggleButton) theEvent.getSource()).isSelected();
        repaint();
    }
}
