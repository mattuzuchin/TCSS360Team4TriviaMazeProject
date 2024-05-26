package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Doors Test Class
 */
public class DoorsTest {
    /**
     * Maze field
     */
    private Maze myMaze;
    /**
     * Doors object
     */
    private Doors myDoors;

    /**
     * single instance of QF
     */
    private final QuestionFactory myQF = QuestionFactory.getInstance();

    /**
     * beforeEach method
     */
    @BeforeEach
    public void beforeEach() {
        myMaze = new Maze(4, myQF);
        myDoors = new Doors();
    }

    /**
     * tests the constructor
     */
    @Test
    public void testConstructer() {
        assertFalse(myDoors.getMyNorthDoor().isLocked());
        assertFalse(myDoors.getMyEastDoor().isLocked());
        assertFalse(myDoors.getMySouthDoor().isLocked());
        assertFalse(myDoors.getMyWestDoor().isLocked());
    }

    /**
     * tests the check # of locked method (1)
     */
    @Test
    public void testCheckNumber() {
        myDoors.getMyNorthDoor().setLockedStatus(true);
        assertEquals(1,myDoors.checkNumber());
    }

    /**
     * tests the check # of locked method (2)
     */
    @Test
    public void testCheckNumber2() {
        myDoors.getMyNorthDoor().setLockedStatus(true);
        myDoors.getMyEastDoor().setLockedStatus(true);
        assertEquals(2,myDoors.checkNumber());
    }


    /**
     * tests the check # of locked method (3)
     */
    @Test
    public void testCheckNumber3() {
        myDoors.getMyNorthDoor().setLockedStatus(true);
        myDoors.getMyEastDoor().setLockedStatus(true);
        myDoors.getMySouthDoor().setLockedStatus(true);
        assertEquals(3,myDoors.checkNumber());
    }


    /**
     * tests the check # of locked method (4)
     */
    @Test
    public void testCheckNumber4() {
        myDoors.getMyNorthDoor().setLockedStatus(true);
        myDoors.getMyEastDoor().setLockedStatus(true);
        myDoors.getMySouthDoor().setLockedStatus(true);
        myDoors.getMyWestDoor().setLockedStatus(true);
        assertEquals(4,myDoors.checkNumber());
    }

    /**
     * tests toString
     */
    @Test
    public void testToString() {
        assertEquals("North door locked status: false " +
                "East door locked status: false " +
                "South door locked status: false " +
                "West door locked status: false", myDoors.toString());
    }

}