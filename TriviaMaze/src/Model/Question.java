package Model;

import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class Question {

    private static Question uniqueInstance;
    private ArrayList<Map<String, ArrayList<String>>> myPrintedQuestions;
    private Map<String, ArrayList<String>> myQuestions;


    private Question() {
    }

    public static synchronized Question getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new Question();
        }
        return uniqueInstance;
    }
    public void assignQuestion() throws FileNotFoundException {
        SQLiteDataSource ds = null;
        myPrintedQuestions = new ArrayList<Map<String,ArrayList<String>>>();
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
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        Scanner in = new Scanner(new File("Questions.txt"));
        String queries = "";
        while(in.hasNext()) {
            queries += in.nextLine();
        }
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate(queries);


        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        query = "SELECT DISTINCT * FROM questions";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String question = rs.getString(1);
                String optionA = rs.getString(2);
                String optionB = rs.getString(3);
                String optionC = rs.getString(4);
                String optionD = rs.getString(5);
                String answer = rs.getString(6);
                ArrayList<String> putIt = new ArrayList<>();
                putIt.add(optionA);
                putIt.add(optionB);
                putIt.add(optionC);
                putIt.add(optionD);

                myQuestions.put(question, putIt);

                myPrintedQuestions.add(myQuestions);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public Map<String, ArrayList<String>> generateQuestion() {
        Random randomQ = new Random();
        int choice = randomQ.nextInt(myPrintedQuestions.size());
        return myPrintedQuestions.get(choice);
    }

    public ArrayList<Map<String,ArrayList<String>>> getArray() {
        return myPrintedQuestions;
    }
}