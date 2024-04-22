package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class TriviaMazeGUI extends JFrame implements ActionListener {

    private static final String TITLE = "Trivia Maze";

    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

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
    //private final PropertyChangeEnabledTriviaMazeControls myTriviaMaze;

    // Constructor

    /**
     * Constructs a new RoadRageGUI, using the files in the current working
     * directory.
     */
    public TriviaMazeGUI() {
        super(TITLE);
        // initialize instance fields

        initGUI();


        setVisible(true);
    }

    private void initGUI() {
        final TriviaMazePanel tmp = new TriviaMazePanel(4, 4);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
