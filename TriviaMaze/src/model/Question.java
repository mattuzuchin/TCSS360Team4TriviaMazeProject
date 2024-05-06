package model;

import java.util.List;

public class Question {
    String myQuestionBody;
    List<String> myAnswers;
    Question () {

    }

    public String getQuestionBody() {
        return myQuestionBody;
    }
    public List<String> getAnswers() {
        return myAnswers;
    }
    public boolean isCorrect() {
        return true;
    }
    void generateQuestion() {

    }
}