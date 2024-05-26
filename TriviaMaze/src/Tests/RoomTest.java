package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Room Test Class
 */
public class RoomTest {
    /**
     * maze object
     */
    private Maze myMaze;

    /**
     * single QF instance
     */
    private final QuestionFactory myQF = QuestionFactory.getInstance();

    /**
     * room object
     */
    private Room myRoom;

    /**
     * beforeEach
     */
    @BeforeEach
    public void beforeEach() {
        myMaze = new Maze(4, myQF);
        myRoom = new Room(2,3);
    }

    /**
     * test constructor
     */
    @Test
    public void testConstructor() {
        assertEquals(2, myRoom.getRow());
        assertEquals(3,myRoom.getColumn());
    }

    /**
     * test toString
     */
    @Test
    public void testToString() {
        assertEquals("Row: 2 Column: 3", myRoom.toString());
    }

    /**
     * test 1 door locked
     */
    @Test
    public void testToStringOneDoorLocked() {
        myRoom.getDoors().getMyNorthDoor().setLockedStatus(true);
        assertEquals("Row: 2 Column: 3, North door locked",myRoom.toString());
    }

    /**
     * test 2 door locked
     */
    @Test
    public void testToStringTwoDoorLocked() {
        myRoom.getDoors().getMyNorthDoor().setLockedStatus(true);
        myRoom.getDoors().getMyEastDoor().setLockedStatus(true);
        assertEquals("Row: 2 Column: 3, North door locked, East door locked",myRoom.toString());
    }

    /**
     * test 3 door locked
     */
    @Test
    public void testToStringThreeDoorLocked() {
        myRoom.getDoors().getMyNorthDoor().setLockedStatus(true);
        myRoom.getDoors().getMyEastDoor().setLockedStatus(true);
        myRoom.getDoors().getMySouthDoor().setLockedStatus(true);
        assertEquals("Row: 2 Column: 3, North door locked" +
                ", East door locked, South door locked",myRoom.toString());
    }

    /**
     * test all doors locked
     */
    @Test
    public void testToStringAllDoorLocked() {
        myRoom.getDoors().getMyNorthDoor().setLockedStatus(true);
        myRoom.getDoors().getMyEastDoor().setLockedStatus(true);
        myRoom.getDoors().getMySouthDoor().setLockedStatus(true);
        myRoom.getDoors().getMyWestDoor().setLockedStatus(true);
        assertEquals("Row: 2 Column: 3, North door locked" +
                ", East door locked, South door locked, West door locked",myRoom.toString());
    }


}