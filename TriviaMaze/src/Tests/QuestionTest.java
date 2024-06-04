package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Question Test Class
 */
public class QuestionTest {
    /**
     * Question object
     */
    private Question myQuestion;

    /**
     * beforeEach
     */
    @BeforeEach
    public void beforeEach() {
        String Question = "What game is this test test case for?";
        String A = "Fortnite";
        String B = "Elden Ring";
        String C = "Call of Duty";
        String D = "Trivia Maze";
        String Answer = "Trivia Maze";
        String QuestionType = "1";

        myQuestion = new Question(Question,A,B,C,D,Answer, QuestionType);
    }

    /**
     * test constructor
     */
    @Test
    public void testConstructor() {
        assertEquals(myQuestion.getMyQuestionText(), "What game is this test test case for?");
        assertEquals(myQuestion.getOptions().get(0), "Fortnite");
        assertEquals(myQuestion.getOptions().get(1), "Elden Ring");
        assertEquals(myQuestion.getOptions().get(2), "Call of Duty");
        assertEquals(myQuestion.getOptions().get(3), "Trivia Maze");
        assertEquals(myQuestion.getCorrectAnswer(), "Trivia Maze");
        assertEquals(myQuestion.getType(), 1);
    }

    /**
     * test toString
     */
    @Test
    public void testToString() {
        assertEquals(myQuestion.toString(), "Question{"
                + "questionText=What game is this test test case for?, "
                + "optionA=Fortnite, "
                + "optionB=Elden Ring, "
                + "optionC=Call of Duty, "
                + "optionD=Trivia Maze, "
                + "type=1, correctAnswer=Trivia Maze"
                + "}");
    }
}