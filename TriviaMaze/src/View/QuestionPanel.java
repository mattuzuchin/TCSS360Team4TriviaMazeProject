package View;

import Controller.TriviaMaze;
import Model.Maze;
import Model.Question;
import Model.QuestionFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;

import static Controller.PropertyChangeEnabledTriviaMazeControls.*;

public class QuestionPanel extends JPanel implements PropertyChangeListener, ChangeListener {

    private static final String ANSWER_A = "A) ";
    private static final String ANSWER_B = "B) ";
    private static final String ANSWER_C = "C) ";
    private static final String ANSWER_D = "D) ";

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

    private int myCheckAnswer;

    public QuestionPanel(final TriviaMaze theMaze) {
        super();
        myMaze = theMaze;
        myQuestionBody = new JLabel();
        myQuestionBody.setVisible(true);
        myAnswerButtons = new ButtonGroup();
        mySubmit = new JButton("Submit");
        mySubmit.setVisible(true);
        mySubmit.setEnabled(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
    public void updateQuestion() {
        myQuestion  = QuestionFactory.getInstance().getQuestion();
        myQuestionLabel = new JLabel("Question: " + myQuestion.getQuestionText());
        myButtonA = new JRadioButton(myQuestion.getOptionA());
        myAnswerButtons.add(myButtonA);

        myButtonB = new JRadioButton(myQuestion.getOptionB());
        myAnswerButtons.add(myButtonB);

        myButtonC = new JRadioButton(myQuestion.getOptionC());
        myAnswerButtons.add(myButtonC);

        myButtonD = new JRadioButton(myQuestion.getOptionD());
        myAnswerButtons.add(myButtonD);
        myQuestionBody.setVisible(true);
        myButtonA.setVisible(true);
        myButtonB.setVisible(true);
        myButtonC.setVisible(true);
        myButtonD.setVisible(true);
    }

    public void setComponents() {
        myQuestion  = QuestionFactory.getInstance().getQuestion();
        myQuestionLabel = new JLabel();
        add(myQuestionLabel);
        add(myQuestionBody);
        myButtonA = new JRadioButton();
        myAnswerButtons.add(myButtonA);

        myButtonB = new JRadioButton();
        myAnswerButtons.add(myButtonB);

        myButtonC = new JRadioButton();
        myAnswerButtons.add(myButtonC);

        myButtonD = new JRadioButton();
        myAnswerButtons.add(myButtonD);

        add(myButtonA);
        add(myButtonB);
        add(myButtonC);
        add(myButtonD);
        add(mySubmit);
        myQuestionBody.setVisible(false);
        myButtonA.setVisible(false);
        myButtonB.setVisible(false);
        myButtonC.setVisible(false);
        myButtonD.setVisible(false);
    }


    public ButtonGroup getGroup() {
        return myAnswerButtons;
    }
    public JButton getMySubmit() {
        return mySubmit;
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
            int choice = QuestionFactory.getInstance().getChoice();
            String theAnswer = myQuestion.getCorrectAnswer();
            String selectedAnswer = getSelectedAnswer();
            if (selectedAnswer.equals(theAnswer)) {
                JOptionPane.showMessageDialog(this, "Correct!");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect, the answer was: " + theAnswer);
                myMaze.lockDoor(myDir);
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
