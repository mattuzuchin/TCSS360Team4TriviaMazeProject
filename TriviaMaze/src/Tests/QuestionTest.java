package Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.*;
import static org.junit.jupiter.api.Assertions.*;
public class QuestionTest {
    private Question myQuestion;

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

    @Test
    public void testConstructor() {
        assertEquals(myQuestion.getQuestionText(), "What game is this test test case for?");
        assertEquals(myQuestion.getOptionA(), "Fortnite");
        assertEquals(myQuestion.getOptionB(), "Elden Ring");
        assertEquals(myQuestion.getOptionC(), "Call of Duty");
        assertEquals(myQuestion.getOptionD(), "Trivia Maze");
        assertEquals(myQuestion.getCorrectAnswer(), "Trivia Maze");
        assertEquals(myQuestion.getType(), 1);
    }

    @Test
    public void testToString() {
        assertEquals(myQuestion.toString(), "Question{" +
                "questionText=" + myQuestion.getQuestionText()  +
                ", optionA=" + myQuestion.getOptionA()  +
                ", optionB=" + myQuestion.getOptionB()  +
                ", optionC=" + myQuestion.getOptionC()  +
                ", optionD=" + myQuestion.getOptionD()  +
                ", type=" + myQuestion.getType()  +
                ", correctAnswer=" + myQuestion.getCorrectAnswer()  +
                "}");
    }
}
