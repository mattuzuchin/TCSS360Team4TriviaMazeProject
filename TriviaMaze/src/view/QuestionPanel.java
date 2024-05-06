package view;

import model.Question;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class QuestionPanel extends JPanel implements PropertyChangeListener, ChangeListener {

    private static final String[] ANSWER_LABELS = {"A) ", "B) ", "C) ", "D) "};

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
            final JButton button = new JButton(new AnswerAction(i));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(250, 100));
            myAnswerButtons.add(button);
            add(button);
        }
    }

    private void displayQuestion() {
        myQuestionBody.setText(myQuestion.getQuestionBody());
        final List<String> answers = myQuestion.getAnswers();

        for (int i = 0; i < answers.size(); i++) {
            myAnswerButtons.get(i).setText(ANSWER_LABELS[i] + answers.get(i));
        }
        repaint();
        setVisible(true);
    }

    private void setAnswerStatus(final boolean theCorrectness) {
        // Where to display notification?
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
        if (theEvent.getPropertyName().equals("question")) {
            myQuestion = (Question) theEvent.getNewValue();
            displayQuestion();
        }
        if (theEvent.getPropertyName().equals("answer")) {

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

    private static final class AnswerAction extends AbstractAction{

        private final int myAnswerInt;
        private final char myAnswerChar;
        AnswerAction(final int theAnswerIndex) {
            super();
            myAnswerInt = theAnswerIndex;
            myAnswerChar = ANSWER_LABELS[myAnswerInt].charAt(0);
            putValue(Action.SHORT_DESCRIPTION, "Answer " + myAnswerChar);
            putValue(Action.MNEMONIC_KEY, myAnswerChar);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(myAnswerChar));
        }

        /**
         * Invoked when an action occurs.
         *
         * @param theEvent the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent theEvent) {
            firePropertyChange("answer chosen", null, myAnswerInt);
        }
    }
}
