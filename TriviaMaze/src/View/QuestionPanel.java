package View;

import Controller.TriviaMaze;
import Model.Question;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static Controller.PropertyChangeEnabledTriviaMazeControls.PROPERTY_PLAYER;

/**
 * The QuestionPanel class represents a panel in the Trivia Maze game that displays questions
 * and handles user interactions such as answering questions and submitting answers.
 * It supports multiple choice, true/false, and short answer question types.
 * @author Matthew Uzunoe-Chin, Dustin Feldt, Elias Arriolas
 * @version Spring 2024
 */
public class QuestionPanel extends JPanel implements PropertyChangeListener, ChangeListener {

    /**
     * The Clip for playing an incorrect answer sound.
     */
    private Clip myIncorrectSound;

    /**
     * The Clip for playing a correct answer sound.
     */
    private Clip myCorrectSound;

    /**
     * The current question being displayed.
     */
    private Question myQuestion;

    /**
     * The JLabel displaying the question body.
     */
    private final JLabel myQuestionBody;

    /**
     * The ButtonGroup containing the answer buttons.
     */
    private final ButtonGroup myAnswerButtons;

    /**
     * The JButton for submitting an answer.
     */
    private final JButton mySubmit;

    /**
     * The JRadioButton for the first answer option.
     */
    private JRadioButton myButtonA;

    /**
     * The JRadioButton for the second answer option.
     */
    private JRadioButton myButtonB;

    /**
     * The JRadioButton for the third answer option.
     */
    private JRadioButton myButtonC;

    /**
     * The JRadioButton for the fourth answer option.
     */
    private JRadioButton myButtonD;

    /**
     * The JLabel displaying the question label.
     */
    private JLabel myQuestionLabel;

    /**
     * The direction in which the player is moving.
     */
    private int myDir;

    /**
     * The TriviaMaze instance that this panel interacts with.
     */
    private final TriviaMaze myMaze;

    /**
     * The count of correct answers given by the player.
     */
    private int myCorrect;

    /**
     * The count of incorrect answers given by the player.
     */
    private int myIncorrect;

    /**
     * The check answer flag indicating if an answer has been checked.
     */
    private int myCheckAnswer;

    /**
     * The JLabel for displaying long questions.
     */
    private JLabel myLong;

    /**
     * The JTextField for entering a short answer.
     */
    private JTextField myField;

    /**
     * The GUI view that this panel is a part of.
     */
    private final TriviaMazeGUI myView;

    /**
     * The JPanel containing the answer components.
     */
    private JPanel myAnswer;

