package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;
public class DoorTest {
    private Maze myMaze;
    private QuestionFactory myQF = QuestionFactory.getInstance();
    private Door myDoor;
    @BeforeEach
    public void beforeEach() {
        myMaze = new Maze(4, myQF);
        myDoor = new Door(Direction.NORTH);
    }
    @Test
    public void testDoorConstructor() {

        assertEquals(false, myDoor.isLocked());
    }

    @Test
    public void setLockedTrue() {
        myDoor.setLockedStatus(true);
        assertEquals(true, myDoor.isLocked());
    }

    @Test
    public void toStringTest() {
        assertEquals("Locked status: false, Direction: NORTH", myDoor.toString());
    }

    @Test
    public void getAssigned() {
        myDoor.setQuestion();
        String test = "Question{" +
                "questionText=2+2 is?" +
                ", optionA=1"  +
                ", optionB=2"  +
                ", optionC=3"  +
                ", optionD=4"  +
                ", type=1" +
                ", correctAnswer=4" +
                '}';
        assertEquals(test,myDoor.getMyAssignedQuestion().toString());
    }

    @Test
    public void reAssign() {
        myDoor.setQuestion();
        String test = "Question{" +
                "questionText=2+2 is?" +
                ", optionA=1"  +
                ", optionB=2"  +
                ", optionC=3"  +
                ", optionD=4"  +
                ", type=1" +
                ", correctAnswer=4" +
                '}';
        assertEquals(test,myDoor.getMyAssignedQuestion().toString());

        myDoor.reassignQuestion();
        String test2 = "Question{" +
                "questionText=2+2 is?" +
                ", optionA=1"  +
                ", optionB=2"  +
                ", optionC=3"  +
                ", optionD=4"  +
                ", type=1" +
                ", correctAnswer=4" +
                '}';
        assertEquals(false,!test2.equals(test));
    }
}
