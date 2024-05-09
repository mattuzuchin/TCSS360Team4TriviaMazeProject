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
    private ArrayList<String> myPrintedQuestions;
    private ArrayList<String> myArrayA;
    private ArrayList<String> myArrayB;
    private ArrayList<String> myArrayC;
    private ArrayList<String> myArrayD;
    private ArrayList<String> myArrayAnswer;

    private Map<String, ArrayList<String>> myQuestions;

    private int myChoice;

    private Question() throws FileNotFoundException {
        assignQuestion();
    }

    // singleton
    public static synchronized Question getInstance() {
        if(uniqueInstance == null) {
            try {
                uniqueInstance = new Question();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return uniqueInstance;
    }
    public void assignQuestion() throws FileNotFoundException {
        SQLiteDataSource ds = null;
        myPrintedQuestions = new ArrayList<String>();
        myArrayA = new ArrayList<String>();
        myArrayB = new ArrayList<String>();
        myArrayC = new ArrayList<String>();
        myArrayD = new ArrayList<String>();
        myArrayAnswer = new ArrayList<String>();
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
                myPrintedQuestions.add(question);
                myArrayA.add(optionA);
                myArrayB.add(optionB);
                myArrayC.add(optionC);
                myArrayD.add(optionD);
                myArrayAnswer.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public String getQ(final int theQ) {
        if(theQ < 0 || theQ > myPrintedQuestions.size()) {
            throw new IllegalArgumentException("Size is not valid: " + theQ);
        }
        return myPrintedQuestions.get(theQ);
    }
    public String getA(final int theA) {
        if(theA < 0 || theA > myArrayA.size()) {
            throw new IllegalArgumentException("Size is not valid: " + theA);
        }
        return myArrayA.get(theA);
    }
    public String getB(final int theB) {
        if(theB < 0 || theB > myArrayB.size()) {
            throw new IllegalArgumentException("Size is not valid: " + theB);
        }
        return myArrayB.get(theB);
    }
    public String getC(final int theC) {
        if(theC < 0 || theC > myArrayC.size()) {
            throw new IllegalArgumentException("Size is not valid: " + theC);
        }
        return myArrayC.get(theC);
    }
    public String getD(final int theD) {
        if(theD < 0 || theD > myArrayD.size()) {
            throw new IllegalArgumentException("Size is not valid: " + theD);
        }
        return myArrayD.get(theD);
    }
    public String getAns(final int theAns) {
        if(theAns < 0 || theAns > myArrayAnswer.size()) {
            throw new IllegalArgumentException("Size is not valid: " + theAns);
        }
        return myArrayAnswer.get(theAns);
    }
    public int generateQuestion() {
        Random randomQ = new Random();
        myChoice = randomQ.nextInt(myPrintedQuestions.size());
        return myChoice;
    }

    public int getChoice() {
        return myChoice;
    }
}