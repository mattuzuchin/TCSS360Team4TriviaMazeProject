package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;
public class DoorsTest {
    private Maze myMaze;

    Doors myDoors;
    private QuestionFactory myQF = QuestionFactory.getInstance();

    @BeforeEach
    public void beforeEach() {
        myMaze = new Maze(4, myQF);
        myDoors = new Doors();
    }

    @Test
    public void testConstructer() {
        assertFalse(myDoors.getMyNorthDoor().isLocked());
        assertFalse(myDoors.getMyEastDoor().isLocked());
        assertFalse(myDoors.getMySouthDoor().isLocked());
        assertFalse(myDoors.getMyWestDoor().isLocked());
    }

    @Test
    public void testCheckNumber() {
        myDoors.getMyNorthDoor().setLockedStatus(true);
        assertEquals(1,myDoors.checkNumber());
    }
    @Test
    public void testCheckNumber2() {
        myDoors.getMyNorthDoor().setLockedStatus(true);
        myDoors.getMyEastDoor().setLockedStatus(true);
        assertEquals(2,myDoors.checkNumber());
    }
    @Test
    public void testCheckNumber3() {
        myDoors.getMyNorthDoor().setLockedStatus(true);
        myDoors.getMyEastDoor().setLockedStatus(true);
        myDoors.getMySouthDoor().setLockedStatus(true);
        assertEquals(3,myDoors.checkNumber());
    }
    @Test
    public void testCheckNumber4() {
        myDoors.getMyNorthDoor().setLockedStatus(true);
        myDoors.getMyEastDoor().setLockedStatus(true);
        myDoors.getMySouthDoor().setLockedStatus(true);
        myDoors.getMyWestDoor().setLockedStatus(true);
        assertEquals(4,myDoors.checkNumber());
    }
    @Test
    public void testToString() {
        assertEquals("North door locked status: false " +
                "East door locked status: false " +
                "South door locked status: false " +
                "West door locked status: false", myDoors.toString());
    }

}
