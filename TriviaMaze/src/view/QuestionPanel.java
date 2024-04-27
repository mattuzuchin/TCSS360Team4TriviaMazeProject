package view;

import model.Question;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class QuestionPanel extends JPanel implements PropertyChangeListener, ChangeListener {

    private static final String ANSWER_A = "A) ";
    private static final String ANSWER_B = "B) ";
    private static final String ANSWER_C = "C) ";
    private static final String ANSWER_D = "D) ";

    private JLabel myQuestionBody;

    private ButtonGroup myAnswerButtons;

    private Question myQuestion;

    public QuestionPanel() {
        super();
        myQuestionBody = new JLabel();
        myAnswerButtons = new ButtonGroup();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setComponents();
    }

    private void setComponents() {

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
}
