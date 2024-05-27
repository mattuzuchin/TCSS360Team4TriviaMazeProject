package Controller;

import View.QuestionPanel;
/**
 * Define the behaviors of the Trivia Maze.
 *
 * @author Matthew Uzunoe-Chin, Dustin Feldt, Elias Arriolas
 * @version Spring 2024
 */
public interface TriviaMazeControls {
    /**
     * Advances the simulation north (or up).
     * @param thePanel is a question panel object.
     */
    void advanceNorth(final QuestionPanel thePanel);

    /**
     * Advances the simulation east (or right).
     * @param thePanel is a question panel object.
     */
    void advanceEast(final QuestionPanel thePanel);

    /**
     * Advances the simulation south (or down).
     * @param thePanel is a question panel object.
     */
    void advanceSouth(final QuestionPanel thePanel);

    /**
     * Advances the simulation west (or left).
     * @param thePanel is a question panel object.
     */
    void advanceWest(final QuestionPanel thePanel);

    /**
     * lockDoor will lock a door given an int direction
     * @param theDir given int direction
     */
    void lockDoor(final int theDir);


}
