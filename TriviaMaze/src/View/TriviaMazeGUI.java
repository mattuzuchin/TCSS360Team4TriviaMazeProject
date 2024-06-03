package View;


import Controller.TriviaMaze;
import Model.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
/**
 * The main GUI class for the Movie Trivia Maze game. This class sets up the game's main window,
 * initializes all the components, and handles user interactions and events.
 * @author Matthew Uzunoe-Chin, Dustin Feldt, Elias Arriolas
 * @version Spring 2024
 */
public final class TriviaMazeGUI extends JFrame implements ActionListener, Serializable {
    /**
     * The title of the game window.
     */
    private static final String TITLE = "Movie Trivia Maze";

    /**
     * The toolkit used to get the screen size.
     */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /**
     * The size of the screen.
     */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /**
     * The command string for loading a game.
     */
    private static final String LOAD_COMMAND = "Load";

    /**
     * The command string for saving a game.
     */
    private static final String SAVE_COMMAND = "Save";

    /**
     * The command string for moving up.
     */
    private static final String MOVE_UP = "Up";

    /**
     * The command string for moving down.
     */
    private static final String MOVE_DOWN = "Down";

    /**
     * The command string for moving right.
     */
    private static final String MOVE_RIGHT = "Right";

    /**
     * The command string for moving left.
     */
    private static final String MOVE_LEFT = "Left";

    /**
     * The command string for room information.
     */
    private static final String ROOM_INFO = "Room";

    /**
     * The command string for using a potion.
     */
    private static final String POTION = "Use Potion";

    /**
     * The command string for resetting the game.
     */
    private static final String RESET_COMMAND = "Reset";

    /**
     * The command string for ending the game.
     */
    private static final String END_COMMAND = "End";

    /**
     * The button for moving up.
     */
    private JButton myUp;

    /**
     * The button for moving down.
     */
    private JButton myDown;

    /**
     * The button for moving left.
     */
    private JButton myLeft;

    /**
     * The button for moving right.
     */
    private JButton myRight;

    /**
     * The count of moves made by the player.
     */
    private int myMoves;

    /**
     * The TriviaMaze game instance.
     */
    private final TriviaMaze myTriviaMaze;

    /**
     * The panel that holds the south and question panel.
     */
    private JPanel mySouthAndQuestionPanel;

    /**
     * The size of the game panel.
     */
    private final int mySize;

    /**
     * The question panel for displaying questions.
     */
    private QuestionPanel myPanel;

    /**
     * The panel for displaying the maze.
     */
    private TriviaMazePanel myMazePanel;

    /**
     * The difficulty level of the game.
     */
    private final String myDif;

    /**
     * Indicates if the up button was hit.
     */
    private boolean myHitUp;

    /**
     * Indicates if the down button was hit.
     */
    private boolean myHitDown;

    /**
     * Indicates if the left button was hit.
     */
    private boolean myHitLeft;

    /**
     * Indicates if the right button was hit.
     */
    private boolean myHitRight;

    /**
     * The end message for the player.
     */
    private String myPlayerEnd;

    /**
     * The win message for the player.
     */
    private String myPlayerWon;

    /**
     * The lose message for the player.
     */
    private String myPlayerLost;

    /**
     * The audio clip for game won.
     */
    private Clip myGameWon;

    /**
     * The audio clip for game lost.
     */
    private Clip myGameLost;

    /**
     * The count of unlock potions the player has.
     */
    private int myUnlockPotion;

    /**
     * The menu item for using a potion.
     */
    private JMenuItem myPotionItem;

    /**
     * Indicates if the potion is yet found.
     */
    private boolean myYetFound;

    /**
     * The menu item for displaying the player's name.
     */
    private JMenuItem myPlayerName;



    /**
     * Constructs a new TriviaMazeGUI.
     *
     * @param theMaze      The TriviaMaze instance.
     * @param thePanelSize The size of the game panel.
     * @param theDif       The difficulty level of the game.
     */
    public TriviaMazeGUI(TriviaMaze theMaze, int thePanelSize, String theDif) {
        super(TITLE);
        initializeAudio();
        myUnlockPotion = 0;
        myDif =theDif;
        mySize = thePanelSize;
        myTriviaMaze = theMaze;
        initGUI(theDif);
        setVisible(true);
    }


