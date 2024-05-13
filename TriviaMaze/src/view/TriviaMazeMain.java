package View;

import java.awt.*;

public class TriviaMazeMain {
    private TriviaMazeMain() {}
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new TitleScreen());
    }
}
