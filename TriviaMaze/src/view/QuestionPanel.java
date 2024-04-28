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

public class QuestionPanel extends JPanel implements PropertyChangeListener, ChangeListener {

    private static final String ANSWER_A = "A) ";
    private static final String ANSWER_B = "B) ";
    private static final String ANSWER_C = "C) ";
    private static final String ANSWER_D = "D) ";

    private JLabel myQuestionBody;

    private Question myQuestion;

    public QuestionPanel() {
        super();
        myQuestionBody = new JLabel();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(myQuestionBody);
        createButtons();
    }

    private void createButtons() {
        final char[] answers = {'A', 'B', 'C', 'D'};

        for (char c : answers) {
            final JButton button = new JButton(new AnswerAction(c));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(button);
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

    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param theEvent a ChangeEvent object
     */
    @Override
    public void stateChanged(final ChangeEvent theEvent) {

    }

    private final class AnswerAction extends AbstractAction{

        private final char answerChar;
        AnswerAction(final char theAnswer) {
            super(theAnswer + ") ");
            answerChar = theAnswer;
            putValue(Action.SHORT_DESCRIPTION, "Answer " + answerChar);
        }

        /**
         * Invoked when an action occurs.
         *
         * @param theEvent the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent theEvent) {
            firePropertyChange("answer chosen", null, answerChar);
        }
    }
}
