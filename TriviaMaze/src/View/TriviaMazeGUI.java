package View;


import Controller.TriviaMaze;
import Model.Direction;
import Model.Maze;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public final class TriviaMazeGUI extends JFrame implements ActionListener, Serializable {
    private static long serialVersionUID = 0;
    /**
     * The main title for the program.
     */
    private static final String TITLE = "Movie Trivia Maze";

    private int myMoves;


    /**
     * The Toolkit used.
     */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /**
     * The size of the current screen.
     */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /**
     * The Start command.
     */
    private static final String START_COMMAND = "Start";

    /**
     * The Stop command.
     */
    private static final String STOP_COMMAND = "Stop";

    private static final String LOAD_COMMAND = "Load";
    private static final String SAVE_COMMAND = "Save";
    private static final String MOVE_UP = "Up";
    private static final String MOVE_DOWN = "Down";
    private static final String MOVE_RIGHT = "Right";
    private static final String MOVE_LEFT = "Left";
    private boolean myWaitToAdvance = false;

    /**
     * The Reset command.
     */
    private static final String RESET_COMMAND = "Reset";
    private JButton myUp;
    private JButton myDown;
    private JButton myLeft;
    private JButton myRight;

    /**
     * The logic for the simulation.
     */
    private TriviaMaze myTriviaMaze;
    private JPanel mySouthAndQuestionPanel;
    private int mySize;

    private QuestionPanel myPanel;
    private Graphics2D myGraph;
    private TriviaMazePanel myMazePanel;
    private String myDif;
    private boolean myStop;


    public TriviaMazeGUI(TriviaMaze theMaze, int thePanelSize, String theDif) {
        super(TITLE);
        myDif =theDif;
        mySize = thePanelSize;
        myTriviaMaze = theMaze;
        initGUI(theDif);
        setVisible(true);

        addListener();


    }

    private void initGUI(final String theDif) {
         myMazePanel = new TriviaMazePanel(mySize, myTriviaMaze, theDif);
        myPanel = new QuestionPanel(myTriviaMaze, theDif);
        myPanel.setVisible(false);
        myTriviaMaze.mySetPanel(myMazePanel);
        myTriviaMaze.addPropertyChangeListener(myMazePanel);
        myTriviaMaze.addPropertyChangeListener(myPanel);
        myMazePanel.setFocusable(true);
        final JPanel southPanel = new JPanel(new FlowLayout());
        mySouthAndQuestionPanel = new JPanel();
        mySouthAndQuestionPanel.setLayout(new BoxLayout(mySouthAndQuestionPanel, BoxLayout.Y_AXIS));

        myUp = new JButton(MOVE_UP);
        myUp.addActionListener(this);
        myRight = new JButton(MOVE_RIGHT);


        myRight.addActionListener(this);

        myDown = new JButton(MOVE_DOWN);

        myDown.addActionListener(this);

        myLeft = new JButton(MOVE_LEFT);

        myLeft.addActionListener(this);

        myLeft.setFocusable(false);
        myRight.setFocusable(false);
        myUp.setFocusable(false);
        myDown.setFocusable(false);
        final JLabel name = new JLabel(myTriviaMaze.getMyPlayer().getName());
        southPanel.add(myUp);
        southPanel.add(myRight);
        southPanel.add(myDown);
        southPanel.add(myLeft);
        mySouthAndQuestionPanel.add(southPanel);
        mySouthAndQuestionPanel.add(myPanel);
        final Container masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.X_AXIS));
        masterPanel.add(myMazePanel);
        masterPanel.add(mySouthAndQuestionPanel);
        mySouthAndQuestionPanel.setVisible(true);
        setJMenuBar(createMenuBar());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        add(masterPanel);
        pack();

        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2, SCREEN_SIZE.height / 2 - getHeight() / 2);
        myUp.setEnabled(false); //START (0,0)
        myLeft.setEnabled(false);
    }


    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();

        final JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        final JMenuItem startItem = new JMenuItem(START_COMMAND);
        startItem.addActionListener(this);
        gameMenu.add(startItem);

        final JMenuItem stopItem = new JMenuItem(STOP_COMMAND);
        stopItem.addActionListener(this);
        gameMenu.add(stopItem);

        final JMenuItem resetItem = new JMenuItem(RESET_COMMAND);
        resetItem.addActionListener(this);
        gameMenu.add(resetItem);

        menuBar.add(gameMenu);

        final JMenuItem stopGame = new JMenuItem(STOP_COMMAND);
        stopGame.addActionListener(this);
        gameMenu.add(stopGame);

        final JMenuItem loadItem = new JMenuItem(LOAD_COMMAND);
        loadItem.addActionListener(this);
        gameMenu.add(loadItem);

        final JMenuItem saveItem = new JMenuItem(SAVE_COMMAND);
        saveItem.addActionListener(this);
        gameMenu.add(saveItem);
        final JMenu playerMenu = new JMenu("Player: " + myTriviaMaze.getMyPlayer().getName());
        playerMenu.setEnabled(false);


        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        final String gameRules = "Welcome to the Movie Trivia Maze " + myTriviaMaze.getMyPlayer().getName() +  "! \nGame Rules as follows:\n" +
                "You will start at a square (a room), and you must go through the maze to reach the exit, which\n" +
                "is listed on the maze itself. Each room has 4 doors, with each being a direction you wish to choose from, \n" +
                "whenever you move to a door, a question will pop up for you to answer. READ THIS: A room that is green \n" +
                "indicates that ONE DOOR is locked, if a room is pink, it indicates that TWO DOORS are locked, if a room \n" +
                "is white, it indicates that THREE DOORS are locked, if a room is WHITE, then ALL doors are locked. Grey doors \n" +
                "indicate no locked doors! If you get it correct, great! You\n" +
                "can move on, if you get it wrong, that door is locked and you must find another way! Good luck and have fun!";
        final JMenuItem rules = new JMenuItem("Rules");
        rules.setMnemonic(KeyEvent.VK_R);
        rules.addActionListener(theEvent -> JOptionPane.showMessageDialog(null, gameRules));
        helpMenu.add(rules);

        final JMenuItem about = new JMenuItem("About");
        about.setMnemonic(KeyEvent.VK_A);
        about.addActionListener(theEvent -> JOptionPane.showMessageDialog(null, "Trivia Maze V0.01"));
        helpMenu.add(about);

        menuBar.add(helpMenu);
        menuBar.add(playerMenu);
        return menuBar;

    }


    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final String command = theEvent.getActionCommand();
        switch (command) {
            case START_COMMAND:
                myTriviaMaze.start();
                break;
            case RESET_COMMAND:
                myTriviaMaze.reset();
                break;
            case LOAD_COMMAND:
                Maze loaded = loadGame();
                dispose();
                myTriviaMaze.setMaze(loaded);
                new TriviaMazeGUI(myTriviaMaze, loaded.getSize(), myDif);
                break;
            case STOP_COMMAND:
                myStop = true;
                end();
                break;
            case MOVE_UP:

                if(!myTriviaMaze.checkLock(Direction.NORTH)) {
                    myPanel.setDir(0);
                    myTriviaMaze.advanceNorth(myPanel);
                    myMoves++;
                    changePosition();
                    checkEnd();
                } else {
                    JOptionPane.showMessageDialog(this, "Door Locked! Try another way!");
                }
                break;
            case MOVE_DOWN:

                if(!myTriviaMaze.checkLock(Direction.SOUTH)) {
                    myPanel.setDir(2);
                    myTriviaMaze.advanceSouth(myPanel);
                    myMoves++;
                    changePosition();
                    checkEnd();
                } else {
                    JOptionPane.showMessageDialog(this, "Door Locked! Try another way!");
                }
                break;
            case MOVE_LEFT:
                if(!myTriviaMaze.checkLock(Direction.WEST)) {
                    myPanel.setDir(3);
                    myTriviaMaze.advanceWest(myPanel);
                    myMoves++;
                    changePosition();
                    checkEnd();
                } else {
                    JOptionPane.showMessageDialog(this, "Door Locked! Try another way!");
                }
                break;
            case MOVE_RIGHT:
                if(!myTriviaMaze.checkLock(Direction.EAST)) {
                    myPanel.setDir(1);
                    myTriviaMaze.advanceEast(myPanel);
                    myMoves++;
                    changePosition();
                    checkEnd();
                } else {
                    JOptionPane.showMessageDialog(this, "Door Locked! Try another way!");
                }
                break;
            case SAVE_COMMAND:
                saveGame(myTriviaMaze.getMyMaze());
                break;
            default:

                break;
        }
        updateButtonState();
    }

    public void changePosition() {
        myMazePanel.setColor(myTriviaMaze.getRow(), myTriviaMaze.getCol());
    }

    public void checkEnd() {
        if (myTriviaMaze.getRow() == myTriviaMaze.getExitRow()  &&
                myTriviaMaze.getCol() == myTriviaMaze.getExitCol() ) {
            end();
        }
    }
    private void updateButtonState() {
        int row = myTriviaMaze.getRow();
        int col = myTriviaMaze.getCol();
        myUp.setEnabled(row > 0);
        myDown.setEnabled(row < mySize - 1);
        myLeft.setEnabled(col > 0);
        myRight.setEnabled(col < mySize - 1);
    }


    public static void saveGame(final Maze theMaze) {
        try {
            FileOutputStream outputStream = new FileOutputStream("save.ser");
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(theMaze);
            out.close();
            outputStream.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public static Maze loadGame() throws NullPointerException{
        Maze maze;
        File file = new File("save.ser");
        try {
            FileInputStream grabFile = new FileInputStream(file);
            ObjectInputStream mazeIn = new ObjectInputStream(grabFile);
            maze = (Maze) mazeIn.readObject();
            mazeIn.close();
            grabFile.close();

        }catch (IOException | ClassNotFoundException i) {
            return loadGame();
        }
        return maze;
    }
    public void end() {
        if (!myStop) {
            int option = JOptionPane.showConfirmDialog(this, "Congratulations " + myTriviaMaze.getMyPlayer().getName() + "! You won! \n " +
                            "Stats: " + myTriviaMaze.getCorrect() + " correct. \n" +
                            myTriviaMaze.getIncorrect() + " incorrect. \n" +
                            myMoves + " moves taken. \n Do you want to play again?",
                    "Game Over", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                dispose();
                new TitleScreen();
            } else {
                System.exit(0);
            }
        } else {
            int option = JOptionPane.showConfirmDialog(this, myTriviaMaze.getMyPlayer().getName() + " ended the game. \n " +
                            "Stats: " + myTriviaMaze.getCorrect() + " correct. \n" +
                            myTriviaMaze.getIncorrect() + " incorrect. \n" +
                            myMoves + " moves taken. \n Do you want to play again?",
                    "Game Over", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                dispose();
                new TitleScreen();
            } else {
                System.exit(0);
            }
        }

    }
  

    public void addListener() {
//        myUp.addActionListener(theEvent -> {
//
//            if(!myTriviaMaze.checkLock(Direction.NORTH)) {
//                myTriviaMaze.advanceNorth(myPanel);
//                myMoves++;
//                myMazePanel.setColor(myTriviaMaze.getRow(), myTriviaMaze.getCol());
//
//                if (myTriviaMaze.getRow() == myTriviaMaze.getExitRow()  &&
//                        myTriviaMaze.getCol() == myTriviaMaze.getExitCol() ) {
//                    end();
//                }
//            } else {
//
//                JOptionPane.showMessageDialog(this, "Door Locked! Try another way!");
//            }
//
//
//        });
//        myDown.addActionListener(theEvent -> {
//            if(!myTriviaMaze.checkLock(Direction.SOUTH)) {
//
//                myTriviaMaze.advanceSouth(myPanel);
//                myMoves++;
//                myMazePanel.setColor(myTriviaMaze.getRow(), myTriviaMaze.getCol());
//
//                if (myTriviaMaze.getRow() == myTriviaMaze.getExitRow() &&
//                        myTriviaMaze.getCol() == myTriviaMaze.getExitCol()) {
//                    end();
//                }
//            } else {
//
//                JOptionPane.showMessageDialog(this, "Door Locked! Try another way!");
//            }
//
//        });
//        myLeft.addActionListener(theEvent -> {
//
//
//            if(!myTriviaMaze.checkLock(Direction.WEST)) {
//
//                myTriviaMaze.advanceWest(myPanel);
//                myMoves++;
//                myMazePanel.setColor(myTriviaMaze.getRow(), myTriviaMaze.getCol());
//
//                if (myTriviaMaze.getRow() == myTriviaMaze.getExitRow() &&
//                        myTriviaMaze.getCol() == myTriviaMaze.getExitCol()) {
//                    end();
//                }
//            } else {
//
//                JOptionPane.showMessageDialog(this, "Door Locked! Try another way!");
//            }
//
//        });
//        myRight.addActionListener(theEvent -> {
//
//
//            if(!myTriviaMaze.checkLock(Direction.EAST)) {
//
//                myTriviaMaze.advanceEast(myPanel);
//                myMoves++;
//                myMazePanel.setColor(myTriviaMaze.getRow(), myTriviaMaze.getCol());
//
//                if (myTriviaMaze.getRow() == myTriviaMaze.getExitRow()  &&
//                myTriviaMaze.getCol() == myTriviaMaze.getExitCol() ) {
//                    end();
//                }
//            } else {
//
//                JOptionPane.showMessageDialog(this, "Door Locked! Try another way!");
//            }
//        });

    }

}
