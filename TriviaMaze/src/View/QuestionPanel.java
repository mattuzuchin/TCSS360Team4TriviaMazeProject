package View;

import Controller.TriviaMaze;
import Model.Question;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 * Displays the text of the question and handles user input for answering multiple-choice,
 * true/false, and short-answer questions.
 * @author Matthew Uzunoe-Chin, Dustin Feldt, Elias Arriolas
 * @version Spring 2024
 */
public class QuestionPanel extends JPanel {

    /**
     * The text for the Submit button.
     */
    private static final String SUBMIT_TEXT = "Submit";

    /**
     * The message presented following a correct answer.
     */
    private static final String CORRECT_MESSAGE = "Correct!";

    /**
     * The message presented following an incorrect answer.
     */
    private static final String INCORRECT_MESSAGE = "Incorrect, Door locked, the answer was: ";

    /**
     * The filename for the correct answer sound.
     */
    private static final String CORRECT_SOUND = "correctbuzz.wav";

    /**
     * The filename for the incorrect answer sound.
     */
    private static final String INCORRECT_SOUND = "incorrectbuzz.wav";

    /**
     * The size of the panel.
     */
    private static final Dimension PANEL_SIZE = new Dimension(400, 400);

    /**
     * The size of the question body text area.
     */
    private static final Dimension QUESTION_LABEL_SIZE = new Dimension(400, 200);

    /**
     * The font used by the text area.
     */
    private static final Font BODY_FONT = new Font("Monospace", Font.PLAIN, 16);

    /**
     * The size of the short answer user input area.
     */
    private static final int SHORT_ANSWER_COL = 20;

    /**
     * The incorrect answer sound.
     */
    private Clip myIncorrectSound;

    /**
     * The correct answer sound.
     */
    private Clip myCorrectSound;

    /**
     * The question to be displayed.
     */
    private Question myQuestion;

    /**
     * The buttonGroup used for the possible answers.
     */
    private final ButtonGroup myButtonGroup;

    /**
     * The list of answer buttons.
     */
    private final ArrayList<JToggleButton> myAnswerButtons;

    /**
     * The Submit button.
     */
    private final JButton mySubmitButton;

    /**
     * The text area which displays the question.
     */
    private JTextArea myQuestionLabel;

    /**
     * The direction moved in.
     */
    private int myDir;

    /**
     * The TriviaMaze instance used.
     */
    private final TriviaMaze myMaze;

    /**
     * The number of questions answered correctly.
     */
    private int myCorrect;

    /**
     *  The number of questions answered incorrectly.
     */
    private int myIncorrect;

    /**
     * The user input area for short answer questions.
     */
    private JTextField myShortAnswerField;

    /**
     * The TriviaMazeGUI instance holding this panel.
     */
    private final TriviaMazeGUI myView;

    /**
     * Constructor.
     *
     * @param theMaze The TriviaMaze object this class interacts with.
     * @param theView The TriviaMazeGUI object holding this panel.
     */
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
            final AudioInputStream correctStream = AudioSystem.getAudioInputStream(new File(CORRECT_SOUND));
            myCorrectSound.open(correctStream);

