package Model;

import java.io.Serializable;

public class Question implements Serializable {
    private String myQuestionText;
    private String myOptionA;
    private String myOptionB;
    private String myOptionC;
    private String myOptionD;
    private String myAnswer;
    private int myType;

    public Question(final String theQuestionText, final String theA, final String theB, final String theC, final String theD,
                    final String theAnswer, final String theType) {
        myQuestionText = theQuestionText;
        myOptionA = theA;
        myOptionB = theB;
        myOptionC = theC;
        myOptionD = theD;
        myType = Integer.parseInt(theType);
        myAnswer = theAnswer;
    }
    public String getMyQuestionText() {
        return myQuestionText;
    }
    public String getOptionA() {
        return myOptionA;
    }
    public String getOptionB() {
        return myOptionB;
    }
    public String getOptionC() {
        return myOptionC;
    }
    public String getOptionD() {
        return myOptionD;
    }
    public String getCorrectAnswer() {
        return myAnswer;
    }
    public int getType() {
        return myType;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText=" + myQuestionText +
                ", optionA=" + myOptionA  +
                ", optionB=" + myOptionB  +
                ", optionC=" + myOptionC  +
                ", optionD=" + myOptionD  +
                ", type=" + myType  +
                ", correctAnswer=" + myAnswer  +
                '}';
    }
}