    /**
     * Constructs a new QuestionPanel.
     *
     * @param theMaze the TriviaMaze object that this panel interacts with
     * @param theDif the difficulty level of the questions
     * @param theView the GUI view that this panel is a part of
     */
    public QuestionPanel(final TriviaMaze theMaze, String theDif, TriviaMazeGUI theView) {
        super();
        myView = theView;
        myMaze = theMaze;
        myQuestionBody = new JLabel();
        myQuestionBody.setVisible(true);
        myAnswerButtons = new ButtonGroup();
        mySubmit = new JButton("Submit");
        mySubmit.setVisible(true);
        mySubmit.setEnabled(false);
        try {
            myCorrectSound = AudioSystem.getClip();
            AudioInputStream correctStream = AudioSystem.getAudioInputStream(new File("correctbuzz.wav"));
            myCorrectSound.open(correctStream);

            myIncorrectSound = AudioSystem.getClip();
            AudioInputStream incorrectStream = AudioSystem.getAudioInputStream(new File("incorrectbuzz.wav"));
            myIncorrectSound.open(incorrectStream);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if(theDif.equals("Easy")) {
            setPreferredSize(new Dimension(200,200));
        } else if(theDif.equals("Medium")) {
            setPreferredSize(new Dimension(300,300));
        } else if(theDif.equals("Hard")) {
            setPreferredSize(new Dimension(500,500));
        } else {
            setPreferredSize(new Dimension(500,500));
        }
        setComponents();
        addListener();
    }
    /**
     * Sets the question to be displayed by this panel.
     *
     * @param theQuestion the question to display
     * @throws IllegalArgumentException if theQuestion is null
     */
    public void setQuestion(final Question theQuestion) {
        if(theQuestion == null) {
            throw new IllegalArgumentException("null");
        }
        myQuestion = theQuestion;
    }

    /**
     * Plays the sound associated with a correct answer.
     */
    private void playCorrectSound() {
        if (myCorrectSound.isRunning())
            myCorrectSound.stop();
        myCorrectSound.setFramePosition(0);
        myCorrectSound.start();
    }

    /**
     * Plays the sound associated with an incorrect answer.
     */
    private void playIncorrectSound() {
        if (myIncorrectSound.isRunning())
            myIncorrectSound.stop();
        myIncorrectSound.setFramePosition(0);
        myIncorrectSound.start();
    }

    /**
     * Sets the direction of the door associated with the current question.
     *
     * @param theDir the direction (0 = north, 1 = south, 2 = west, 3 = east)
     * @throws IllegalArgumentException if theDir is not in the range 0-3
     */
    public void setDir(final int theDir) {
        if(theDir < 0 || theDir > 3) {
            throw new IllegalArgumentException("not valid");
        }
        myDir = theDir;
    }

    /**
     * Updates the panel to display a new question.
     *
     * @param theQ the new question to display
     * @throws IllegalArgumentException if the question type is not recognized
     */
    public void updateQuestion(final Question theQ) {
        myCheckAnswer = 0;
        setQuestion(theQ);
        myQuestion  = theQ;
        String question = myQuestion.getQuestionText();
        if(theQ.getType() == 1) { // multiple choice
            setMultipleChoiceVisible(true);
            setMultipleChoiceEnable(true);
            mySubmit.setEnabled(false);
            if (question.length() > 80) {
                myLong.setVisible(true);
                myQuestionLabel.setText("Question: " + question.substring(0, 80));
                myLong.setText(question.substring(80));
                myButtonA.setText(myQuestion.getOptionA());
                myButtonB.setText(myQuestion.getOptionB());
                myButtonC.setText(myQuestion.getOptionC());
                myButtonD.setText(myQuestion.getOptionD());

            } else {
                myQuestionLabel.setText("Question: " + question);
                myButtonA.setText(myQuestion.getOptionA());
                myButtonB.setText(myQuestion.getOptionB());
                myButtonC.setText(myQuestion.getOptionC());
                myButtonD.setText(myQuestion.getOptionD());

            }
        } else if (theQ.getType() == 2) { //true false
            setMultipleChoiceVisible(true);
            setMultipleChoiceEnable(true);
            mySubmit.setEnabled(false);
            myButtonC.setEnabled(false);
            myButtonD.setEnabled(false);
            myButtonC.setVisible(false);
            myButtonD.setVisible(false);
            if (question.length() > 80) {
                myLong.setVisible(true);
                myQuestionLabel.setText("Question: " + question.substring(0, 80));
                myLong.setText(question.substring(80));
                myButtonA.setText(myQuestion.getOptionA());
                myButtonB.setText(myQuestion.getOptionB());

            } else {
                myQuestionLabel.setText("Question: " + question);
                myButtonA.setText(myQuestion.getOptionA());
                myButtonB.setText(myQuestion.getOptionB());

            }
        } else if (theQ.getType() == 3) { // short answer
            setMultipleChoiceEnable(false);
            setMultipleChoiceVisible(false);
            myQuestionBody.setVisible(true);
            myQuestionLabel.setVisible(true);
            myField.setVisible(true);
            myField.setEditable(true);
            mySubmit.setVisible(true);
            myAnswer.setVisible(true);
            if (question.length() > 60) {
                myLong.setVisible(true);
                myQuestionLabel.setText("Question: " + question.substring(0, 60));
                myLong.setText(question.substring(60));

            } else {
                myQuestionLabel.setText("Question: " + question);
            }

        } else {
            throw new IllegalArgumentException("no valid question types found!");
        }
    }

    /**
     * Sets the visibility of multiple choice components.
     *
     * @param theB true to make components visible, false to hide them
     */
    private void setMultipleChoiceVisible(final boolean theB) {
        myQuestionBody.setVisible(theB);
        myQuestionLabel.setVisible(theB);
        myButtonA.setVisible(theB);
        myButtonB.setVisible(theB);
        myButtonC.setVisible(theB);
        myButtonD.setVisible(theB);
        mySubmit.setVisible(theB);
    }

    /**
     * Sets the enabled state of multiple choice components.
     *
     * @param theB true to enable components, false to disable them
     */
    private void setMultipleChoiceEnable(final boolean theB) {
        myQuestionBody.setVisible(theB);
        myQuestionLabel.setVisible(theB);
        myButtonA.setEnabled(theB);
        myButtonB.setEnabled(theB);
        myButtonC.setEnabled(theB);
        myButtonD.setEnabled(theB);
        mySubmit.setEnabled(theB);
    }

    /**
     * Initializes the components of the panel.
     */
    private void setComponents() {
        myAnswer = new JPanel();
        myQuestionLabel = new JLabel();
        myLong = new JLabel();
        myField = new JTextField(20);
        myButtonA = new JRadioButton();
        myAnswerButtons.add(myButtonA);

        myButtonB = new JRadioButton();
        myAnswerButtons.add(myButtonB);

        myButtonC = new JRadioButton();
        myAnswerButtons.add(myButtonC);

        myButtonD = new JRadioButton();
        myAnswerButtons.add(myButtonD);
        myField.setEditable(false);
        myField.setVisible(false);
        myAnswer.add(myField);
        myAnswer.setVisible(false);
        setMultipleChoiceVisible(false);
        setMultipleChoiceEnable(false);
        add(myQuestionLabel);
        add(myLong);
        add(myAnswer);
        add(myButtonA);
        add(myButtonB);
        add(myButtonC);
        add(myButtonD);
        add(mySubmit);
    }

    /**
     * Responds to property change events.
     *
     * @param theEvent the event that occurred
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        switch (theEvent.getPropertyName()) {
            case PROPERTY_PLAYER:

        }
    }

    /**
     * Responds to state change events.
     *
     * @param theEvent the event that occurred
     */
    @Override
    public void stateChanged(final ChangeEvent theEvent) {

    }

    /**
     * Gets the selected answer from the radio buttons.
     *
     * @return the text of the selected answer, or an empty string if no answer is selected
     */
    private String getSelectedAnswer() {
        for (AbstractButton button : Collections.list(myAnswerButtons.getElements())) {
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "";
    }

    /**
     * Adds action listeners to the submit button and answer buttons.
     */
    public void addListener() {
        mySubmit.addActionListener(theEvent -> {
            if(myQuestion.getType() == 1 || myQuestion.getType() == 2) {
                myCheckAnswer = 1;
                myLong.setText("");
                mySubmit.setEnabled(false);
                myButtonA.setEnabled(false);
                myButtonB.setEnabled(false);
                myButtonC.setEnabled(false);
                myButtonD.setEnabled(false);
                String theAnswer = myQuestion.getCorrectAnswer();
                String selectedAnswer = getSelectedAnswer();
                if (selectedAnswer.equals(theAnswer)) {
                    playCorrectSound();
                    JOptionPane.showMessageDialog(this, "Correct!");
                    myCorrect++;
                    if (myView.getMyUp()) {
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
                    setMultipleChoiceVisible(false);

                } else {
                    myIncorrect++;
                    playIncorrectSound();
                    JOptionPane.showMessageDialog(this, "Incorrect, Door locked, the answer was: " + theAnswer);
                    myMaze.lockDoor(myDir);
                    myView.setUpBut();
                    myView.updateButtonState();
                    myView.playerLost();
                    myView.checkExitEnd();
                    if (myView.getMyUp()) {
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
                    setMultipleChoiceVisible(false);
                }
                myAnswerButtons.clearSelection();
            } else {
                myCheckAnswer = 1;
                myLong.setText("");
                mySubmit.setEnabled(false);
                String theAnswer = myQuestion.getCorrectAnswer();
                String selectedAnswer = myField.getText();
                if (selectedAnswer.toLowerCase().equals(theAnswer.toLowerCase())) {
                    playCorrectSound();
                    JOptionPane.showMessageDialog(this, "Correct!");
                    myCorrect++;
                    if (myView.getMyUp()) {
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
                    myField.setVisible(false);
                    myQuestionBody.setVisible(false);
                    myQuestionLabel.setVisible(false);

                } else {
                    myIncorrect++;
                    playIncorrectSound();
                    JOptionPane.showMessageDialog(this, "Incorrect, Door locked, the answer was: " + theAnswer);
                    myMaze.lockDoor(myDir);
                    myView.setUpBut();
                    myView.updateButtonState();
                    myView.playerLost();
                    myView.checkExitEnd();
                    if (myView.getMyUp()) {
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
                myQuestionBody.setVisible(false);
                myQuestionLabel.setVisible(false);
                myField.setVisible(false);
                myAnswer.setVisible(false);
                mySubmit.setVisible(false);
                myField.setText("");

            }


        });
        myButtonA.addActionListener(theEvent -> {
            if(myCheckAnswer == 0) {
                mySubmit.setEnabled(true);

            }
        });
        myButtonB.addActionListener(theEvent -> {
            if(myCheckAnswer == 0) {
                mySubmit.setEnabled(true);

            }
        });
        myButtonC.addActionListener(theEvent -> {
            if (myCheckAnswer == 0) {

                mySubmit.setEnabled(true);

            }
        });
        myField.addActionListener(theEvent -> {
            if (myCheckAnswer == 0) {

                mySubmit.setEnabled(true);

            }
        });
        myButtonD.addActionListener(theEvent -> {
            if (myCheckAnswer == 0) {
                mySubmit.setEnabled(true);

            }
        });
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
