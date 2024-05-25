package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;
public class DirectionTest {
    @Test
    public void testLetter() {
        assertEquals('N', Direction.NORTH.letter(), "NORTH should return 'N'");
        assertEquals('W', Direction.WEST.letter(), "WEST should return 'W'");
        assertEquals('S', Direction.SOUTH.letter(), "SOUTH should return 'S'");
        assertEquals('E', Direction.EAST.letter(), "EAST should return 'E'");
    }

    @Test
    public void testValueOf() {
        assertEquals(Direction.NORTH, Direction.valueOf('N'), "valueOf should return NORTH for 'N'");
        assertEquals(Direction.WEST, Direction.valueOf('W'), "valueOf should return WEST for 'W'");
        assertEquals(Direction.SOUTH, Direction.valueOf('S'), "valueOf should return SOUTH for 'S'");
        assertEquals(Direction.EAST, Direction.valueOf('E'), "valueOf should return EAST for 'E'");

        assertNull(Direction.valueOf('X'), "valueOf should return null for an invalid letter");
    }
}