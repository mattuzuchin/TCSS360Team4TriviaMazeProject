package View;

import Controller.TriviaMaze;
import Model.Difficulty;
import Model.QuestionFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 * The opening window for Movie Trivia Maze, which prompts the user to enter their name
 * and select a difficulty.
 *
 * @author Matthew Uzunoe-Chin, Dustin Feldt, Elias Arriolas
 * @version Spring 2024
 */
public class TitleScreen extends JFrame implements ActionListener {

    /**
     * The title of the game.
     */
    private static final String TITLE = "Movie Trivia Maze";

    /**
     * The username prompt text.
     */
    private static final String INPUT_TEXT = "Enter your name: ";

    /**
     * The difficulty selection prompt.
     */
    private static final String SELECT_DIFF_TEXT = "Select a difficulty: ";

    /**
     * The logo icon filename.
     */
    private static final String ICON_NAME = "movie.png";

    /**
     * The text for the Start button.
     */
    private static final String START_COMMAND = "Start";

    /**
     * The size of the username input field.
     */
    private static final int INPUT_COLUMNS = 20;

    /**
     * The window dimension.
     */
    private static final Dimension TITLE_DIMENSION = new Dimension(800, 600);

    /**
     * The Start button.
     */
    private final JButton myStartButton;

    /**
     * The username text input field.
     */
    private final JTextField myTextName;

    /**
     * The TriviaMaze object to be used for the game.
     */
    private final TriviaMaze myTM;

    /**
     * The difficulty option buttons.
     */
    private final ArrayList<JToggleButton> myDifficultyButtons;

    /**
     * Constructor for TitleScreen.
     */
    public TitleScreen() {
        myDifficultyButtons = new ArrayList<>();
        final QuestionFactory factory = QuestionFactory.getInstance();
        myTM = new TriviaMaze(factory);
        myTextName = new JTextField(INPUT_COLUMNS);
        myStartButton = new JButton(START_COMMAND);
        initGUI();
        setTitle(TITLE);
        setSize(TITLE_DIMENSION);
        setLocationRelativeTo(null);
    }

    /**
     * Creates the GUI components and displays them.
     */
    private void initGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel masterPanel = new JPanel(new BorderLayout());
        masterPanel.setBackground(Color.WHITE);
        setBackground(Color.WHITE);

        // Create and add a text input field
        final JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel(INPUT_TEXT));
        inputPanel.add(myTextName);
        inputPanel.setBackground(Color.WHITE);
        masterPanel.add(inputPanel, BorderLayout.NORTH);

        // Load and display your image
        final BufferedImage image = loadImage();
        assert image != null;
        final JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setPreferredSize(TITLE_DIMENSION);
        masterPanel.add(imageLabel);

        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

        // Create difficulty buttons
        final JPanel difficultyPanel = new JPanel();
        difficultyPanel.setBackground(Color.WHITE);
        difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.X_AXIS));
        difficultyPanel.add(new JLabel(SELECT_DIFF_TEXT));
        final ButtonGroup bg = new ButtonGroup();
        for (Difficulty d : Difficulty.values()) {
            final JToggleButton button = new JToggleButton(d.getName());
            button.setBackground(Color.WHITE);
            button.addActionListener(theEvent -> myStartButton.setEnabled(true));
            bg.add(button);
            myDifficultyButtons.add(button);
            difficultyPanel.add(button);
        }
        southPanel.add(difficultyPanel);

        // Create start button
        myStartButton.setBackground(Color.WHITE);
        myStartButton.addActionListener(this);
        myStartButton.setEnabled(false);

        southPanel.add(myStartButton);
        southPanel.setBackground(Color.WHITE);

        masterPanel.add(southPanel, BorderLayout.SOUTH);
        add(masterPanel);
        setVisible(true);
    }

    /**
     * Load the logo image.
     *
     * @return the image, or null if an exception is thrown.
     */
    private BufferedImage loadImage() {
        try {
            return ImageIO.read(new File(ICON_NAME));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return null;
        }
    }

    /**
     * Sets the Action performed by the Start button.
     *
     * @param theEvent the event to be processed
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (theEvent.getSource() == myStartButton) {
            if (myTextName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No empty names!");
                myTextName.requestFocusInWindow();
            } else if (myTextName.getText().trim().equals("Han Solo")) {
                // Secret name gives the player a potion on game start
                setVisible(false);
                myTM.setName(myTextName.getText());
                JOptionPane.showMessageDialog(this,
                        "Congrats! You earned a free potion for using the name " + myTextName.getText());
                String getDifficulty = null;
                for (JToggleButton button : myDifficultyButtons) {
                    if (button.isSelected()) {
                        getDifficulty = button.getText();
                    }
                }
                for (Difficulty d : Difficulty.values()) {
                    assert getDifficulty != null;
                    if (getDifficulty.equals(d.getName())) {
                        new TriviaMazeGUI(myTM, d.getSize(d.getName()), d.name(), 1);
                    }
                }
            } else {
                setVisible(false);
                myTM.setName(myTextName.getText());
                String getDifficulty = null;
                for (JToggleButton button : myDifficultyButtons) {
                    if (button.isSelected()) {
                        getDifficulty = button.getText();
                    }
                }
                for (Difficulty d : Difficulty.values()) {
                    assert getDifficulty != null;
                    if (getDifficulty.equals(d.getName())) {
                        new TriviaMazeGUI(myTM, d.getSize(d.getName()), d.name());
                    }
                }
            }
        }
    }
}