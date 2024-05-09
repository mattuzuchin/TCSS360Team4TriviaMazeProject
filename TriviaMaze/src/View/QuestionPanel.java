package View;

import Model.Maze;
import Model.Question;

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


    private JLabel myQuestionBody;

    private ButtonGroup myAnswerButtons;
    private JButton mySubmit;

    private JRadioButton myButtonA;
    private JRadioButton myButtonB;
    private JRadioButton myButtonC;
    private JRadioButton myButtonD;
    private Maze myMaze;

    private int myCheckAnswer;

    public QuestionPanel() {
        super();
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

    private void setComponents() {
        // Create the question label
        int q  = Question.getInstance().generateQuestion();
        JLabel questionLabel = new JLabel("Question: " + Question.getInstance().getQ(q));
        add(questionLabel);
        add(myQuestionBody);
        myButtonA = new JRadioButton(Question.getInstance().getA(q));
        myAnswerButtons.add(myButtonA);

        myButtonB = new JRadioButton(Question.getInstance().getB(q));
        myAnswerButtons.add(myButtonB);

        myButtonC = new JRadioButton(Question.getInstance().getC(q));
        myAnswerButtons.add(myButtonC);

        myButtonD = new JRadioButton(Question.getInstance().getD(q));
        myAnswerButtons.add(myButtonD);

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
            int choice = Question.getInstance().getChoice();
            String theAnswer = Question.getInstance().getAns(choice);
            String selectedAnswer = getSelectedAnswer();
            if (selectedAnswer.equals(theAnswer)) {
                JOptionPane.showMessageDialog(this, "Correct!");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect, the answer was: " + theAnswer);
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
