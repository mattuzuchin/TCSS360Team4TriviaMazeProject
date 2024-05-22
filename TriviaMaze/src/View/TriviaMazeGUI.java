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

public final class TriviaMazeGUI extends JFrame implements ActionListener, Serializable {
    private static long serialVersionUID = 0;

    private static final String TITLE = "Movie Trivia Maze";

    private int myMoves;


    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    private static final String LOAD_COMMAND = "Load";
    private static final String SAVE_COMMAND = "Save";
    private static final String MOVE_UP = "Up";
    private static final String MOVE_DOWN = "Down";
    private static final String MOVE_RIGHT = "Right";
    private static final String MOVE_LEFT = "Left";
    private static final String ROOM_INFO = "Room";
    private static final String POTION = "Use Potion";

    private static final String RESET_COMMAND = "Reset";
    private static final String END_COMMAND = "End";
    private JButton myUp;
    private JButton myDown;
    private JButton myLeft;
    private JButton myRight;

    private TriviaMaze myTriviaMaze;
    private JPanel mySouthAndQuestionPanel;
    private int mySize;

    private QuestionPanel myPanel;
    private TriviaMazePanel myMazePanel;
    private String myDif;

    private boolean myHitUp;
    private boolean myHitDown;
    private boolean myHitLeft;
    private boolean myHitRight;

    private String myPlayerEnd;
    private String myPlayerWon;
    private String myPlayerLost;
    private Clip myGameWon;
    private Clip myGameLost;
    private int myUnlockPotion;
    private JMenuItem myPotionItem;
    private boolean myYetFound;



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
    public TriviaMazeGUI(TriviaMaze theMaze, int thePanelSize, String theDif, int theUnlockPotion) {
        super(TITLE);
        initializeAudio();
        myUnlockPotion = 1;
        myDif =theDif;
        mySize = thePanelSize;
        myTriviaMaze = theMaze;
        initGUI(theDif);
        setVisible(true);
    }


