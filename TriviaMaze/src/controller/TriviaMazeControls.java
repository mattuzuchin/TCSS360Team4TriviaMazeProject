package controller;

import view.QuestionPanel;

public interface TriviaMazeControls {

    void advanceNorth(final QuestionPanel thePanel);
    void advanceEast(final QuestionPanel thePanel);
    void advanceSouth(final QuestionPanel thePanel);
    void advanceWest(final QuestionPanel thePanel);
    void lockDoor(final int theDir);
    void start();

    void reset();

    int getHeight();


    int getWidth();
}
