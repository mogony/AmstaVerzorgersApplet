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
    
    /* Bewoner table attributes */
//    private static final String PATIENT_TBL = "bewoner";
//    private static final String P_ID = "bewoner_id";
//    private static final String P_NAME = "naam";
//    private static final String P_ROOM = "kamer";
//    private static final String P_DOB = "geb_datum";
//    private static final String P_COMMENTS = "opmerkingen";

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
            String sql = "SELECT " + User.ID + ", COUNT(*) AS `rows` "
                    + "FROM " + User.TABLE + " "
                    + "WHERE " + User.NAME  + " = " + stringify(username) + " "
                    + "AND " + User.PW + " = " + stringify(password);
            ResultSet result = db.doQuery(sql);
            if (result.next()) {
                    return result.getInt(User.ID);
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
        User user = new User(-1, null, null, null);
        user.setId(userId);
        try {
            db.openConnection();
            String sql = "SELECT " + User.NAME + ", " + User.DOB + " "
                    + "FROM " + User.TABLE + " "
                    + "WHERE " + User.ID + " = " + userId;
            ResultSet result = db.doQuery(sql);
            if (result.next()) {
                user.setNaam(result.getString(User.NAME));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with the database!"
                    + "\n" + db.SQL_EXCEPTION + e.getMessage());
        } finally {
            db.closeConnection();
        }
        System.out.println("Retrieved data of user with ID " + userId);
        return user;
    }
    
    public LinkedList<Patient> getPatientList() {
        System.out.println("Getting patients list...");
        LinkedList<Patient> patients = new LinkedList<Patient>();
        try {
            db.openConnection();
            String sql = "SELECT * FROM " + Patient.TABLE;
            ResultSet result = db.doQuery(sql);
            while(result.next()) {
                patients.add(new Patient(
                    result.getInt(Patient.ID),
                    result.getString(Patient.NAME),
                    result.getString(Patient.ROOM),
                    result.getString(Patient.DOB),
                    result.getString(Patient.COMMENTS)));
            }
        } catch(SQLException e) {
            System.out.println("Something went wrong with the database!"
                    + "\n" + db.SQL_EXCEPTION + e.getMessage());
        } finally {
            db.closeConnection();
        }
        System.out.println("Retrieved list of all patients.");
        return patients;
    }
    
    public LinkedList<Score> getUserScores(int patientid) {
        System.out.println("Getting all scores of patient " + patientid + "...");
        LinkedList<Score> userScores = new LinkedList();
        try {
            db.openConnection();
            String sql = "SELECT " + Score.LVL + "," + Patient.NAME + "," 
                    + Score.DATE + "," + Score.SCORE + "," + Score.COLS + " "
                    + "FROM score s INNER JOIN bewoner b "
                    + "ON b.bewoner_id = s.bewoner_id "
                    + "WHERE b.bewoner_id = " + patientid + " "
                    + "ORDER BY datum";
            ResultSet result = db.doQuery(sql);
            while(result.next()) {
                userScores.add(new Score(
                    result.getInt("level_id"),
                    result.getString("naam"),
                    result.getString("score"),
                    result.getInt("collisions"),
                    result.getString("datum")));
            }
        } catch(SQLException e) {
            System.out.println("Something went wrong with the database!"
                    + "\n" + db.SQL_EXCEPTION + e.getMessage());
        } finally {
            db.closeConnection();
        }
        System.out.println("Done getting all scores of patient " + patientid + ".");
        return userScores;
    }
    
    public void deletePatient(int patientId) {
        System.out.println("Deleting patient with ID " + patientId + "...");
        db.openConnection();
        String sql = "DELETE FROM bewoner WHERE "+ Patient.ID +" = " + patientId;
        db.executeUpdate(sql);
        db.closeConnection();
    }
    
    public void editPatient(int patientId, String toChange, String newValue) {
        db.openConnection();
        String sql = "UPDATE bewoner " + "SET " + toChange + "='" + newValue + "'"
                + "WHERE bewoner_id='"+patientId+"';";
        System.out.println(sql);
        db.executeUpdate(sql);
        db.closeConnection();
    }
    
    public void addPatient(String in, String ik, String ig, String io) {
        String sql = "INSERT INTO bewoner (naam, kamer, geb_datum, opmerkingen) " +
        "VALUES ('"+in+"','"+ik+"','"+ig+"','"+io+"')";
        System.out.println(sql);
        db.executeUpdate(sql);
        db.closeConnection();
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