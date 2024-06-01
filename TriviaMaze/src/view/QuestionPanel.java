package View;

import Controller.TriviaMaze;
import Model.Question;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class QuestionPanel extends JPanel {

    private static final String SUBMIT_TEXT = "Submit";
    private static final String CORRECT_SOUND = "correctbuzz.wav";
    private static final String INCORRECT_SOUND = "incorrectbuzz.wav";
    private static final Dimension PANEL_SIZE = new Dimension(400, 400);
    private Clip myIncorrectSound;
    private Clip myCorrectSound;

    private Question myQuestion;

    private final ButtonGroup myButtonGroup;
    private final ArrayList<JToggleButton> myAnswerButtons;
    private final JButton mySubmitButton;

    private JTextArea myQuestionLabel;
    private int myDir;
    private final TriviaMaze myMaze;
    private int myCorrect;
    private int myIncorrect;
    private int myCheckAnswer;
    private JTextField myShortAnswerField;
    private final TriviaMazeGUI myView;

    public QuestionPanel(final TriviaMaze theMaze, final TriviaMazeGUI theView) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(PANEL_SIZE);
        myView = theView;
        myMaze = theMaze;
        myButtonGroup = new ButtonGroup();
        myAnswerButtons = new ArrayList<>();
        mySubmitButton = new JButton(SUBMIT_TEXT);
        mySubmitButton.addActionListener(theEvent -> {
           mySubmitButton.setEnabled(false);
           checkAnswer();
        });
        mySubmitButton.setVisible(false);
        mySubmitButton.setEnabled(false);
        try {
            myCorrectSound = AudioSystem.getClip();
            AudioInputStream correctStream = AudioSystem.getAudioInputStream(new File(CORRECT_SOUND));
            myCorrectSound.open(correctStream);

            myIncorrectSound = AudioSystem.getClip();
            AudioInputStream incorrectStream = AudioSystem.getAudioInputStream(new File(INCORRECT_SOUND));
            myIncorrectSound.open(incorrectStream);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        setComponents();
    }

    public void setQuestion(final Question theQuestion) {
        if(theQuestion == null) {
            throw new IllegalArgumentException("Question cannot be null.");
        }
        myQuestion = theQuestion;

    }

    private void playCorrectSound() {
        if (myCorrectSound.isRunning())
            myCorrectSound.stop();
        myCorrectSound.setFramePosition(0);
        myCorrectSound.start();
    }


    private void playIncorrectSound() {
        if (myIncorrectSound.isRunning())
            myIncorrectSound.stop();
        myIncorrectSound.setFramePosition(0);
        myIncorrectSound.start();
    }

    public void setDir(final int theDir) {
        if(theDir < 0 || theDir > 3) {
            throw new IllegalArgumentException("Invalid direction.");
        }
        myDir = theDir;
    }

    public void updateQuestion(final Question theQ) {
        myCheckAnswer = 0;
        setQuestion(theQ);
        myQuestion = theQ;
        String question = myQuestion.getQuestionText();
        ArrayList<String> options = myQuestion.getOptions();

        myQuestionLabel.setText("Question: " + question);
        myQuestionLabel.setVisible(true);
        mySubmitButton.setVisible(true);
        mySubmitButton.setEnabled(false);

        if(theQ.getType() == 1) { // multiple choice
            for (int i = 0; i < options.size(); i++) {
                myAnswerButtons.get(i).setEnabled(true);
                myAnswerButtons.get(i).setVisible(true);
                myAnswerButtons.get(i).setText(options.get(i));
            }
        } else if (theQ.getType() == 2) { //true false
            for (int i = 0; i < options.size() / 2; i++) {
                myAnswerButtons.get(i).setEnabled(true);
                myAnswerButtons.get(i).setVisible(true);
                myAnswerButtons.get(i).setText(options.get(i));
            }
        } else if (theQ.getType() == 3) { // short answer
            myShortAnswerField.setVisible(true);
            myShortAnswerField.setEditable(true);
        } else {
            throw new IllegalArgumentException("no valid question types found!");
        }
    }


    private void setComponents() {

        // Text area for the body of the question
        myQuestionLabel = new JTextArea();
//        myQuestionLabel.setBackground(this.getBackground());
        myQuestionLabel.setLineWrap(true);
        myQuestionLabel.setWrapStyleWord(true);
        myQuestionLabel.setEditable(false);
        myQuestionLabel.setFont(new Font("Monospace", Font.PLAIN, 16));
        myQuestionLabel.setPreferredSize(new Dimension(400, 200));
        myQuestionLabel.setVisible(false);
        add(myQuestionLabel);

        // Short answer field
        myShortAnswerField = new JTextField(20);
        myShortAnswerField.setVisible(false);
        myShortAnswerField.addActionListener(theEvent -> {
            if (myCheckAnswer == 0) {
                mySubmitButton.setEnabled(true);
            }
        });
        add(myShortAnswerField);

        // Panel to hold answer and submit buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,1));
        for (int i = 0; i < 4; i++) {
            JToggleButton tb = new JToggleButton();
            tb.addActionListener(theEvent -> {
                if(myCheckAnswer == 0) {
                    mySubmitButton.setEnabled(true);
                }
            });
            myButtonGroup.add(tb);
            myAnswerButtons.add(tb);
            panel.add(tb);
            tb.setVisible(false);
        }
        panel.add(new Container());
        panel.add(mySubmitButton);
        add(panel);
    }

    private String getSelectedAnswer() {
        String answerText = "";
        for (AbstractButton button : Collections.list(myButtonGroup.getElements())) {
            if (button.isSelected()) {
                answerText = button.getText();
            }
        }
        return answerText;
    }

    private void checkAnswer() {
        String correctAnswer = myQuestion.getCorrectAnswer();
        if (correctAnswer.equalsIgnoreCase(getSelectedAnswer())) {
            playCorrectSound();
            JOptionPane.showMessageDialog(this, "Correct!");
            myCorrect++;
            if (myView.getMyUpButton()) {
                myMaze.advanceNorth(myView.getQPanel());
                myView.changePosition();
                myView.setUpBut();
                myView.checkEnd();
                myView.updateButtonState();
                myView.setUp(false);
            }
            if (myView.getMyDown()) {
                myMaze.advanceSouth(myView.getQPanel());
                myView.changePosition();
                myView.setUpBut();
                myView.checkEnd();
                myView.updateButtonState();
                myView.setDown(false);

            }
            if (myView.getMyLeft()) {
                myMaze.advanceWest(myView.getQPanel());
                myView.changePosition();
                myView.setUpBut();
                myView.checkEnd();
                myView.updateButtonState();
                myView.setLeft(false);
            }
            if (myView.getMyRight()) {
                myMaze.advanceEast(myView.getQPanel());
                myView.changePosition();
                myView.setUpBut();
                myView.checkEnd();
                myView.updateButtonState();
                myView.setRight(false);
            }
        }
        else {
            myIncorrect++;
            playIncorrectSound();
            JOptionPane.showMessageDialog(this, "Incorrect, Door locked, the answer was: " + correctAnswer);
            myMaze.lockDoor(myDir);
            myView.setUpBut();
            myView.updateButtonState();
            myView.playerLost();
            myView.checkExitEnd();
            if (myView.getMyUpButton()) {
                myView.setDisableUp();
                myView.setUp(false);
                myView.changePosition();
            }
            if (myView.getMyDown()) {
                myView.setDisableDown();
                myView.setDown(false);
                myView.changePosition();

            }
            if (myView.getMyRight()) {
                myView.setDisableRight();
                myView.setRight(false);
                myView.changePosition();

            }
            if (myView.getMyLeft()) {
                myView.setDisableLeft();
                myView.setLeft(false);
                myView.changePosition();

            }
        }
        myButtonGroup.clearSelection();
        for (JToggleButton tb : myAnswerButtons) {
            tb.setVisible(false);
        }
        myQuestionLabel.setVisible(false);
        myShortAnswerField.setText("");
        myShortAnswerField.setVisible(false);
        mySubmitButton.setVisible(false);
    }

    public int getCorrect() {
        return myCorrect;
    }

    public int getIncorrect() {
        return myIncorrect;
    }
}
