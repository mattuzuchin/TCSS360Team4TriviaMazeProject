package model;

import java.util.ArrayList;

public class Question {

    private final String questionText;
    private final ArrayList<String> myAnswerOptions;
    private final String myAnswer;

    public Question(final String theQuestionText, final ArrayList<String> theOptions, final String theAnswer) {
        questionText = theQuestionText;
        myAnswerOptions = theOptions;
        myAnswer = theAnswer;
    }
    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getOptions() {
        return myAnswerOptions;
    }
    public String getCorrectAnswer() {
        return myAnswer;
    }

    @Override
    public String toString() {
        return "Question Text:" + questionText + '\'' +
                "Options: " + myAnswerOptions.toString() + "\n" +
                "Correct Answer='" + myAnswer + '\'';
    }
}