    /**
     * Constructs a new TriviaMazeGUI with a specified potion count.
     *
     * @param theMaze         The TriviaMaze instance.
     * @param thePanelSize    The size of the game panel.
     * @param theDif          The difficulty level of the game.
     * @param theUnlockPotion The count of unlock potions.
     */
    public TriviaMazeGUI(TriviaMaze theMaze, int thePanelSize, String theDif, int theUnlockPotion) {
        super(TITLE);
        initializeAudio();
        myUnlockPotion = theUnlockPotion;
        myDif =theDif;
        mySize = thePanelSize;
        myTriviaMaze = theMaze;
        initGUI(theDif);
        setVisible(true);
    }

    /**
     * Initializes the audio clips for the game.
     */
    private void initializeAudio() {
        try {
            myGameWon = AudioSystem.getClip();
            AudioInputStream correctStream = AudioSystem.getAudioInputStream(new File("gamewon.wav"));
            myGameWon.open(correctStream);

            myGameLost = AudioSystem.getClip();
            AudioInputStream incorrectStream = AudioSystem.getAudioInputStream(new File("gameover.wav"));
            myGameLost.open(incorrectStream);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the question panel.
     *
     * @return The QuestionPanel instance.
     */
    public QuestionPanel getQPanel() {
        return myPanel;
    }


    /**
     * Initializes the GUI components.
     *
     * @param theDif The difficulty level of the game.
     */
    private void initGUI(final String theDif) {
        myYetFound = false;
        myMazePanel = new TriviaMazePanel(mySize, myTriviaMaze, theDif);
        myPanel = new QuestionPanel(myTriviaMaze, theDif, this);
        myPanel.setVisible(true);
        myTriviaMaze.mySetPanel(myMazePanel);
        myTriviaMaze.addPropertyChangeListener(myMazePanel);
        myTriviaMaze.addPropertyChangeListener(myPanel);
        myPlayerEnd = myTriviaMaze.getMyPlayer().getName() + " ended the game" ;
        myPlayerWon = myTriviaMaze.getMyPlayer().getName() + " won the game!";

        myPlayerLost = myTriviaMaze.getMyPlayer().getName() + " LOST! \n ";
        myMazePanel.setFocusable(true);
        final JPanel southPanel = new JPanel(new FlowLayout());
        mySouthAndQuestionPanel = new JPanel();
        mySouthAndQuestionPanel.setLayout(new BoxLayout(mySouthAndQuestionPanel, BoxLayout.Y_AXIS));
        myMazePanel.setBounds(0,600,500,500);
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

    /**
     * Creates the menu bar for the game window.
     *
     * @return The JMenuBar instance.
     */
    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();

        final JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);


        final JMenuItem resetItem = new JMenuItem(RESET_COMMAND);
        resetItem.addActionListener(this);
        gameMenu.add(resetItem);

        final JMenuItem endGame = new JMenuItem(END_COMMAND);
        endGame.addActionListener(this);
        gameMenu.add(endGame);

        myPotionItem = new JMenuItem(POTION);
        myPotionItem.addActionListener(this);
        gameMenu.add(myPotionItem);
        myPotionItem.setEnabled(myUnlockPotion > 0);
        menuBar.add(gameMenu);

        final JMenuItem loadItem = new JMenuItem(LOAD_COMMAND);
        loadItem.addActionListener(this);
        gameMenu.add(loadItem);

        final JMenuItem saveItem = new JMenuItem(SAVE_COMMAND);
        saveItem.addActionListener(this);
        gameMenu.add(saveItem);
        myPlayerName = new JMenu("Player: " + myTriviaMaze.getMyPlayer().getName());

        myPlayerName.setEnabled(false);


        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        final String gameRules = "Welcome to the Movie Trivia Maze " + myTriviaMaze.getMyPlayer().getName() +  "! \nGame Rules as follows:\n" +
                "You will start at a square (a room), and you must go through the maze to reach the exit, which\n" +
                "is listed on the maze itself. Each room has 4 doors, with each being a direction you wish to choose from, \n" +
                "whenever you move to a door, a question will pop up for you to answer. READ THIS: A room that is green \n" +
                "indicates that ONE DOOR is locked, if a room is pink, it indicates that TWO DOORS are locked, if a room \n" +
                "is white, it indicates that THREE DOORS are locked, if a room is WHITE, then ALL doors are locked. Grey doors \n" +
                "indicate no locked doors! If you get it correct, great! There is also a potion you can find randomly placed in \n" +
                "the maze, this will UNLOCK ALL LOCKED DOORS! Be wise when you wish to use it! You " +
                "can move on, if you get it wrong, \nthat door is locked and you must find another way! Good luck and have fun!";
        final JMenuItem rules = new JMenuItem("Rules");
        rules.setMnemonic(KeyEvent.VK_R);
        rules.addActionListener(theEvent -> JOptionPane.showMessageDialog(null, gameRules));
        helpMenu.add(rules);

        final JMenuItem aboutRoom = new JMenuItem(ROOM_INFO);
        aboutRoom.setMnemonic(KeyEvent.VK_L);
        aboutRoom.addActionListener(this);

        final JMenuItem about = new JMenuItem("About");
        about.setMnemonic(KeyEvent.VK_A);
        about.addActionListener(theEvent -> JOptionPane.showMessageDialog(null, "Trivia Maze V0.01"));
        helpMenu.add(about);
        helpMenu.add(aboutRoom);

        menuBar.add(helpMenu);
        menuBar.add(myPlayerName);
        return menuBar;

    }

    /**
     * This method is called when an action is performed by the user interface components.
     * It handles various actions such as moving the player, using potions, saving the game, etc.
     *
     * @param theEvent The ActionEvent generated by the user interface component.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final String command = theEvent.getActionCommand();
        switch (command) {
            case END_COMMAND:
                end("Are you sure you want to end the game?");
                break;
            case POTION:
                int choice = JOptionPane.showConfirmDialog(this,"Are you sure you want to use a potion? You have " + myUnlockPotion,
                        "Confirm", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    usePotion();
                    updateButtonState();
                    myPotionItem.setEnabled(myUnlockPotion > 0);
                }
                break;
            case RESET_COMMAND:
                int option = JOptionPane.showConfirmDialog(this,"Are you sure you want to restart the game? It will take you to the title page.",
                        "Confirm", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                    myTriviaMaze.getQF().setInstance();
                    new TitleScreen();
                }
                break;
            case ROOM_INFO:
                JOptionPane.showMessageDialog(this, "Current Room Info:\n" + "Row: " + myTriviaMaze.getRow() + "\nColumn: " + myTriviaMaze.getCol() +
                        "\nLocked Doors: " + myTriviaMaze.getCurrentRoom().getDoors().checkNumber());
                break;
            case LOAD_COMMAND:
                Maze loaded = loadGame();
                dispose();
                TriviaMazeGUI newGUI = new TriviaMazeGUI(myTriviaMaze, loaded.getSize(), myDif);
                updatePlayer();
                myTriviaMaze.setMaze(loaded);
                newGUI.setPlayerLocation(myTriviaMaze.getMyMaze().getMyRow(), myTriviaMaze.getMyMaze().getMyCol());
                myMazePanel.setChecked();
                myMazePanel.repaint();

                break;
            case MOVE_UP:
                myHitUp = true;
                myMoves++;
                if(!myTriviaMaze.checkLock(Direction.NORTH) && myTriviaMaze.checkNorthLocation()) {
                    disableButtons();
                    myPanel.updateQuestion(myTriviaMaze.getCurrentRoom().getDoors().getMyNorthDoor().getMyAssignedQuestion());
                    myPanel.setDir(0);
                    if(checkPotion()) {
                        messagePotion(1);
                    }
                    checkEnd();

                } else {

                    JOptionPane.showMessageDialog(this, "Door Locked! Try another way!");
                }
                break;
            case MOVE_DOWN:
                myHitDown = true;
                myMoves++;
                if(!myTriviaMaze.checkLock(Direction.SOUTH) && myTriviaMaze.checkSouthLocation()) {
                    disableButtons();
                    myPanel.updateQuestion(myTriviaMaze.getCurrentRoom().getDoors().getMySouthDoor().getMyAssignedQuestion());
                    myPanel.setDir(2);
                    if(checkPotion()) {
                        messagePotion(1);
                    }
                    checkEnd();
                } else {

                    JOptionPane.showMessageDialog(this, "Door Locked! Try another way!");
                }
                break;
            case MOVE_LEFT:
                myHitLeft = true;
                myMoves++;
                if(!myTriviaMaze.checkLock(Direction.WEST) && myTriviaMaze.checkWestLocation()) {
                    disableButtons();
                    myPanel.updateQuestion(myTriviaMaze.getCurrentRoom().getDoors().getMyWestDoor().getMyAssignedQuestion());
                    myPanel.setDir(3);
                    if(checkPotion()) {
                        messagePotion(1);
                    }
                    checkEnd();
                } else {

                    JOptionPane.showMessageDialog(this, "Door Locked! Try another way!");
                }
                break;
            case MOVE_RIGHT:
                myHitRight = true;
                myMoves++;
                if(!myTriviaMaze.checkLock(Direction.EAST) && myTriviaMaze.checkEastLocation()) {
                    disableButtons();
                    myPanel.updateQuestion(myTriviaMaze.getCurrentRoom().getDoors().getMyEastDoor().getMyAssignedQuestion());
                    myPanel.setDir(1);
                    if(checkPotion()) {
                        messagePotion(1);
                    }
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

    }
    /**
     * Sets the player's location in the maze and updates GUI elements accordingly.
     *
     * @param theRow The row of the new player location.
     * @param theCol The column of the new player location.
     */
    private void setPlayerLocation(final int theRow, final int theCol) {
        myTriviaMaze.setRow(theRow);
        myTriviaMaze.setCol(theCol);
        myTriviaMaze.getMyMaze().setCurrentLocation(theRow, theCol);
        myMazePanel.setColor(theRow, theCol);
        updateButtonState();
    }
    /**
     * Checks if the player is on a potion tile and updates potion count if found.
     *
     * @return True if the player is on a potion tile and potion count is updated, otherwise false.
     */
    private boolean checkPotion() {
        if(myTriviaMaze.getRow() == myTriviaMaze.getMyMaze().placePotionRow() && myTriviaMaze.getCol() ==
                myTriviaMaze.getMyMaze().placePotionCol() && !myYetFound) {
            myUnlockPotion++;
            myYetFound = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Displays a message related to potion usage based on the given condition.
     *
     * @param theM The condition for displaying the message.
     */
    private void messagePotion(final int theM) {
        if(theM == 1) {
            int option = JOptionPane.showConfirmDialog(this, "You found a potion! This unlocks all" +
                            " locked doors...would you like to use it?",
                    "Game Over", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                usePotion();
            } else {
                myPotionItem.setEnabled(true);
                updateCount();
            }
        } else {
            int option = JOptionPane.showConfirmDialog(this, "You HAVE a potion to use! If you don't use, the game ends.",
                    "Game Over", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                usePotion();
            } else {
                myUnlockPotion = 0;
                end(myPlayerLost + "Stats: \n" + myPanel.getCorrect() + " correct.\n" +
                        myPanel.getIncorrect() +  " incorrect.\n" +
                        myMoves +  " moves taken.\n Do you want to play again?");
            }
        }

    }
    /**
     * Updates player-related GUI elements.
     */
    private void updatePlayer() {
        myPlayerName.setText(myTriviaMaze.getMyPlayer().getName());
    }

    /**
     * Uses a potion, unlocks all doors, updates GUI elements, and decreases potion count.
     */
    private void usePotion() {
        myUnlockPotion--;
        myTriviaMaze.unlockAll();
        myMazePanel.useCheat();
        updateButtonState();
        myMazePanel.paintComponent(myMazePanel.getGraphics());
        updateCount();

    }

    /**
     * Updates the text of the potion item.
     */
    private void updateCount() {
        myPotionItem.setText("Potion");
    }

    /**
     * Enables all movement buttons.
     */
    public void setUpBut() {
        myUp.setEnabled(true);
        myDown.setEnabled(true);
        myRight.setEnabled(true);
        myLeft.setEnabled(true);
    }

    /**
     * Disables the up movement button.
     */
    public void setDisableUp() {
        myUp.setEnabled(false);
    }

    /**
     * Disables the down movement button.
     */
    public void setDisableDown() {
        myDown.setEnabled(false);
    }

    /**
     * Disables the left movement button.
     */
    public void setDisableLeft() {
        myLeft.setEnabled(false);
    }

    /**
     * Disables the right movement button.
     */
    public void setDisableRight() {
        myRight.setEnabled(false);
    }


    /**
     * Sets the state of the up movement button.
     *
     * @param theB The state of the up movement button.
     */
    public void setUp(final boolean theB) {
        myHitUp = theB;
    }

    /**
     * Sets the state of the down movement button.
     *
     * @param theB The state of the down movement button.
     */
    public void setDown(final boolean theB) {
        myHitDown = theB;
    }

    /**
     * Sets the state of the left movement button.
     *
     * @param theB The state of the left movement button.
     */
    public void setLeft(final boolean theB) {
        myHitLeft = theB;
    }

    /**
     * Sets the state of the right movement button.
     *
     * @param theB The state of the right movement button.
     */
    public void setRight(final boolean theB) {
        myHitRight = theB;
    }

    /**
     * Retrieves the state of the up movement button.
     *
     * @return The state of the up movement button.
     */
    public boolean getMyUp() {
        return myHitUp;
    }

    /**
     * Retrieves the state of the down movement button.
     *
     * @return The state of the down movement button.
     */
    public boolean getMyDown() {
        return myHitDown;
    }

    /**
     * Retrieves the state of the left movement button.
     *
     * @return The state of the left movement button.
     */
    public boolean getMyLeft() {
        return myHitLeft;
    }

    /**
     * Retrieves the state of the right movement button.
     *
     * @return The state of the right movement button.
     */
    public boolean getMyRight() {
        return myHitRight;
    }

    /**
     * Changes the position of the player on the maze panel.
     */
    public void changePosition() {
        myMazePanel.setColor(myTriviaMaze.getRow() , myTriviaMaze.getCol() );
    }

    /**
     * Checks if the game has ended (player reached the exit) and displays appropriate message.
     */
    public void checkEnd() {
        if (myTriviaMaze.getRow() == myTriviaMaze.getExitRow()  &&
                myTriviaMaze.getCol() == myTriviaMaze.getExitCol() ) {
            playWonSound();
            myUnlockPotion = 0;
            end(myPlayerWon + "Stats: \n" + myPanel.getCorrect() + " correct.\n" +
                    myPanel.getIncorrect() +  " incorrect.\n" +
                    myMoves +  " moves taken.\n Do you want to play again?");
        }
    }

    /**
     * Checks if the player has lost the game under various conditions and ends the game accordingly.
     */
    public void playerLost() {
        String message = myPlayerLost + "Stats: \n" + myPanel.getCorrect() + " correct.\n" +
                myPanel.getIncorrect() +  " incorrect.\n" +
                myMoves +  " moves taken.\n Do you want to play again?";
        playLostSound();
        if(myTriviaMaze.getCurrentRoom().getDoors().getMyNorthDoor().isLocked() && myTriviaMaze.getCurrentRoom().getDoors().getMySouthDoor().isLocked() &&
                myTriviaMaze.getCurrentRoom().getDoors().getMyEastDoor().isLocked() && myTriviaMaze.getCurrentRoom().getDoors().getMyWestDoor().isLocked()) {
            end(message);
        } else if ((myTriviaMaze.getRow() == 0 && myTriviaMaze.getCol() == 0) && (myTriviaMaze.getCurrentRoom().getDoors().getMySouthDoor().isLocked() &&
                myTriviaMaze.getCurrentRoom().getDoors().getMyEastDoor().isLocked())) {
            end(message);
        } else if ((myTriviaMaze.getRow() == 0 && myTriviaMaze.getCol() == myTriviaMaze.getExitCol() - 1) && (myTriviaMaze.getCurrentRoom().getDoors().getMySouthDoor().isLocked() &&
                myTriviaMaze.getCurrentRoom().getDoors().getMyWestDoor().isLocked())) {
            end(message);
        } else if ((myTriviaMaze.getRow() == 0) && (myTriviaMaze.getCurrentRoom().getDoors().getMySouthDoor().isLocked() &&
                myTriviaMaze.getCurrentRoom().getDoors().getMyEastDoor().isLocked() && myTriviaMaze.getCurrentRoom().getDoors().getMyWestDoor().isLocked())) {
            end(message);
        } else if ((myTriviaMaze.getCol() == 0) && (myTriviaMaze.getCurrentRoom().getDoors().getMySouthDoor().isLocked() &&
                myTriviaMaze.getCurrentRoom().getDoors().getMyEastDoor().isLocked() && myTriviaMaze.getCurrentRoom().getDoors().getMyNorthDoor().isLocked())) {
            end(message);
        } else if ((myTriviaMaze.getCol() == 0 && myTriviaMaze.getRow() == myTriviaMaze.getExitRow() - 1) && myTriviaMaze.getCurrentRoom().getDoors().getMyNorthDoor().isLocked() &&
                myTriviaMaze.getCurrentRoom().getDoors().getMyEastDoor().isLocked()) {
            end(message);
        } else if ((myTriviaMaze.getCol() == myTriviaMaze.getExitCol() - 1) && (myTriviaMaze.getCurrentRoom().getDoors().getMySouthDoor().isLocked() &&
                myTriviaMaze.getCurrentRoom().getDoors().getMyWestDoor().isLocked() && myTriviaMaze.getCurrentRoom().getDoors().getMyNorthDoor().isLocked())) {
            end(message);
        } else if ((myTriviaMaze.getRow() == myTriviaMaze.getExitRow() - 1 ) && (myTriviaMaze.getCurrentRoom().getDoors().getMyNorthDoor().isLocked() &&
                myTriviaMaze.getCurrentRoom().getDoors().getMyEastDoor().isLocked() && myTriviaMaze.getCurrentRoom().getDoors().getMyWestDoor().isLocked())) {
            end(message);
        }
    }

    /**
     * Checks if the player has lost the game when reaching the exit and ends the game accordingly.
     */
    public void checkExitEnd() {
        int exitRow = myTriviaMaze.getExitRow() - 1;
        int exitCol = myTriviaMaze.getExitCol() - 1;
        Room[][] room = myTriviaMaze.getMaze();

        String message = myPlayerLost + "Stats: \n" + myPanel.getCorrect() + " correct.\n" +
                myPanel.getIncorrect() +  " incorrect.\n" +
                myMoves +  " moves taken.\n Do you want to play again?";
        if(room[exitRow][exitCol].getDoors().getMyNorthDoor().isLocked() && room[exitRow][exitCol].getDoors().getMyWestDoor().isLocked()) {
            playLostSound();
            end(message);
        }
    }

    /**
     * Disables all movement buttons.
     */
    private void disableButtons() {
        myUp.setEnabled(false);
        myDown.setEnabled(false);
        myLeft.setEnabled(false);
        myRight.setEnabled(false);
    }

    /**
     * Updates the state of movement buttons based on the current player position and locked doors.
     */
    public void updateButtonState() {
        int row = myTriviaMaze.getRow();
        int col = myTriviaMaze.getCol();

        myUp.setEnabled(!myTriviaMaze.getCurrentRoom().getDoors().getMyNorthDoor().isLocked() && row > 0);
        myDown.setEnabled(!myTriviaMaze.getCurrentRoom().getDoors().getMySouthDoor().isLocked() && row < mySize - 1);
        myLeft.setEnabled(!myTriviaMaze.getCurrentRoom().getDoors().getMyWestDoor().isLocked() && col > 0);
        myRight.setEnabled(!myTriviaMaze.getCurrentRoom().getDoors().getMyEastDoor().isLocked() && col < mySize - 1);
    }

    /**
     * Saves the current game state to a file.
     *
     * @param theMaze The maze object representing the current game state.
     */
    private static void saveGame(final Maze theMaze) {
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

    /**
     * Loads a saved game from a file.
     *
     * @return The maze object representing the loaded game state.
     * @throws NullPointerException If there is an issue loading the game.
     */
    private static Maze loadGame() throws NullPointerException{


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

    /**
     * Ends the game with the provided message and handles potion usage if available.
     *
     * @param theMessage The message to display when ending the game.
     */
    private void end(final String theMessage) {
        if(myUnlockPotion > 0) {
            messagePotion(2);
        } else {
            int option = JOptionPane.showConfirmDialog(this, theMessage,
                    "Game Over", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                dispose();
                new TitleScreen();
            } else {
                System.exit(0);
            }
        }

    }

    /**
     * plays the win sound.
     */
    private void playWonSound() {
        if (myGameWon.isRunning())
            myGameWon.stop();
        myGameWon.setFramePosition(0);
        myGameWon.start();
    }

    /**
     * plays the lost sound.
     */
    private void playLostSound() {
        if (myGameLost.isRunning())
            myGameLost.stop();
        myGameLost.setFramePosition(0);
        myGameLost.start();
    }
}