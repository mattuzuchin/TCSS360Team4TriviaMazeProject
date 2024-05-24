package Tests;
import Model.QuestionFactory;
import Model.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionFactoryTest {

    private QuestionFactory questionFactory;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        questionFactory = QuestionFactory.getInstance();
    }

    @AfterEach
    public void tearDown() {
        questionFactory.setInstance();
    }

    @Test
    public void testSingletonInstance() throws FileNotFoundException {
        QuestionFactory instance1 = QuestionFactory.getInstance();
        QuestionFactory instance2 = QuestionFactory.getInstance();
        assertSame(instance1, instance2, "Instances should be the same (Singleton pattern)");
    }

    @Test
    public void testAssignQuestion() {
        assertFalse(questionFactory.getQuestionsList().isEmpty(), "Questions list should not be empty after assignment");
    }

    @Test
    public void testGetQuestion() {
        Question question = questionFactory.getQuestion();
        assertNotNull(question, "Retrieved question should not be null");
    }

    @Test
    public void testGetRandomPermutationOfIntegers() {
        int size = 10;
        int[] permutation = QuestionFactory.getRandomPermutationOfIntegers(size);
        assertEquals(size, permutation.length, "Permutation length should match the specified size");
        boolean[] found = new boolean[size];
        for (int value : permutation) {
            found[value] = true;
        }
        for (boolean b : found) {
            assertTrue(b, "Each value from 0 to size-1 should be in the permutation");
        }
    }
}