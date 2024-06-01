package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private final String questionText;
    private final String myOptionA;
    private final String myOptionB;
    private final String myOptionC;
    private final String myOptionD;
    private final String myAnswer;
    private final ArrayList<String> myOptions;
    private final int myType;

    public Question(final String theQuestionText, final String theA, final String theB, final String theC, final String theD,
                    final String theAnswer, final String theType) {
        questionText = theQuestionText;
        myOptionA = theA;
        myOptionB = theB;
        myOptionC = theC;
        myOptionD = theD;
        myOptions = new ArrayList<>();
        myOptions.add(myOptionA);
        myOptions.add(myOptionB);
        myOptions.add(myOptionC);
        myOptions.add(myOptionD);
        myType = Integer.parseInt(theType);
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
    public ArrayList<String> getOptions() {
        return myOptions;
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

