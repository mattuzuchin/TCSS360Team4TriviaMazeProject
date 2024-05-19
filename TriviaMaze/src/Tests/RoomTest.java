package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;
public class RoomTest {
    private Maze myMaze;
    private QuestionFactory myQF = QuestionFactory.getInstance();
    private Room myRoom;

    @BeforeEach
    public void beforeEach() {
        myMaze = new Maze(4, myQF);
        myRoom = new Room(2,3);
    }

    @Test
    public void testConstructor() {
        assertEquals(2, myRoom.getRow());
        assertEquals(3,myRoom.getColumn());
    }
    @Test
    public void testToString() {
        assertEquals("Row: 2 Column: 3", myRoom.toString());
    }
    @Test
    public void testToStringOneDoorLocked() {
        myRoom.getDoors().getMyNorthDoor().setLockedStatus(true);
        assertEquals("Row: 2 Column: 3, North door locked",myRoom.toString());
    }
    @Test
    public void testToStringTwoDoorLocked() {
        myRoom.getDoors().getMyNorthDoor().setLockedStatus(true);
        myRoom.getDoors().getMyEastDoor().setLockedStatus(true);
        assertEquals("Row: 2 Column: 3, North door locked, East door locked",myRoom.toString());
    }
    @Test
    public void testToStringThreeDoorLocked() {
        myRoom.getDoors().getMyNorthDoor().setLockedStatus(true);
        myRoom.getDoors().getMyEastDoor().setLockedStatus(true);
        myRoom.getDoors().getMySouthDoor().setLockedStatus(true);
        assertEquals("Row: 2 Column: 3, North door locked" +
                ", East door locked, South door locked",myRoom.toString());
    }
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
