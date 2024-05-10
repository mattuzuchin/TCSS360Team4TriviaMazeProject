package view;

import model.Question;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import static controller.PropertyChangeEnabledTriviaMazeControls.PROPERTY_ANSWER;
import static controller.PropertyChangeEnabledTriviaMazeControls.PROPERTY_QUESTION;

public class QuestionPanel extends JPanel implements PropertyChangeListener, ChangeListener {

    private static final String[] ANSWER_LABELS = {"A) ", "B) ", "C) ", "D) "};
    private static final String CORRECT_MESSAGE = "Correct!";
    private static final String WRONG_MESSAGE = "Incorrect!";

    private final JLabel myQuestionBody;
    private final ArrayList<JButton> myAnswerButtons;

    private Question myQuestion;

    public QuestionPanel() {
        super();
        myQuestionBody = new JLabel();
        myAnswerButtons = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(myQuestionBody);
        createButtons();
        setVisible(false);
    }

    private void createButtons() {
        for (int i = 0; i < ANSWER_LABELS.length; i++) {
            final JButton button = new JButton();
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(250, 100));
            final int chosenIndex = i;
            button.addActionListener(theEvent -> checkAnswer(chosenIndex));
            myAnswerButtons.add(button);
            add(button);
        }
    }

    private void displayQuestion() {
        myQuestionBody.setText(myQuestionBody.getText());
        final ArrayList<String> answers = myQuestion.getOptions();

        for (int i = 0; i < answers.size(); i++) {
            final JButton button = myAnswerButtons.get(i);
            button.setText(ANSWER_LABELS[i] + answers.get(i));
            button.setMnemonic(ANSWER_LABELS[i].charAt(0));
        }
//        repaint();
        setVisible(true);
    }

    private void checkAnswer(final int theOptionIndex) {
        final boolean isCorrect = myQuestion.getOptions().get(theOptionIndex).equals(myQuestion.getCorrectAnswer());
        setAnswerStatus(isCorrect);
        firePropertyChange(PROPERTY_ANSWER, null, isCorrect);
    }

    private void setAnswerStatus(final boolean theCorrectness) {
        if (theCorrectness) {
            for (JButton button : myAnswerButtons) {
                if (button.isSelected()) {
                    button.setForeground(Color.GREEN);
                }
                else {
                    button.setFont(button.getFont().deriveFont(Font.ITALIC));
                    button.setForeground(Color.DARK_GRAY);
                }
            }
            JOptionPane.showMessageDialog(null, CORRECT_MESSAGE, null, JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            for (JButton button : myAnswerButtons) {
                if (button.isSelected()) {
                    button.setForeground(Color.RED);
                }
                else {
                    // Highlight correct answer in GREEN
                    button.setFont(button.getFont().deriveFont(Font.ITALIC));
                    button.setForeground(Color.DARK_GRAY);
                }
            }
            JOptionPane.showMessageDialog(null, WRONG_MESSAGE, null, JOptionPane.INFORMATION_MESSAGE);

        }
    }


    /**
     * This method gets called when a bound property is changed.
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals(PROPERTY_QUESTION)) {
            myQuestion = (Question) theEvent.getNewValue();
            displayQuestion();
        }
    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param theEvent a ChangeEvent object
     */
    @Override
    public void stateChanged(final ChangeEvent theEvent) {
        // Enable debug mode features here
    }
}
