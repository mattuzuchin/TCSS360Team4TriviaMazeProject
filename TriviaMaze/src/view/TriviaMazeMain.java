package view;

import java.awt.*;

public class TriviaMazeMain {

    private TriviaMazeMain() {
        // Dummy constructor
    }

    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(() -> new TriviaMazeGUI());    }
}
