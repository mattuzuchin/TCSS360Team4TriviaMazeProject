package Model;

import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuestionFactory implements Serializable {
    private static QuestionFactory myUniqueInstance = null;
    private ArrayList<Question> myQuestionsList;
    private Random myRandom;

    private QuestionFactory() throws FileNotFoundException {
        myRandom = new Random();
        myQuestionsList = new ArrayList<Question>();
        assignQuestion();
    }

    public void setInstance() {
        myUniqueInstance = null;
    }

    public static synchronized QuestionFactory getInstance() {
        if(myUniqueInstance == null) {
            try {
                myUniqueInstance = new QuestionFactory();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return myUniqueInstance;
    }
    public void assignQuestion() throws FileNotFoundException {
        SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:questions.sqlite");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        String query =
                "CREATE TABLE IF NOT EXISTS questions ( " +
                        "QUESTION TEXT NOT NULL, " +
                        "OPTION_A TEXT NOT NULL, " +
                        "OPTION_B TEXT NOT NULL, " +
                        "OPTION_C TEXT NOT NULL, " +
                        "OPTION_D TEXT NOT NULL, " +
                        "ANSWER TEXT NOT NULL, " +
                        "TYPE TEXT NOT NULL )";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement(); ) {
             int rv = stmt.executeUpdate( query );
            Scanner in = new Scanner(new File("Questions.txt"));
            String queries = "";
            while(in.hasNext()) {
                queries += in.nextLine();
            }
            rv = stmt.executeUpdate(queries);
            query = "SELECT DISTINCT * FROM questions";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String questionText = rs.getString(1);
                String optionA = rs.getString(2);
                String optionB = rs.getString(3);
                String optionC = rs.getString(4);
                String optionD = rs.getString(5);
                String answer = rs.getString(6);
                String type = rs.getString(7);
                Question question = new Question(questionText, optionA, optionB, optionC, optionD,answer, type);
                myQuestionsList.add(question);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
    public Question getQuestion() {
        int size = myQuestionsList.size();
        int[] indices = getRandomPermutationOfIntegers(size);
        for (int i = 0; i < size; i++) {
            int index = indices[i];
            Question question = myQuestionsList.get(index);
            return question;
        }

        return null;
    }
    public static int[] getRandomPermutationOfIntegers(final int theSize) {
        int[] data = new int[theSize];
        for (int i = 0; i < theSize; i++) {
            data[i] = i;
        }
        for (int i = 0; i < theSize; i++) {
            int temp;
            int swap = i + (int) ((theSize - i) * Math.random());
            temp = data[i];
            data[i] = data[swap];
            data[swap] = temp;
        }

        return data;
    }

    public ArrayList<Question> getQuestionsList() {
        return myQuestionsList;
    }
}