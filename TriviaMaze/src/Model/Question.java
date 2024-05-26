
package Model;

import java.io.Serializable;

/**
 * @author Matthew Uzunoe-Chin, Elias Arriola, Dustin Feldt
 * @version Spring 2024
 * Implementation of a single question.
 */
public class Question implements Serializable {
    /**
     * Field represents the question as a string.
     */
    private final String questionText;
    /**
     * Field represents 1st option of question.
     */
    private final String myOptionA;
    /**
     * Field represents 2nd option of question.
     */
    private final String myOptionB;
    /**
     * Field represents 3rd option of question.
     */
    private final String myOptionC;
    /**
     * Field represents fourth option of question.
     */
    private final String myOptionD;
    /**
     * Field represents answer to question.
     */
    private final String myAnswer;
    /**
     * Field represents question type as an integer.
     */
    private final int myType;

    /**
     * Question Constructor.
     * @param theQuestionText text for question
     * @param theA option a
     * @param theB option b
     * @param theC option c
     * @param theD option d
     * @param theAnswer the answer
     * @param theType type of question
     */
    public Question(final String theQuestionText, final String theA, final String theB, final String theC, final String theD,
                    final String theAnswer, final String theType) {
        questionText = theQuestionText;
        myOptionA = theA;
        myOptionB = theB;
        myOptionC = theC;
        myOptionD = theD;
        myType = Integer.parseInt(theType);
        myAnswer = theAnswer;
    }

    /**
     *
     * @return question text.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     *
     * @return 1st option of question.
     */
    public String getOptionA() {
        return myOptionA;
    }

    /**
     *
     * @return 2nd option of question
     */
    public String getOptionB() {
        return myOptionB;
    }

    /**
     *
     * @return 3rd option of question.
     */
    public String getOptionC() {
        return myOptionC;
    }

    /**
     *
     * @return fourth option of question.
     */
    public String getOptionD() {
        return myOptionD;
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
        return "Question{" +
                "questionText=" + questionText  +
                ", optionA=" + myOptionA  +
                ", optionB=" + myOptionB  +
                ", optionC=" + myOptionC  +
                ", optionD=" + myOptionD  +
                ", type=" + myType  +
                ", correctAnswer=" + myAnswer  +
                '}';
    }
}
