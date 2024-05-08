package view;

import controller.PropertyChangeEnabledTriviaMazeControls;
import controller.TriviaMaze;
import model.Direction;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public final class TriviaMazeGUI extends JFrame implements ActionListener {

    /**
     * The main title for the program.
     */
    private static final String TITLE = "Trivia Maze";

    /**
     * The Toolkit used.
     */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /**
     * The size of the current screen.
     */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /**
     * The New Game command.
     */
    private static final String NEW_GAME_COMMAND = "New Game";
    private static final String SAVE_COMMAND = "Save Game";
    private static final String LOAD_COMMAND = "Load Game";
    private static final String QUIT_COMMAND = "Quit";
    private static final String RULES_COMMAND = "Rules";
    private static final String ABOUT_COMMAND = "About";
    private static final String SAVE_POPUP_MESSAGE = "Would you like to save before ending the game?";
    private static final String POPUP_TITLE = "Unsaved Game!";

    private static final ArrayList<BasicArrowButton> MOVE_BUTTONS = new ArrayList<>();


    /**
     * The logic for the simulation.
     */
    private final PropertyChangeEnabledTriviaMazeControls myTriviaMaze;

    private final Container myMainPanel;
    private final Container myTitlePanel;

    /**
     * Constructs a new RoadRageGUI, using the files in the current working
     * directory.
     */
    public TriviaMazeGUI() {
        super(TITLE);
        // initialize instance fields

        myTriviaMaze = new TriviaMaze();
        myMainPanel = new JPanel(new BorderLayout());
        myTitlePanel = new JPanel(new BorderLayout());
        createTitleScreen();
        createMainPanel();
        createMenuBar();
        setContentPane(myTitlePanel);
//        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
//                SCREEN_SIZE.height / 2 - getHeight() / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void createTitleScreen() {
        myTitlePanel.add(makeButton(NEW_GAME_COMMAND), BorderLayout.WEST);
        myTitlePanel.add(makeButton(LOAD_COMMAND), BorderLayout.EAST);
        pack();
    }

    private void createMainPanel() {
        final TriviaMazePanel mazePanel = new TriviaMazePanel();
        final QuestionPanel qPanel = new QuestionPanel();
        myTriviaMaze.addPropertyChangeListener(mazePanel);
        myTriviaMaze.addPropertyChangeListener(qPanel);

        final JCheckBox debugBox = new JCheckBox("Debug");
        debugBox.addChangeListener(mazePanel);
        debugBox.addChangeListener(qPanel);

        final Container movePanel = createMovePanel();

        myMainPanel.add(mazePanel, BorderLayout.WEST);
        myMainPanel.add(qPanel, BorderLayout.EAST);
        myMainPanel.add(movePanel, BorderLayout.SOUTH);

        pack();
    }

    // Arrow buttons for movement
    private JComponent createMovePanel() {

        // TODO: fetch player position and disable direction buttons as needed
        final JComponent south = new JPanel();
        south.setLayout(new FlowLayout());
        for (final Direction d : Direction.values()) {
            BasicArrowButton ab = null;
            switch (d) {
                case NORTH:
                    ab = new BasicArrowButton(BasicArrowButton.NORTH);
                    ab.setMnemonic(KeyEvent.VK_UP);
                    break;
                case SOUTH:
                    ab = new BasicArrowButton(BasicArrowButton.SOUTH);
                    ab.setMnemonic(KeyEvent.VK_DOWN);
                    break;
                case EAST:
                    ab = new BasicArrowButton(BasicArrowButton.EAST);
                    ab.setMnemonic(KeyEvent.VK_RIGHT);
                    break;
                case WEST:
                    ab = new BasicArrowButton(BasicArrowButton.WEST);
                    ab.setMnemonic(KeyEvent.VK_LEFT);
                    break;
                default:
            }
            ab.setToolTipText("Move " + d.getDirection());
            ab.addActionListener(theEvent -> firePropertyChange("MOVE", null, d));
            MOVE_BUTTONS.add(ab);
            south.add(ab);
        }
        return south;
    }

    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();

        final JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        gameMenu.add(makeButton(NEW_GAME_COMMAND));
        gameMenu.add(makeButton(SAVE_COMMAND));
        gameMenu.add(makeButton(LOAD_COMMAND));
        gameMenu.add(makeButton(QUIT_COMMAND));
        menuBar.add(gameMenu);

        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.add(makeButton(RULES_COMMAND));
        helpMenu.add(makeButton(ABOUT_COMMAND));
        menuBar.add(helpMenu);

        return menuBar;

    }

    private AbstractButton makeButton(final String theText) {
        final AbstractButton item = new AbstractButton() {
        };
        final char mnemonic = theText.charAt(0);
        item.setMnemonic(mnemonic);
        item.addActionListener(this);
        return item;
    }

    private boolean displaySavePopup() {
        final int input = JOptionPane.showConfirmDialog(null,
                SAVE_POPUP_MESSAGE, POPUP_TITLE,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (input == JOptionPane.CANCEL_OPTION) {
            return false;
        }
        else {
            if (input == JOptionPane.YES_OPTION) {
                // save command
            }
            return true;
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param theEvent the event to be processed
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final String command = theEvent.getActionCommand().intern();
        switch (command) {
            case NEW_GAME_COMMAND:
                if (getContentPane().equals(myMainPanel)) {
                    if (displaySavePopup()) {
                        myTriviaMaze.reset();
                    }
                }
                else {
                    setContentPane(myMainPanel);
                    myTriviaMaze.start();
                }
                break;
            case SAVE_COMMAND:
                // save command
                break;
            case LOAD_COMMAND:
                if (displaySavePopup()) {
                    setContentPane(myMainPanel);
                    // load command
                }
                break;
            case QUIT_COMMAND:
                if (displaySavePopup()) {
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                }
                break;
            case RULES_COMMAND:
                JOptionPane.showMessageDialog(null, "Rules go here!",
                        "Rules", JOptionPane.PLAIN_MESSAGE);
                break;
            case ABOUT_COMMAND:
                JOptionPane.showMessageDialog(null, "Trivia Maze V0.01",
                        "About", JOptionPane.PLAIN_MESSAGE);
                break;
            default:
        }
    }

}