    public void initializeAudio() {
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
    public QuestionPanel getQPanel() {
        return myPanel;
    }
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


        final JMenuItem resetItem = new JMenuItem(RESET_COMMAND);
        resetItem.addActionListener(this);
        gameMenu.add(resetItem);

        final JMenuItem endGame = new JMenuItem(END_COMMAND);
        endGame.addActionListener(this);
        gameMenu.add(endGame);

        myPotionItem = new JMenuItem(POTION);
        myPotionItem.addActionListener(this);
        gameMenu.add(myPotionItem);
        if(myUnlockPotion > 0) {
            myPotionItem.setEnabled(true);
        } else {
            myPotionItem.setEnabled(false);
        }
        menuBar.add(gameMenu);

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

        final JMenuItem aboutRoom = new JMenuItem(ROOM_INFO);
        aboutRoom.setMnemonic(KeyEvent.VK_L);
        aboutRoom.addActionListener(this);

        final JMenuItem about = new JMenuItem("About");
        about.setMnemonic(KeyEvent.VK_A);
        about.addActionListener(theEvent -> JOptionPane.showMessageDialog(null, "Trivia Maze V0.01"));
        helpMenu.add(about);
        helpMenu.add(aboutRoom);

        menuBar.add(helpMenu);
        menuBar.add(playerMenu);
        return menuBar;

    }


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
                    if(myUnlockPotion > 0) {
                        myPotionItem.setEnabled(true);
                    } else {
                        myPotionItem.setEnabled(false);
                    }
                } else {
                    //
                }
                break;
            case RESET_COMMAND:
                int option = JOptionPane.showConfirmDialog(this,"Are you sure you want to restart the game? It will take you to the title page.",
                        "Confirm", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                    myTriviaMaze.getQF().setInstance();
                    new TitleScreen();
                } else {
                    //
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
                myTriviaMaze.setMaze(loaded);
                newGUI.setPlayerLocation(loaded.getMyRow(), loaded.getMyCol());
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
    public void setPlayerLocation(final int theRow, final int theCol) {
        myTriviaMaze.getMyMaze().setCurrentLocation(theRow, theCol);
        myMazePanel.setColor(theRow, theCol);
    }
    public boolean checkPotion() {
        if(myTriviaMaze.getRow() == myTriviaMaze.getMyMaze().placePotionRow() && myTriviaMaze.getCol() ==
                myTriviaMaze.getMyMaze().placePotionCol() && !myYetFound) {
            myUnlockPotion++;
            myYetFound = true;
            return true;
        } else {
            return false;
        }
    }

    public void messagePotion(final int theM) {
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
                end(myPlayerLost + "Stats: " + myPanel.getCorrect() + " correct.\n" +
                        myPanel.getIncorrect() +  " incorrect.\n" +
                        myMoves +  " moves taken.\n Do you want to play again?");
            }
        }

    }

    public void usePotion() {
        myUnlockPotion--;
        myTriviaMaze.unlockAll();
        myMazePanel.useCheat();
        myMazePanel.paintComponent(myMazePanel.getGraphics());
        updateCount();

    }
    public void updateCount() {
        myPotionItem.setText("Potion");
    }
    public void setUpBut() {
        myUp.setEnabled(true);
        myDown.setEnabled(true);
        myRight.setEnabled(true);
        myLeft.setEnabled(true);
    }

    public void setDisableUp() {
        myUp.setEnabled(false);
    }
    public void setDisableDown() {
        myDown.setEnabled(false);
    }
    public void setDisableLeft() {
        myLeft.setEnabled(false);
    }
    public void setDisableRight() {
        myRight.setEnabled(false);
    }
    public void setUp(final boolean theB) {
        myHitUp = theB;
    }
    public void setDown(final boolean theB) {
        myHitDown = theB;
    }
    public void setLeft(final boolean theB) {
        myHitLeft = theB;
    }
    public void setRight(final boolean theB) {
        myHitRight = theB;
    }

    public boolean getMyUp() {
        return myHitUp;
    }
    public boolean getMyDown() {
        return myHitDown;
    }
    public boolean getMyLeft() {
        return myHitLeft;
    }
    public boolean getMyRight() {
        return myHitRight;
    }

    public void changePosition() {
        myMazePanel.setColor(myTriviaMaze.getRow() , myTriviaMaze.getCol() );
    }

    public void checkEnd() {
        if (myTriviaMaze.getRow() == myTriviaMaze.getExitRow()  &&
                myTriviaMaze.getCol() == myTriviaMaze.getExitCol() ) {
            playWonSound();
            myUnlockPotion = 0;
            end(myPlayerWon + "Stats: " + myPanel.getCorrect() + " correct.\n" +
                    myPanel.getIncorrect() +  " incorrect.\n" +
                    myMoves +  " moves taken.\n Do you want to play again?");
        }
    }

    public void playerLost() {
        String message = myPlayerLost + "Stats: " + myPanel.getCorrect() + " correct.\n" +
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
        } else {
            // do- nothing
        }
    }

    public void checkExitEnd() {
        int exitRow = myTriviaMaze.getExitRow() - 1;
        int exitCol = myTriviaMaze.getExitCol() - 1;
        Room[][] room = myTriviaMaze.getMaze();

        String message = myPlayerLost + "Stats: " + myPanel.getCorrect() + " correct.\n" +
                myPanel.getIncorrect() +  " incorrect.\n" +
                myMoves +  " moves taken.\n Do you want to play again?";
        if(room[exitRow][exitCol].getDoors().getMyNorthDoor().isLocked() && room[exitRow][exitCol].getDoors().getMyWestDoor().isLocked()) {
            playLostSound();
            end(message);
        }
    }

    public void disableButtons() {
        myUp.setEnabled(false);
        myDown.setEnabled(false);
        myLeft.setEnabled(false);
        myRight.setEnabled(false);
    }
    public void updateButtonState() {
        int row = myTriviaMaze.getRow();
        int col = myTriviaMaze.getCol();

        myUp.setEnabled(!myTriviaMaze.getCurrentRoom().getDoors().getMyNorthDoor().isLocked() && row > 0);
        myDown.setEnabled(!myTriviaMaze.getCurrentRoom().getDoors().getMySouthDoor().isLocked() && row < mySize - 1);
        myLeft.setEnabled(!myTriviaMaze.getCurrentRoom().getDoors().getMyWestDoor().isLocked() && col > 0);
        myRight.setEnabled(!myTriviaMaze.getCurrentRoom().getDoors().getMyEastDoor().isLocked() && col < mySize - 1);
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
    public void end(final String theMessage) {
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

    private void playWonSound() {
        if (myGameWon.isRunning())
            myGameWon.stop();
        myGameWon.setFramePosition(0);
        myGameWon.start();
    }

    private void playLostSound() {
        if (myGameLost.isRunning())
            myGameLost.stop();
        myGameLost.setFramePosition(0);
        myGameLost.start();
    }
}
