package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Player Test Class
 */
public class PlayerTest {
    /**
     * player object
     */
    private Player myPlayer;

    /**
     * beforeEach
     */
    @BeforeEach
    public void beforeEach() {
        myPlayer = new Player("Joe Mama");
    }

    /**
     * test constructor
     */
    @Test
    public void testConstructor() {
        assertEquals("Joe Mama", myPlayer.getName());
        assertEquals (0, myPlayer.getMyRow());
        assertEquals(0,myPlayer.getMyColumn());
    }

    /**
     * test setters
     */
    @Test
    public void testValidSetters() {
        myPlayer.setRow(10);
        myPlayer.setColumn(5);
        myPlayer.setName("JOE");
        assertEquals(10,myPlayer.getMyRow());
        assertEquals(5, myPlayer.getMyColumn());
        assertEquals("JOE", myPlayer.getName());
    }

    /**
     * test invalid row
     */
    @Test
    public void testInvalidSetterRow() {
        boolean InvalidRow = false;
        try {
            myPlayer.setRow(-1);

        } catch (IllegalArgumentException e) {
            InvalidRow = true;
        }
        assertTrue(InvalidRow);

    }

    /**
     * test invalid column
     */
    @Test
    public void testInvalidSetterColumn() {
        boolean InvalidColumn = false;
        try {
            myPlayer.setColumn(-1);
        } catch (IllegalArgumentException e) {
            InvalidColumn = true;
        }
        assertTrue(InvalidColumn);
    }

    /**
     * test invalid name
     */
    @Test
    public void testInvalidSetterName() {
        boolean InvalidName = false;
        try {
            myPlayer.setName("");
        } catch (IllegalArgumentException e) {
            InvalidName = true;
        }
        assertTrue(InvalidName);
    }

    /**
     * test toString
     */
    @Test
    public void testToString() {
        assertEquals("Joe Mama", myPlayer.toString());
    }


}