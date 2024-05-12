package View;

import Controller.TriviaMaze;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;

public class TitleScreen extends JFrame implements ActionListener {

    private JButton myStartButton;
    private JTextField myTextName;
    private TriviaMaze myTM = new TriviaMaze();
    private ButtonGroup myButtonGroup;
    private JRadioButton myEasy, myMedium, myHard, myExtreme;
    private JButton mySet;
    private JButton myNameSet;

    public TitleScreen() {

        initGUI();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Movie Trivia Maze");
        setSize(800, 600);
        setLocationRelativeTo(null);
        addListener();
    }

    private void initGUI() {

        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BorderLayout());
        masterPanel.setBackground(Color.WHITE);
        // Load and display your image
        BufferedImage image = loadImage("movie.png");
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setPreferredSize(new Dimension(800,600));
        masterPanel.add(imageLabel, BorderLayout.CENTER);

        // Create and add a text input field
        myTextName = new JTextField(20);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter your name:"));
        inputPanel.add(myTextName);
        myNameSet = new JButton("Set");
        myNameSet.setEnabled(true);
        inputPanel.add(myNameSet);
        inputPanel.setBackground(Color.WHITE);
        masterPanel.add(inputPanel, BorderLayout.NORTH);


        JPanel difficulty = new JPanel();
        difficulty.setBackground(Color.WHITE);
        difficulty.setLayout(new BoxLayout(difficulty, BoxLayout.Y_AXIS));
        difficulty.add(new JLabel("Select Difficulty: "));
        myButtonGroup = new ButtonGroup();
        myEasy = new JRadioButton("Easy");
        myMedium = new JRadioButton("Medium");
        myHard = new JRadioButton("Hard");
        myExtreme = new JRadioButton("Extreme");
        mySet = new JButton("Set");
        mySet.setEnabled(false);
        myEasy.setBackground(Color.WHITE);
        myMedium.setBackground(Color.WHITE);
        myHard.setBackground(Color.WHITE);
        myExtreme.setBackground(Color.WHITE);
        myButtonGroup.add(myEasy);
        myButtonGroup.add(myMedium);
        myButtonGroup.add(myHard);
        myButtonGroup.add(myExtreme);
        myButtonGroup.add(mySet);
        difficulty.add(myEasy);
        difficulty.add(myMedium);
        difficulty.add(myHard);
        difficulty.add(myExtreme);
        difficulty.add(mySet);
        masterPanel.add(difficulty, BorderLayout.WEST);
        myEasy.setEnabled(false);
        myMedium.setEnabled(false);
        myHard.setEnabled(false);
        myExtreme.setEnabled(false);
        mySet.setEnabled(false);
        myStartButton = new JButton("Start");
        myStartButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(myStartButton);
        myStartButton.setEnabled(false);
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
                new TriviaMazeGUI(myTM, 4, "Easy");
            } else if (getDifficulty.equals("Medium")) {
                new TriviaMazeGUI(myTM, 6, "Medium");
            } else if (getDifficulty.equals("Hard")) {
                new TriviaMazeGUI(myTM, 8, "Hard");
            } else {
                new TriviaMazeGUI(myTM, 10, "Extreme");
            }

        }
    }
    public void addListener() {
        myEasy.addActionListener(theEvent -> {
            mySet.setEnabled(true);
        });
        myMedium.addActionListener(theEvent -> {
            mySet.setEnabled(true);
        });
        myHard.addActionListener(theEvent -> {
            mySet.setEnabled(true);
        });
        myExtreme.addActionListener(theEvent -> {
            mySet.setEnabled(true);
        });
        mySet.addActionListener(theEvent -> {
            myEasy.setEnabled(false);
            myMedium.setEnabled(false);
            myHard.setEnabled(false);
            myExtreme.setEnabled(false);
            mySet.setEnabled(false);
            myStartButton.setEnabled(true);

        });
        myNameSet.addActionListener(theEvent -> {
            if (myTextName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No empty names!");
                myNameSet.setEnabled(true);

            } else {
                // Enable the component if the text field is not empty
                myTextName.setEditable(false);
                myNameSet.setEnabled(false);
                myEasy.setEnabled(true);
                myMedium.setEnabled(true);
                myHard.setEnabled(true);
                myExtreme.setEnabled(true);
            }


        });


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

