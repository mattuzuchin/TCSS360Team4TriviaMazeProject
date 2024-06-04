package View;

import Controller.TriviaMaze;
import Model.Difficulty;
import Model.QuestionFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 * The TitleScreen class represents the title screen for the Trivia Maze game.
 * It allows the player to enter their name, select a difficulty level, and start the game.
 * @author Matthew Uzunoe-Chin, Dustin Feldt, Elias Arriolas
 * @version Spring 2024
 */
public class TitleScreen extends JFrame implements ActionListener {

    /**
     * The start button to begin the game.
     */
    private JButton myStartButton;

    /**
     * The text field for entering the player's name.
     */
    private JTextField myTextName;

    /**
     * The TriviaMaze instance that this title screen interacts with.
     */
    private final TriviaMaze myTM;

    /**
     * A list of toggle buttons for selecting the difficulty level.
     */
    private final ArrayList<JToggleButton> myButtons;

    /**
     * A ButtonGroup for grouping the difficulty level toggle buttons.
     */
    private ButtonGroup myButtonGroup;

    /**
     * The QuestionFactory instance for generating questions.
     */
    private final QuestionFactory myFactory;

    /**
     * Constructs a new TitleScreen.
     */
    public TitleScreen() {
        myButtons = new ArrayList<>();
        myFactory = QuestionFactory.getInstance();
        myTM = new TriviaMaze(myFactory);
        initGUI();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Movie Trivia Maze");
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    /**
     * Initializes the GUI components of the title screen.
     */
    private void initGUI() {
        JPanel masterPanel = new JPanel(new BorderLayout());
        masterPanel.setBackground(Color.WHITE);
        setBackground(Color.WHITE);
        myTextName = new JTextField(20);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter your name:"));
        inputPanel.add(myTextName);
        inputPanel.setBackground(Color.WHITE);
        masterPanel.add(inputPanel, BorderLayout.NORTH);
        BufferedImage image = loadImage("movie.png");
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setPreferredSize(new Dimension(800,600));
        masterPanel.add(imageLabel);
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        JPanel difficulty = new JPanel();
        difficulty.setBackground(Color.WHITE);
        difficulty.setLayout(new BoxLayout(difficulty, BoxLayout.X_AXIS));
        difficulty.add(new JLabel("Select Difficulty: "));
        myButtonGroup = new ButtonGroup();
        for (Difficulty d : Difficulty.values()) {
            JToggleButton button = new JToggleButton(d.getName());
            button.setBackground(Color.WHITE);
            button.addActionListener(theEvent -> myStartButton.setEnabled(true));
            myButtonGroup.add(button);
            myButtons.add(button);
            difficulty.add(button);
        }
        southPanel.add(difficulty);
        myStartButton = new JButton("Start");
        myStartButton.setBackground(Color.WHITE);
        myStartButton.addActionListener(this);
        myStartButton.setEnabled(false);
        southPanel.add(myStartButton);
        southPanel.setBackground(Color.WHITE);

        masterPanel.add(southPanel, BorderLayout.SOUTH);
        add(masterPanel);
    }

    /**
     * Loads an image from the specified file path.
     *
     * @param theImagePath the path to the image file
     * @return the loaded BufferedImage, or null if the image could not be loaded
     */
    private BufferedImage loadImage(final String theImagePath) {
        try {
            return ImageIO.read(new File(theImagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Handles action events, such as when the start button is clicked.
     *
     * @param theEvent the action event
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (theEvent.getSource() == myStartButton) {
            if (myTextName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No empty names!");
                myTextName.requestFocusInWindow();
            } else if (myTextName.getText().trim().equals("Han Solo")) {
                setVisible(false);
                myTM.setName(myTextName.getText());
                JOptionPane.showMessageDialog(this, "Congrats! You earned a free potion for using the name " + myTextName.getText());
                String getDifficulty = null;
                for (JToggleButton button : myButtons) {
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
                for (JToggleButton button : myButtons) {
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