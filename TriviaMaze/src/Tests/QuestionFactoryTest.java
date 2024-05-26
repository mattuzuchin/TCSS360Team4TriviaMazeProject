package Tests;
import Model.QuestionFactory;
import Model.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * QuestionFactory Test Class
 */
public class QuestionFactoryTest {

    /**
     * single QF instance
     */
    private final QuestionFactory myQuestionFactory = QuestionFactory.getInstance();

    /**
     * AfterEach
     */
    @AfterEach
    public void tearDown() {
        myQuestionFactory.setInstance();
    }

    /**
     * test singleton instance
     */
    @Test
    public void testSingletonInstance() throws FileNotFoundException {
        QuestionFactory instance1 = QuestionFactory.getInstance();
        QuestionFactory instance2 = QuestionFactory.getInstance();
        assertSame(instance1, instance2, "Instances should be the same (Singleton pattern)");
    }

    /**
     * test assign question
     */
    @Test
    public void testAssignQuestion() {
        assertFalse(myQuestionFactory.getQuestionsList().isEmpty(), "Questions list should not be empty after assignment");
    }

    /**
     * test get question
     */
    @Test
    public void testGetQuestion() {
        Question question = myQuestionFactory.getQuestion();
        assertNotNull(question, "Retrieved question should not be null");
    }

    /**
     * test random number method
     */
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