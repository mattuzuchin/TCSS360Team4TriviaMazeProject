package Controller;

import View.QuestionPanel;

public interface TriviaMazeControls {

    void advanceNorth(final QuestionPanel thePanel);
    void advanceEast(final QuestionPanel thePanel);
    void advanceSouth(final QuestionPanel thePanel);
    void advanceWest(final QuestionPanel thePanel);
    void lockDoor(final int theDir);


}
