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
import java.io.IOException;
import java.util.ArrayList;

public class TitleScreen extends JFrame implements ActionListener {

    private JButton myStartButton;
    private JTextField myTextName;
    private TriviaMaze myTM;
    private ArrayList<JToggleButton> myButtons;
    private ButtonGroup myButtonGroup;
    private QuestionFactory myFactory;


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

    private void initGUI() {

        JPanel masterPanel = new JPanel(new BorderLayout());
        masterPanel.setBackground(Color.WHITE);
        setBackground(Color.WHITE);

        // Create and add a text input field
        myTextName = new JTextField(20);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter your name:"));
        inputPanel.add(myTextName);
        inputPanel.setBackground(Color.WHITE);
        masterPanel.add(inputPanel, BorderLayout.NORTH);

        // Load and display your image
        BufferedImage image = loadImage("movie.png");
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setPreferredSize(new Dimension(800,600));
        masterPanel.add(imageLabel);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

        // Create difficulty buttons
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

        // Create start button
        myStartButton = new JButton("Start");
        myStartButton.setBackground(Color.WHITE);
        myStartButton.addActionListener(this);
        myStartButton.setEnabled(false);
        southPanel.add(myStartButton);
        southPanel.setBackground(Color.WHITE);

        masterPanel.add(southPanel, BorderLayout.SOUTH);
        add(masterPanel);
    }

    private BufferedImage loadImage(final String theImagePath) {
        try {
            return ImageIO.read(new File(theImagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (theEvent.getSource() == myStartButton) {
            if (myTextName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No empty names!");
                myTextName.requestFocusInWindow();
            } else if (myTextName.getText().trim().equals("test")) {
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
            }else {
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