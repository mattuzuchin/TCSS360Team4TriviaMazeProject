package View;

import java.awt.EventQueue;

/**
 * The TriviaMazeMain class contains the main method to launch the Trivia Maze game.
 * @author Matthew Uzunoe-Chin, Dustin Feldt, Elias Arriolas
 * @version Spring 2024
 */
public final class TriviaMazeMain {

    /**
     * Private constructor to prevent instantiation.
     */
    private TriviaMazeMain() {
    }

    /**
     * The main method to start the Trivia Maze game.
     * It schedules the creation of the TitleScreen on the Event Dispatch Thread.
     *
     * @param theArgs the command line arguments
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(() -> new TitleScreen());
    }
}
