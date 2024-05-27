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

public class QuestionPanel extends JPanel implements ChangeListener {

    private static final String SUBMIT_TEXT = "Submit";
    private static final String CORRECT_SOUND = "correctbuzz.wav";
    private static final String INCORRECT_SOUND = "incorrectbuzz.wav";
    private static final Dimension PANEL_SIZE = new Dimension(400, 400);
    private Clip myIncorrectSound;
    private Clip myCorrectSound;

    private Question myQuestion;

    private final ButtonGroup myAnswerButtons;
    private final JButton mySubmitButton;

    private JToggleButton myButtonA;
    private JToggleButton myButtonB;
    private JToggleButton myButtonC;
    private JToggleButton myButtonD;

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
        myAnswerButtons = new ButtonGroup();
        mySubmitButton = new JButton(SUBMIT_TEXT);
        mySubmitButton.setVisible(true);
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
        addListener();
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
            throw new IllegalArgumentException("not valid");
        }
        myDir = theDir;
    }
    public void updateQuestion(final Question theQ) {
        myCheckAnswer = 0;
       setQuestion(theQ);
       myQuestion  = theQ;
       String question = myQuestion.getQuestionText();
       if(theQ.getType() == 1) { // multiple choice
           setMultipleChoiceVisible(true);
           setMultipleChoiceEnable(true);
           mySubmitButton.setEnabled(false);
           myQuestionLabel.setText("Question: " + question);
           myButtonA.setText(myQuestion.getOptionA());
           myButtonB.setText(myQuestion.getOptionB());
           myButtonC.setText(myQuestion.getOptionC());
           myButtonD.setText(myQuestion.getOptionD());
       } else if (theQ.getType() == 2) { //true false
           setMultipleChoiceVisible(true);
           setMultipleChoiceEnable(true);
           mySubmitButton.setEnabled(false);
           myButtonC.setEnabled(false);
           myButtonD.setEnabled(false);
           myButtonC.setVisible(false);
           myButtonD.setVisible(false);
           myQuestionLabel.setText("Question: " + question);
           myButtonA.setText(myQuestion.getOptionA());
           myButtonB.setText(myQuestion.getOptionB());
       } else if (theQ.getType() == 3) { // short answer
           setMultipleChoiceEnable(false);
           setMultipleChoiceVisible(false);
           myQuestionLabel.setVisible(true);
           myShortAnswerField.setVisible(true);
           myShortAnswerField.setEditable(true);
           mySubmitButton.setVisible(true);
           myQuestionLabel.setText("Question: " + question);
       } else {
           throw new IllegalArgumentException("no valid question types found!");
       }
    }
    private void setMultipleChoiceVisible(final boolean theB) {
        myQuestionLabel.setVisible(theB);
        myButtonA.setVisible(theB);
        myButtonB.setVisible(theB);
        myButtonC.setVisible(theB);
        myButtonD.setVisible(theB);
        mySubmitButton.setVisible(theB);
    }
    private void setMultipleChoiceEnable(final boolean theB) {
        myQuestionLabel.setVisible(theB);
        myButtonA.setEnabled(theB);
        myButtonB.setEnabled(theB);
        myButtonC.setEnabled(theB);
        myButtonD.setEnabled(theB);
        mySubmitButton.setEnabled(theB);
    }
    private void setComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,1));

        myQuestionLabel = new JTextArea();
//        myQuestionLabel.setBackground(this.getBackground());
        myQuestionLabel.setLineWrap(true);
        myQuestionLabel.setWrapStyleWord(true);
        myQuestionLabel.setEditable(false);
        myQuestionLabel.setFont(new Font("Monospace", Font.PLAIN, 16));
        myQuestionLabel.setPreferredSize(new Dimension(400, 200));

        myShortAnswerField = new JTextField(20);
//        myButtonA = new JRadioButton();
        myButtonA = new JToggleButton();

        myAnswerButtons.add(myButtonA);


        myButtonB = new JToggleButton();
        myButtonB.setVerticalAlignment(SwingConstants.CENTER);
        myAnswerButtons.add(myButtonB);

        myButtonC = new JToggleButton();
        myAnswerButtons.add(myButtonC);

        myButtonD = new JToggleButton();
        myAnswerButtons.add(myButtonD);
//        myShortAnswerField.setEditable(false);
        myShortAnswerField.setVisible(false);
        setMultipleChoiceVisible(false);
        setMultipleChoiceEnable(false);
        add(myQuestionLabel);
        panel.add(myButtonA);
        panel.add(myButtonB);
        panel.add(myButtonC);
        panel.add(myButtonD);
        panel.add(new Container());
        add(myShortAnswerField);
        panel.add(mySubmitButton);
        add(panel);
    }


    @Override
    public void stateChanged(final ChangeEvent theEvent) {
    }

    public String getSelectedAnswer() {
        for (AbstractButton button : Collections.list(myAnswerButtons.getElements())) {
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "";
    }

    private void addListener() {
        mySubmitButton.addActionListener(theEvent -> {
            if(myQuestion.getType() == 1 || myQuestion.getType() == 2) {
                mySubmitButton.setEnabled(false);
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
                mySubmitButton.setEnabled(false);
                String theAnswer = myQuestion.getCorrectAnswer();
                String selectedAnswer = myShortAnswerField.getText();
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
                    myShortAnswerField.setVisible(false);
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
                myQuestionLabel.setVisible(false);
                myShortAnswerField.setVisible(false);
                mySubmitButton.setVisible(false);
                myShortAnswerField.setText("");

            }


        });
        myButtonA.addActionListener(theEvent -> {
            if(myCheckAnswer == 0) {
                mySubmitButton.setEnabled(true);

            }
        });
        myButtonB.addActionListener(theEvent -> {
            if(myCheckAnswer == 0) {
                mySubmitButton.setEnabled(true);

            }
        });
        myButtonC.addActionListener(theEvent -> {
            if (myCheckAnswer == 0) {

            mySubmitButton.setEnabled(true);

            }
        });
        myShortAnswerField.addActionListener(theEvent -> {
            if (myCheckAnswer == 0) {

                mySubmitButton.setEnabled(true);

            }
        });
        myButtonD.addActionListener(theEvent -> {
            if (myCheckAnswer == 0) {
                mySubmitButton.setEnabled(true);

            }
        });
      }


      public int getCorrect() {
        return myCorrect;
      }

      public int getIncorrect() {
        return myIncorrect;
      }
}
