package View;

import Model.Door;
import Model.Question;

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

    private Door myDoor;

    public QuestionPanel() {
        super();
        myQuestionBody = new JLabel();
        myQuestionBody.setVisible(true);
        myAnswerButtons = new ButtonGroup();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setComponents();
    }

    private void setComponents() {

    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {

    }

    @Override
    public void stateChanged(final ChangeEvent theEvent) {

    }
}
