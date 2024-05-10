package View;


import Controller.PropertyChangeEnabledTriviaMazeControls;
import Controller.TriviaMaze;
import Model.Direction;
import Model.QuestionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public final class TriviaMazeGUI extends JFrame implements ActionListener {
    private static long serialVersionUID = 0;
    /**
     * The main title for the program.
     */
    private static final String TITLE = "Trivia Maze";

    private static final String NORTH_COMMAND = "North";
    private static final String EAST_COMMAND = "East";
    private static final String SOUTH_COMMAND = "South";
    private static final String WEST_COMMAND = "West";

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

    private static final String MOVE_COMMAND = "Move";

    /**
     * The Reset command.
     */
    private static final String RESET_COMMAND = "Reset";
    private JButton myNorth;
    private JButton mySouth;
    private JButton myWest;
    private JButton myEast;

    /**
     * The logic for the simulation.
     */
    private TriviaMaze myTriviaMaze;
    private JPanel mySouthAndQuestionPanel;
    private int mySize;

    private QuestionPanel myPanel;


    public TriviaMazeGUI(TriviaMaze theMaze, int thePanelSize) {
        super(TITLE);
        mySize = thePanelSize;
        myTriviaMaze = theMaze;
        initGUI();
        setVisible(true);
        addListener();

    }

    private void initGUI() {
        final TriviaMazePanel mazePanel = new TriviaMazePanel(mySize, myTriviaMaze);
        myPanel = new QuestionPanel(myTriviaMaze);
        myTriviaMaze.addPropertyChangeListener(mazePanel);
        myTriviaMaze.addPropertyChangeListener(myPanel);
        myPanel.setVisible(true);
        final JPanel southPanel = new JPanel(new FlowLayout());
        mySouthAndQuestionPanel = new JPanel();
        mySouthAndQuestionPanel.setLayout(new BoxLayout(mySouthAndQuestionPanel, BoxLayout.Y_AXIS));

        myNorth = new JButton("North");
        myNorth.setActionCommand(NORTH_COMMAND);
        myNorth.addActionListener(this);
        myEast = new JButton("East");

        myEast.setActionCommand(EAST_COMMAND);
        myEast.addActionListener(this);

        mySouth = new JButton("South");
        mySouth.setActionCommand(SOUTH_COMMAND);
        mySouth.addActionListener(this);

        myWest = new JButton("West");
        myWest.setActionCommand(WEST_COMMAND);
        myWest.addActionListener(this);

        myWest.setFocusable(false);
        myEast.setFocusable(false);
        myNorth.setFocusable(false);
        mySouth.setFocusable(false);

        southPanel.add(myNorth);
        southPanel.add(myEast);
        southPanel.add(mySouth);
        southPanel.add(myWest);
        mySouthAndQuestionPanel.add(southPanel);
        mySouthAndQuestionPanel.add(myPanel);
        final Container masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.X_AXIS));
        masterPanel.add(mazePanel);
        masterPanel.add(mySouthAndQuestionPanel);

        setJMenuBar(createMenuBar());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        add(masterPanel);
        pack();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2, SCREEN_SIZE.height / 2 - getHeight() / 2);
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

        final JMenuItem moveItem = new JMenuItem(MOVE_COMMAND);
        moveItem.addActionListener(this);
        gameMenu.add(moveItem);

        final JMenuItem resetItem = new JMenuItem(RESET_COMMAND);
        resetItem.addActionListener(this);
        gameMenu.add(resetItem);

        menuBar.add(gameMenu);

        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        final String gameRules = "Game Rules as follows: \n" +
                "You will start at a square (a room), and you must go through the maze to reach the exit, which \n" +
                "is listed on the maze itself. Each room has 4 doors, with each being a direction you wish to choose from, " +
                "\n whenever you move to a door, a question will pop up for you to answer. If you get it correct, great! You \n" +
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

        return menuBar;

    }



    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final String command = theEvent.getActionCommand();
        switch (command) {
            case START_COMMAND:
                myTriviaMaze.start();
                break;

            case MOVE_COMMAND:
                //myTriviaMaze.??;
                break;
            case RESET_COMMAND:
                myTriviaMaze.reset();
                break;
            default:
                break;
        }
    }

    public void addListener() {
        myNorth.addActionListener(theEvent -> {
            myPanel.updateQuestion();
            if(!myTriviaMaze.checkLock(Direction.NORTH)) {
                myTriviaMaze.advanceNorth(myPanel);
            }


        });
        mySouth.addActionListener(theEvent -> {
            myPanel.updateQuestion();
            myTriviaMaze.advanceSouth(myPanel);
            if(!myTriviaMaze.checkLock(Direction.SOUTH)) {
                myTriviaMaze.advanceNorth(myPanel);
            }

        });
        myWest.addActionListener(theEvent -> {
            myPanel.updateQuestion();
            myTriviaMaze.advanceWest(myPanel);
            if(!myTriviaMaze.checkLock(Direction.WEST)) {
                myTriviaMaze.advanceNorth(myPanel);
            }

        });
        myEast.addActionListener(theEvent -> {
            myPanel.updateQuestion();
            myTriviaMaze.advanceEast(myPanel);
            if(!myTriviaMaze.checkLock(Direction.EAST)) {
                myTriviaMaze.advanceNorth(myPanel);
            }
        });

    }
}
