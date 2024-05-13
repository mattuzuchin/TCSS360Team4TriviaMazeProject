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
    private static QuestionFactory uniqueInstance;
    private ArrayList<Question> myQuestionsList;
    private int myChoice;

    private QuestionFactory() throws FileNotFoundException {
        myQuestionsList = new ArrayList<Question>();
        assignQuestion();
    }

    // singleton
    public static synchronized QuestionFactory getInstance() {
        if(uniqueInstance == null) {
            try {
                uniqueInstance = new QuestionFactory();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return uniqueInstance;
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
                        "ANSWER TEXT NOT NULL )";

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
                Question question = new Question(questionText, optionA, optionB, optionC, optionD, answer);
                myQuestionsList.add(question);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
    public Question getQuestion() {
        Random randomQ = new Random();
        myChoice = randomQ.nextInt(myQuestionsList.size());
        Question noRepeat = myQuestionsList.get(myChoice);
        myQuestionsList.remove(myChoice);
        return noRepeat;
    }


    public int getChoice() {
        return myChoice;
    }
}