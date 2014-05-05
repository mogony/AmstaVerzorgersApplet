package connectivity;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import models.*;


/**
 *
 * @author Jelle Mogony, AMS04
 */
public class QueryManager {
    private final DbManager db = new DbManager();
    private PreparedStatement ps;
    
    /* Verzoger table attributes */
    private static final String NURSE_TBL = "verzorger";
    private static final String USER_ID = "werknemer_id";
    private static final String U_NAME = "naam";
    private static final String U_DOB = "geb_datum";
    private static final String U_PASSWORD = "wachtwoord";
    
    /* Bewoner table attributes */
    private static final String PATIENT_TBL = "bewoner";
    private static final String P_ID = "bewoner_id";
    private static final String P_NAME = "naam";
    private static final String P_ROOM = "kamer";
    private static final String P_DOB = "geb_datum";
    private static final String P_COMMENTS = "opmerkingen";

    /**
     * This method tests if the given username and password combination is
     * correct. If it is, the user id is returned and further user information
     * can be accesed using that id.
     * @param username the username to be tested
     * @param password the password to be tested
     * @return the user id. If the username and password combination is 
     * incorrect, -1 will be returned.
     */
    public int login(String username, String password) {
        System.out.println("Logging in as " + username + "...");
        try {
            db.openConnection();
            String sql = "SELECT " + USER_ID + ", COUNT(*) AS `rows` "
                    + "FROM " + NURSE_TBL + " "
                    + "WHERE " + U_NAME  + " = " + stringify(username) + " "
                    + "AND " + U_PASSWORD + " = " + stringify(password);
            ResultSet result = db.doQuery(sql);
            if (result.next()) {
                    return result.getInt(USER_ID);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with the database!"
                    + "\n" + db.SQL_EXCEPTION + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return -1;
    }
    
    /**
     * Retrieves all relevant user data from the database. Relevant user data
     * does not include the password.
     * @param userId The id associated with all other attributes.
     * @return A user object with attributes associated with the given user id.
     */
    public User getUserData(int userId) {
        System.out.println("Retrieving personal information of user " + userId 
                        + " from database...");
        User user = new User();
        user.setId(userId);
        try {
            db.openConnection();
            String sql = "SELECT " + U_NAME + ", " + U_DOB + " "
                    + "FROM " + NURSE_TBL + " "
                    + "WHERE " + USER_ID + " = " + userId;
            ResultSet result = db.doQuery(sql);
            if (result.next()) {
                user.setNaam(result.getString(U_NAME));
                System.out.println("Name is " + user.getNaam() + ".");
                user.setGebDatum(result.getString(U_DOB));
                System.out.println("Date of birth is " + user.getGebDatum()+"."
                    + "\nAll information retrieved.");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with the database!"
                    + "\n" + db.SQL_EXCEPTION + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return user;
    }
    
    public LinkedList<Patient> getPatientList() {
        System.out.println("Getting patients list...");
        LinkedList<Patient> patients = new LinkedList<Patient>();
        try {
            db.openConnection();
            String sql = "SELECT * FROM " + PATIENT_TBL;
            ResultSet result = db.doQuery(sql);
            while(result.next()) {
                //ublic Patient(int patientId, String name, String room, String dob, String comments) {
                patients.add(new Patient(
                    result.getInt(P_ID),
                    result.getString(P_NAME),
                    result.getString(P_ROOM),
                    result.getString(P_DOB),
                    result.getString(P_COMMENTS)));
            }
        } catch(SQLException e) {
            System.out.println("Something went wrong with the database!"
                    + "\n" + db.SQL_EXCEPTION + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return patients;
    }
    
    public LinkedList<Score> getUserScores(int patientid) {
        System.out.println("Getting all scores of user " + patientid + "...");
        LinkedList<Score> userScores = new LinkedList();
        try {
            db.openConnection();
            String sql = "SELECT level_id, naam, datum, score, collisions "
                    + "FROM score s INNER JOIN bewoner b "
                    + "ON b.bewoner_id = s.bewoner_id "
                    + "WHERE b.bewoner_id = " + patientid;
            ResultSet result = db.doQuery(sql);
            while(result.next()) {
                userScores.add(new Score(
                    result.getInt("level_id"),
                    result.getString("naam"),
                    result.getInt("score"),
                    result.getInt("collisions"),
                    result.getString("datum")));
            }
        } catch(SQLException e) {
            System.out.println("Something went wrong with the database!"
                    + "\n" + db.SQL_EXCEPTION + e.getMessage());
        } finally {
            db.closeConnection();
        }
        return userScores;
    }
    
    /**
     * Adds apostrophes to the given string so MySQL reads it as a string.
     * @param unreadable The string that is unreadable to MySQL.
     * @return A readable string for MySQL.
     */
    public String stringify(String unreadable) {
        return "'" + unreadable + "'";
    }
}