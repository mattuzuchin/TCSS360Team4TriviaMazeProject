package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Maze Test Class
 */
public class MazeTest {
    /**
     * Maze Object
     */
    private Maze myMaze;

    /**
     * single QF instance
     */
    private final QuestionFactory myQF = QuestionFactory.getInstance();

    /**
     * beforeEach
     */
    @BeforeEach
    public void beforeEach() {
        myMaze = new Maze(7, myQF);
    }

    /**
     * test constructor
     */
    @Test
    public void testConstructor() {
        assertEquals(7,myMaze.getSize());
        assertEquals(6, myMaze.getExitRow());
        assertEquals(6,myMaze.getExitColumn());
        assertEquals(0, myMaze.getTotalRooms());
    }

    /**
     * test maze creation
     */
    @Test
    public void testCreateMaze() {
        myMaze.createMaze();
        assertEquals(49, myMaze.getTotalRooms());
    }
}