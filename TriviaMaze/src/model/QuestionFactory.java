package model;

import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuestionFactory {
    private static QuestionFactory uniqueInstance;
    private final ArrayList<Question> myQuestionsList;
    private final Random myRandom;

    private QuestionFactory() throws FileNotFoundException {
        myQuestionsList = new ArrayList<Question>();
        assignQuestion();
        myRandom = new Random(myQuestionsList.size());
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
                ArrayList<String> options = new ArrayList<>();
                String questionText = rs.getString(1);
                options.add(rs.getString(2));
                options.add(rs.getString(3));
                options.add(rs.getString(4));
                options.add(rs.getString(5));
                String answer = rs.getString(6);
                Question question = new Question(questionText, options, answer);
                myQuestionsList.add(question);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
    public Question getQuestion() {
        return myQuestionsList.get(myRandom.nextInt(myQuestionsList.size()));
    }
}