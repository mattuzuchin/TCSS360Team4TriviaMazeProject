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
     * The Start command.
     */
    private static final String START_COMMAND = "Start";

    /**
     * The Stop command.
     */
    private static final String STOP_COMMAND = "Stop";

    /**
     * The Reset command.
     */
    private static final String RESET_COMMAND = "Reset";

    /**
     * The logic for the simulation.
     */
    private final PropertyChangeEnabledTriviaMazeControls myTriviaMaze;

    /**
     * Constructs a new RoadRageGUI, using the files in the current working
     * directory.
     */
    public TriviaMazeGUI() {
        super(TITLE);
        // initialize instance fields


        myTriviaMaze = new TriviaMaze();
        initGUI();
        createMenuBar();

        setVisible(true);
    }

    private void initGUI() {
        final TriviaMazePanel mazePanel = new TriviaMazePanel(4);
        final QuestionPanel qPanel = new QuestionPanel();
        myTriviaMaze.addPropertyChangeListener(mazePanel);
        myTriviaMaze.addPropertyChangeListener(qPanel);

        // TO DO:
        /*
        Menu
        Keyboard shortcuts
        Control buttons
        Debug check
         */

        final JCheckBox debugBox = new JCheckBox("Debug");
        debugBox.addChangeListener(mazePanel);
        debugBox.addChangeListener(qPanel);

        final Container northPanel = new JPanel(new FlowLayout());

        final Container southPanel = new JPanel(new FlowLayout());

        final Container masterPanel = new JPanel(new BorderLayout());
        masterPanel.add(mazePanel, BorderLayout.WEST);
        masterPanel.add(qPanel, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(masterPanel);
        pack();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
    }

    private JComponent createNorthPanel() {

    }

    private JComponent createSouthPanel() {
        // Movement buttons
        final JComponent south = new JPanel();
        south.setLayout(new FlowLayout());
        for (final Direction d : Direction.values()) {
            BasicArrowButton ab = null;
            switch (d) {
                case NORTH:
                    ab = new BasicArrowButton(BasicArrowButton.NORTH);
                    break;
                case SOUTH:
                    ab = new BasicArrowButton(BasicArrowButton.SOUTH);
                    break;
                case EAST:
                    ab = new BasicArrowButton(BasicArrowButton.EAST);
                    break;
                case WEST:
                    ab = new BasicArrowButton(BasicArrowButton.WEST);
                    break;
                default:
            }
            ab.addActionListener(theEvent -> firePropertyChange("MOVE", null, d));
            south.add(ab);
        }
        south.setBorder(border);
        return south;
    }

    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();

        final JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        for (final Action action : myActionList) {
            final JMenuItem menuButton = new JMenuItem(action);
            gameMenu.add(menuButton);
        }
        menuBar.add(gameMenu);

        final JMenu helpMenu = new JMenu("Help");
        gameMenu.setMnemonic(KeyEvent.VK_H);

        final JMenuItem rules = new JMenuItem("Rules");
        rules.setMnemonic(KeyEvent.VK_R);
        rules.addActionListener(theEvent -> JOptionPane.showMessageDialog(null, "No gods, no masters!",
                                                                           "Rules", JOptionPane.PLAIN_MESSAGE));
        final JMenuItem about = new JMenuItem("About");
        about.setMnemonic(KeyEvent.VK_A);
        about.addActionListener(theEvent -> JOptionPane.showMessageDialog(null, "Trivia Maze V0.01",
                                                                            "About", JOptionPane.PLAIN_MESSAGE));
        helpMenu.add(about);
        helpMenu.add(rules);
        menuBar.add(helpMenu);

        return menuBar;

    }

    private JButton makeButton(final String theText) {
        final JButton button = new JButton(theText);
        button.addActionListener(this);
        return button;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param theEvent the event to be processed
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final Object source = theEvent.getSource();

    }

    private static final class MoveAction extends AbstractAction {
        private final Direction myDirection;
        MoveAction(final Direction theDirection, Direction myDirection) {
            super();
            this.myDirection = myDirection;
        }
        /**
         * Invoked when an action occurs.
         *
         * @param theEvent the event to be processed
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {

        }
    }
}
