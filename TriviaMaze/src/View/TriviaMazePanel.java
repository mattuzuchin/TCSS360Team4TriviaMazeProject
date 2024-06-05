package View;

import Controller.TriviaMaze;
import Model.Room;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The TriviaMazePanel class is responsible for rendering the maze on the screen.
 * It implements PropertyChangeListener and ChangeListener to update the maze view when needed.
 * @author Matthew Uzunoe-Chin, Dustin Feldt, Elias Arriolas
 * @version Spring 2024
 */
public class TriviaMazePanel extends JPanel implements ChangeListener {

    /**
     * The font used for rendering text in the panel.
     */
    private static final Font FONT = new Font("SanSerif", Font.BOLD, 9);

    /**
     * The stroke used for drawing the maze's outlines.
     */
    private static final BasicStroke STROKE = new BasicStroke(3, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER, 2,
            new float[] {2, 2, 2, 2}, 0);

    /**
     * The size of each room in the maze.
     */
    private static final int ROOM_SIZE = 50;

    /**
     * The size of the doors in the maze.
     */
    private static final int DOOR_SIZE = 5;

    /**
     * The size of the maze (number of rooms per side).
     */
    private final int mySize;

    /**
     * The TriviaMaze instance representing the current maze.
     */
    private final TriviaMaze myMaze;

    /**
     * A flag indicating whether a room has been checked.
     */
    private boolean myCheck;

    /**
     * A flag indicating whether cheat mode is enabled.
     */
    private boolean myCheat;

    /**
     * The row of the currently checked room.
     */
    private int myRow;

    /**
     * The column of the currently checked room.
     */
    private int myCol;

    /**
     * Constructs a new TriviaMazePanel with the specified size, maze, and difficulty.
     *
     * @param theSize the size of the maze
     * @param theMaze the TriviaMaze instance representing the maze
     */
    public TriviaMazePanel(final int theSize, final TriviaMaze theMaze) {
        super();
        myCheat = false;
        myMaze = theMaze;
        myMaze.makeMaze(theSize);
        mySize = theSize;
        setPreferredSize(new Dimension(theSize * 56, theSize * 56));
        setBackground(Color.ORANGE);
        setFont(FONT);

    }

    /**
     * Sets the checked flag to true.
     */
    public void setChecked() {
        myCheck = true;
    }

    /**
     * Sets the color of the room at the specified row and column.
     *
     * @param theRow the row of the room to be colored
     * @param theCol the column of the room to be colored
     */
    public void setColor(final int theRow, final int theCol) {
        myCheck = true;
        myRow = theRow;
        myCol = theCol;
        repaint();
    }

    /**
     * Paints the component. This method is called automatically when the component
     * needs to be repainted. It sets up the graphics object and calls drawRooms()
     * to render the maze.
     *
     * @param theGraphics the Graphics object used for painting
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        if (!myCheck) {
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

    /**
     * Sets a marker that the player has used a cheat potion.
     */
    public void useCheat() {
        myCheat = true;
    }

    /**
     * Draws the rooms of the maze.
     *
     * @param theGraphics the Graphics2D object used for drawing
     */
    private void drawRooms(final Graphics2D theGraphics) {
        if (!myCheck) {
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
            final Room[][] checkRoom = myMaze.getMaze();

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


                    } else if (checkRoom[y][x].getDoors().checkNumber() == 0) {
                        theGraphics.setPaint(Color.DARK_GRAY);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);

                    } else if (checkRoom[y][x].getDoors().checkNumber() == 1) {
                        theGraphics.setPaint(Color.GREEN);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);

                    } else if (checkRoom[y][x].getDoors().checkNumber() == 2) {
                        theGraphics.setPaint(Color.PINK);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);

                    } else if (checkRoom[y][x].getDoors().checkNumber() == 3) {
                        theGraphics.setPaint(Color.WHITE);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);

                    } else if (checkRoom[y][x].getDoors().checkNumber() == 4) {
                        theGraphics.setPaint(Color.CYAN);
                        theGraphics.fillRect(leftX, topY, ROOM_SIZE, ROOM_SIZE);
                    }
                }
            }
        }
    }

    /**
     * Called when the state of a model changes. This method is invoked whenever
     * the state of the model changes, allowing the panel to repaint itself.
     *
     * @param theEvent the ChangeEvent object representing the change
     */
    @Override
    public void stateChanged(final ChangeEvent theEvent) {
        repaint();
    }
}
