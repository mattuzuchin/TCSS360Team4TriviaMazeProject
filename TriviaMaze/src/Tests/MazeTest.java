package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;
public class MazeTest {
    private Maze myMaze;
    public Room[][] myRooms;
    private QuestionFactory myQF = QuestionFactory.getInstance();

    @BeforeEach
    public void beforeEach() {
        myMaze = new Maze(7, myQF);
    }
    @Test
    public void testConstructor() {
        assertEquals(7,myMaze.getSize());
        assertEquals(6, myMaze.getExitRow());
        assertEquals(6,myMaze.getExitColumn());
        assertEquals(0, myMaze.getTotalRooms());
    }
    @Test
    public void testCreateMaze() {
        myMaze.createMaze();
        assertEquals(49, myMaze.getTotalRooms());
    }
}