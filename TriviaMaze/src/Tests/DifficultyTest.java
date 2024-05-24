package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;
public class DifficultyTest {
    @Test
    public void testGetName() {
        assertEquals("Easy", Difficulty.EASY.getName(), "EASY should return 'Easy'");
        assertEquals("Medium", Difficulty.MEDIUM.getName(), "MEDIUM should return 'Medium'");
        assertEquals("Hard", Difficulty.HARD.getName(), "HARD should return 'Hard'");
        assertEquals("Extreme", Difficulty.EXTREME.getName(), "EXTREME should return 'Extreme'");
    }

    @Test
    public void testGetSize() {
        assertEquals(4, Difficulty.EASY.getSize("Easy"), "EASY should return size 4 for 'Easy'");
        assertEquals(5, Difficulty.MEDIUM.getSize("Medium"), "MEDIUM should return size 5 for 'Medium'");
        assertEquals(6, Difficulty.HARD.getSize("Hard"), "HARD should return size 6 for 'Hard'");
        assertEquals(7, Difficulty.EXTREME.getSize("Extreme"), "EXTREME should return size 7 for 'Extreme'");

        // Test default case
        assertEquals(7, Difficulty.EASY.getSize("Invalid"), "Invalid difficulty should return size 7");
    }
}
