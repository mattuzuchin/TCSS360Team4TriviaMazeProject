package Model;

import java.io.Serializable;

public class Question implements Serializable {
    private String questionText;
    private String myOptionA;
    private String myOptionB;
    private String myOptionC;
    private String myOptionD;
    private String myAnswer;

    public Question(final String theQuestionText, final String theA, final String theB, final String theC, final String theD, final String theAnswer) {
        questionText = theQuestionText;
        myOptionA = theA;
        myOptionB = theB;
        myOptionC = theC;
        myOptionD = theD;
        myAnswer = theAnswer;
    }
    public String getQuestionText() {
        return questionText;
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

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", optionA='" + myOptionA + '\'' +
                ", optionB='" + myOptionB + '\'' +
                ", optionC='" + myOptionC + '\'' +
                ", optionD='" + myOptionD + '\'' +
                ", correctAnswer='" + myAnswer + '\'' +
                '}';
    }
}

