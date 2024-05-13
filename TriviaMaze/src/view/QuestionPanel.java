package view;

import controller.TriviaMaze;
import model.Question;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;

import static controller.PropertyChangeEnabledTriviaMazeControls.PROPERTY_PLAYER;

public class QuestionPanel extends JPanel implements PropertyChangeListener, ChangeListener {



    private Question myQuestion;
    private JLabel myQuestionBody;

    private ButtonGroup myAnswerButtons;
    private JButton mySubmit;

    private JRadioButton myButtonA;
    private JRadioButton myButtonB;
    private JRadioButton myButtonC;
    private JRadioButton myButtonD;

    private JLabel myQuestionLabel;
    private int myDir;
    private TriviaMaze myMaze;
    private int myCorrect;
    private int myIncorrect;
    private int myCheckAnswer;

    private TriviaMazeGUI myView;

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
    public void setQuestion(Question theQuestion) {
        if(theQuestion == null) {
            throw new IllegalArgumentException("null");
        }
        myQuestion = theQuestion;

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
       myQuestionLabel.setText("Question: " + myQuestion.getQuestionText());
       myButtonA.setText(myQuestion.getOptionA());
        myButtonB.setText(myQuestion.getOptionB());
        myButtonC.setText(myQuestion.getOptionC());
        myButtonD.setText(myQuestion.getOptionD());
        myButtonA.setEnabled(true);
        myButtonB.setEnabled(true);
        myButtonC.setEnabled(true);
        myButtonD.setEnabled(true);
    }

    public void setComponents() {

        myQuestionLabel = new JLabel();
        myButtonA = new JRadioButton();
        myAnswerButtons.add(myButtonA);

        myButtonB = new JRadioButton();
        myAnswerButtons.add(myButtonB);

        myButtonC = new JRadioButton();
        myAnswerButtons.add(myButtonC);

        myButtonD = new JRadioButton();
        myAnswerButtons.add(myButtonD);
        myQuestionBody.setVisible(true);
        myButtonA.setVisible(true);
        myButtonB.setVisible(true);
        myButtonC.setVisible(true);
        myButtonD.setVisible(true);
        myButtonA.setEnabled(false);
        myButtonB.setEnabled(false);
        myButtonC.setEnabled(false);
        myButtonD.setEnabled(false);
        add(myQuestionLabel);
        add(myButtonA);
        add(myButtonB);
        add(myButtonC);
        add(myButtonD);
        add(mySubmit);

    }


    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        switch (theEvent.getPropertyName()) {
            case PROPERTY_PLAYER:
        }
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

    public void addListener() {
        mySubmit.addActionListener(theEvent -> {
            myCheckAnswer = 1;
            mySubmit.setEnabled(false);
            myButtonA.setEnabled(false);
            myButtonB.setEnabled(false);
            myButtonC.setEnabled(false);
            myButtonD.setEnabled(false);
            String theAnswer = myQuestion.getCorrectAnswer();
            String selectedAnswer = getSelectedAnswer();
            if (selectedAnswer.equals(theAnswer)) {
                JOptionPane.showMessageDialog(this, "Correct!");
                myCorrect++;
                if(myView.getMyUp()) {
                    myMaze.advanceNorth(myView.getQPanel());
                    myView.changePosition();
                    myView.setUpBut();
                    myView.checkEnd();
                    myView.updateButtonState();
                    myView.setUp(false);
                }
                if(myView.getMyDown()) {
                    myMaze.advanceSouth(myView.getQPanel());
                    myView.changePosition();
                    myView.setUpBut();
                    myView.checkEnd();
                    myView.updateButtonState();
                    myView.setDown(false);

                }
                if(myView.getMyLeft()) {
                    myMaze.advanceWest(myView.getQPanel());
                    myView.changePosition();
                    myView.setUpBut();
                    myView.checkEnd();
                    myView.updateButtonState();
                    myView.setLeft(false);
                }
                if(myView.getMyRight()) {
                    myMaze.advanceEast(myView.getQPanel());
                    myView.changePosition();
                    myView.setUpBut();
                    myView.checkEnd();
                    myView.updateButtonState();
                    myView.setRight(false);
                }

            } else {
                myIncorrect++;
                JOptionPane.showMessageDialog(this, "Incorrect, Door locked, the answer was: " + theAnswer);
                myMaze.lockDoor(myDir);
                myView.setUpBut();
                myView.updateButtonState();
                myView.playerLost();
                if(myView.getMyUp()) {
                    myView.setDisableUp();
                    myView.setUp(false);
                    myView.changePosition();
                }
                if(myView.getMyDown()) {
                    myView.setDisableDown();
                    myView.setDown(false);
                    myView.changePosition();

                }
                if(myView.getMyRight()) {
                    myView.setDisableRight();
                    myView.setRight(false);
                    myView.changePosition();

                }
                if(myView.getMyLeft()) {
                    myView.setDisableLeft();
                    myView.setLeft(false);
                    myView.changePosition();

                }
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
        myButtonD.addActionListener(theEvent -> {
            if (myCheckAnswer == 0) {
                mySubmit.setEnabled(true);

            }
        });
      }
}
