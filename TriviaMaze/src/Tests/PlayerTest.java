package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {
    private Player myPlayer;

    @BeforeEach
    public void beforeEach() {
        myPlayer = new Player("Joe Mama");
    }

    @Test
    public void testConstructor() {
        assertEquals("Joe Mama", myPlayer.getName());
        assertEquals (0, myPlayer.getMyRow());
        assertEquals(0,myPlayer.getMyColumn());
    }

    @Test
    public void testValidSetters() {
        myPlayer.setRow(10);
        myPlayer.setColumn(5);
        myPlayer.setName("JOE");
        assertEquals(10,myPlayer.getMyRow());
        assertEquals(5, myPlayer.getMyColumn());
        assertEquals("JOE", myPlayer.getName());
    }

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

    @Test
    public void testToString() {
        assertEquals("Joe Mama", myPlayer.toString());
    }


}
