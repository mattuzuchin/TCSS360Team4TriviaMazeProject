package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Matthew Uzunoe-Chin, Elias Arriola, Dustin Feldt
 * @version Spring 2024
 * Implementation of a single question.
 */
public class Question implements Serializable {

    /**
     * Field represents the question as a string.
     */
    private final String myQuestionText;

    /**
     * Field represents answer to question.
     */
    private final String myAnswer;

    /**
     * A list of the question's answer options.
     */
    private final ArrayList<String> myOptions;

    /**
     * Field represents question type as an integer,
     * with 1 being multiple choice,
     * 2 being true/false,
     * and 3 being short answer.
     */
    private final int myType;

    /**
     * Constructor.
     *
     * @param theQuestionText The body of the question.
     * @param theA The first answer option.
     * @param theB The second answer option.
     * @param theC The third answer option.
     * @param theD The fourth answer option.
     * @param theAnswer The correct answer.
     * @param theType The question type.
     */
    public Question(final String theQuestionText, final String theA, final String theB, final String theC,
                    final String theD, final String theAnswer, final String theType) {
        myQuestionText = theQuestionText;
        myOptions = new ArrayList<>();
        myOptions.add(theA);
        myOptions.add(theB);
        myOptions.add(theC);
        myOptions.add(theD);
        myType = Integer.parseInt(theType);
        myAnswer = theAnswer;
    }

    /**
     *
     * @return question text.
     */
    public String getMyQuestionText() {
        return myQuestionText;
    }

    /**
     *
     * @return the list of options.
     */
    public ArrayList<String> getOptions() {
        return myOptions;
    }

    /**
     *
     * @return answer to question.
     */
    public String getCorrectAnswer() {
        return myAnswer;
    }

    /**
     *
     * @return question type.
     */
    public int getType() {
        return myType;
    }

    /**
     *
     * @return String representation of question.
     */
    @Override
    public String toString() {
        return "Question{\n" + "questionText = " + myQuestionText
                + ", optionA = " + myOptions.get(0)
                + ", optionB = " + myOptions.get(1)
                + ", optionC = " + myOptions.get(2)
                + ", optionD = " + myOptions.get(3)
                + ", type = " + myType
                + ", correctAnswer = " + myAnswer + "}";
    }
}

