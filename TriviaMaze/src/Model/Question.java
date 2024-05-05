package Model;

import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Question {
    private static ArrayList<String> printedQuestions;
    public static void main(String[] args) throws FileNotFoundException {

        assignQuestion(); //just for testing

    }

    public static void assignQuestion() throws FileNotFoundException {
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:questions.sqlite");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println( "Opened database successfully" );


        //now create a table
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

            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println( "Created questions table successfully" );
        // Plan to have a WIDE RANGE of questions :)
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
        printedQuestions = new ArrayList<>(); // Store unique questions

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery(query);

            // Walk through each row of results and print unique questions
            while (rs.next()) {
                String question = rs.getString(1);
                    String optionA = rs.getString(2);
                    String optionB = rs.getString(3);
                    String optionC = rs.getString(4);
                    String optionD = rs.getString(5);
                    String answer = rs.getString(6);
                    String addArray = question + ", " + optionA + ", " + optionB + ", " + optionC + ", " + optionD + ", " +
                        answer;
                    printedQuestions.add(addArray); // Add question to the set
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}