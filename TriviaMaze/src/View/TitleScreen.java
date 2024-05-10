package View;
import Controller.TriviaMaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import javax.imageio.ImageIO;

public class TitleScreen extends JFrame implements ActionListener {

    private JButton myStartButton;
    private JTextField myTextName;
    private TriviaMaze myTM = new TriviaMaze();
    private ButtonGroup myButtonGroup;
    private JRadioButton myEasy, myMedium, myHard, myExtreme;

    public TitleScreen() {

        initGUI();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trivia Maze Title Screen");
        setSize(800, 600); // Set your preferred size
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void initGUI() {
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BorderLayout());

        // Load and display your image
        BufferedImage image = loadImage("OIP (1).jpg");
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        masterPanel.add(imageLabel, BorderLayout.CENTER);

        // Create and add a text input field
        myTextName = new JTextField(20);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter your name:"));
        inputPanel.add(myTextName);
        masterPanel.add(inputPanel, BorderLayout.NORTH);


        JPanel difficulty = new JPanel();
        difficulty.setLayout(new BoxLayout(difficulty, BoxLayout.Y_AXIS));
        difficulty.add(new JLabel("Select Difficulty: "));
        myButtonGroup = new ButtonGroup();
        myEasy = new JRadioButton("Easy");
        myMedium = new JRadioButton("Medium");
        myHard = new JRadioButton("Hard");
        myExtreme = new JRadioButton("Extreme");

        myButtonGroup.add(myEasy);
        myButtonGroup.add(myMedium);
        myButtonGroup.add(myHard);
        myButtonGroup.add(myExtreme);

        difficulty.add(myEasy);
        difficulty.add(myMedium);
        difficulty.add(myHard);
        difficulty.add(myExtreme);
        masterPanel.add(difficulty, BorderLayout.WEST);

        myStartButton = new JButton("Start");
        myStartButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(myStartButton);
        masterPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(masterPanel);

    }

    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myStartButton) {
            setVisible(false);
            myTM.setName(myTextName.getText());
            String getDifficulty = getSelectedAnswer();
            if(getDifficulty.equals("Easy")) {
                new TriviaMazeGUI(myTM, 4);
            } else if (getDifficulty.equals("Medium")) {
                new TriviaMazeGUI(myTM, 6);
            } else if (getDifficulty.equals("Hard")) {
                new TriviaMazeGUI(myTM, 8);
            } else {
                new TriviaMazeGUI(myTM, 10);
            }

        }
    }

    public String getSelectedAnswer() {
        for (AbstractButton button : Collections.list(myButtonGroup.getElements())) {
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "";
    }

}