            myIncorrectSound = AudioSystem.getClip();
            final AudioInputStream incorrectStream = AudioSystem.getAudioInputStream(new File(INCORRECT_SOUND));
            myIncorrectSound.open(incorrectStream);
        } catch (final LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        setComponents();
    }

    /**
     * Sets the question to be displayed.
     *
     * @param theQuestion the question to display.
     * @throws IllegalArgumentException if theQuestion is null.
     */
    public void setQuestion(final Question theQuestion) {
        if (theQuestion == null) {
            throw new IllegalArgumentException("Question cannot be null.");
        }
        myQuestion = theQuestion;

    }

    /**
     * Plays the sound associated with a correct answer.
     */
    private void playCorrectSound() {
        if (myCorrectSound.isRunning()) {
            myCorrectSound.stop();
        }
        myCorrectSound.setFramePosition(0);
        myCorrectSound.start();
    }

    /**
     * Plays the sound associated with an incorrect answer.
     */
    private void playIncorrectSound() {
        if (myIncorrectSound.isRunning()) {
            myIncorrectSound.stop();
        }
        myIncorrectSound.setFramePosition(0);
        myIncorrectSound.start();
    }

    /**
     * Sets the direction of the door associated with the current question.
     *
     * @param theDir the direction (0 = north, 1 = south, 2 = west, 3 = east).
     * @throws IllegalArgumentException if theDir is not in the range 0-3.
     */
    public void setDir(final int theDir) {
        if (theDir < 0 || theDir > 3) {
            throw new IllegalArgumentException("Invalid direction.");
        }
        myDir = theDir;
    }

    /**
     * Updates the panel to display a new question.
     *
     * @param theQ the new question to display.
     * @throws IllegalArgumentException if the question type is not recognized.
     */
    public void updateQuestion(final Question theQ) {
        setQuestion(theQ);
        myQuestion = theQ;
        final String question = myQuestion.getMyQuestionText();
        final ArrayList<String> options = myQuestion.getOptions();

        myQuestionLabel.setText("Question: " + question);
        myQuestionLabel.setVisible(true);
        mySubmitButton.setVisible(true);
        mySubmitButton.setEnabled(false);

        if (theQ.getType() == 1) { // multiple choice
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

    /**
     * Initializes the components of the panel.
     */
    private void setComponents() {

        // Text area for the body of the question
        myQuestionLabel = new JTextArea();
        myQuestionLabel.setLineWrap(true);
        myQuestionLabel.setWrapStyleWord(true);
        myQuestionLabel.setEditable(false);
        myQuestionLabel.setFont(BODY_FONT);
        myQuestionLabel.setPreferredSize(QUESTION_LABEL_SIZE);
        myQuestionLabel.setVisible(false);
        myQuestionLabel.setBackground(Color.WHITE);
        myQuestionLabel.setFocusable(false);
        add(myQuestionLabel);

        // Short answer field
        myShortAnswerField = new JTextField(SHORT_ANSWER_COL);
        myShortAnswerField.setVisible(false);
        myShortAnswerField.addActionListener(theEvent -> mySubmitButton.setEnabled(true));
        add(myShortAnswerField);

        // Panel to hold answer and submit buttons
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        for (int i = 0; i < 4; i++) {
            final JToggleButton tb = new JToggleButton();
            tb.addActionListener(theEvent -> mySubmitButton.setEnabled(true));
            myButtonGroup.add(tb);
            myAnswerButtons.add(tb);
            panel.add(tb);
            tb.setVisible(false);
        }
        panel.add(new Container());
        panel.add(mySubmitButton);
        add(panel);
    }

    /**
     * Gets the selected answer from the option buttons.
     *
     * @return the text of the selected answer, or an empty string if no answer is selected
     */
    private String getSelectedAnswer() {
        String answerText = "";
        if (myQuestion.getType() != 3) {
            for (AbstractButton button : Collections.list(myButtonGroup.getElements())) {
                if (button.isSelected()) {
                    answerText = button.getText();
                }
            }
        } else {
            answerText = myShortAnswerField.getText();
        }
        return answerText;
    }

    /**
     * Checks the selected answer for accuracy, then moves the player location or
     * locks the corresponding door according to the player's position and direction.
     */
    private void checkAnswer() {
        final String correctAnswer = myQuestion.getCorrectAnswer();
        if (correctAnswer.equalsIgnoreCase(getSelectedAnswer())) {
            playCorrectSound();
            JOptionPane.showMessageDialog(this, CORRECT_MESSAGE);
            myCorrect++;
            if (myView.getMyUpButton()) {
                myMaze.advanceNorth(myView.getQPanel());
                myView.changePosition();
                myView.enableMoveButtons();
                myView.checkEnd();
                myView.updateButtonState();
                myView.setUp(false);
            }
            if (myView.getMyDown()) {
                myMaze.advanceSouth(myView.getQPanel());
                myView.changePosition();
                myView.enableMoveButtons();
                myView.checkEnd();
                myView.updateButtonState();
                myView.setDown(false);

            }
            if (myView.getMyLeft()) {
                myMaze.advanceWest(myView.getQPanel());
                myView.changePosition();
                myView.enableMoveButtons();
                myView.checkEnd();
                myView.updateButtonState();
                myView.setLeft(false);
            }
            if (myView.getMyRight()) {
                myMaze.advanceEast(myView.getQPanel());
                myView.changePosition();
                myView.enableMoveButtons();
                myView.checkEnd();
                myView.updateButtonState();
                myView.setRight(false);
            }
        } else {
            myIncorrect++;
            playIncorrectSound();
            JOptionPane.showMessageDialog(this, INCORRECT_MESSAGE + correctAnswer);
            myMaze.lockDoor(myDir);
            myView.enableMoveButtons();
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

    /**
     * Gets the number of correct answers.
     *
     * @return the number of correct answers
     */
    public int getCorrect() {
        return myCorrect;
    }

    /**
     * Gets the number of incorrect answers.
     *
     * @return the number of incorrect answers
     */
    public int getIncorrect() {
        return myIncorrect;
    }
}
