package View;

import Controller.TriviaMaze;
import Model.Question;
import Model.QuestionFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;

import static Controller.PropertyChangeEnabledTriviaMazeControls.PROPERTY_PLAYER;

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

    private int myCheckAnswer;

    public QuestionPanel(final TriviaMaze theMaze, String theDif) {
        super();

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

    }

    public Question getMyQuestion() {
        return myQuestion;
    }
    public void setComponents() {
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
        add(myQuestionLabel);
        add(myButtonA);
        add(myButtonB);
        add(myButtonC);
        add(myButtonD);
        add(mySubmit);

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
                JOptionPane.showMessageDialog(this, "Incorrect, Door locked, the answer was: " + theAnswer);
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
