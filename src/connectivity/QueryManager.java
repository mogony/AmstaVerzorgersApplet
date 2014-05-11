package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import models.*;


/**
 * All queries are stored here. 
 * @author Jelle Mogony, AMS04
 */
public class QueryManager {
//    private final DbManager db = new DbManager();
//    private PreparedStatement ps;
    
    public QueryManager() throws IllegalAccessException {
        throw new IllegalAccessException("Query Manager is an utility class. It is not meant to be instantiated.");
    }
    
    /**
     * This method tests if the given username and password combination is
     * correct. If it is, the user id is returned and further user information
     * can be accesed using that id.
     * @param username the username to be tested
     * @param password the password to be tested
     * @return the user id. If the username and password combination is 
     * incorrect, -1 will be returned.
         */
    public static int login(String username, String password) {
        System.out.println("Logging in as " + username + "...");
        try {
            DbManager.openConnection();
            String sql = "SELECT " + User.ID + ", COUNT(*) AS `rows` "
                    + "FROM " + User.TABLE + " "
                    + "WHERE " + User.NAME  + " = " + stringify(username) + " "
                    + "AND " + User.PW + " = " + stringify(password);
            ResultSet result = DbManager.doQuery(sql);
            if (result.next()) {
                    return result.getInt(User.ID);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with the database!"
                    + "\n" + DbManager.SQL_EXCEPTION + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }
        return -1;
    }
    
    /**
     * Retrieves all relevant user data from the database. Relevant user data
     * does not include the password.
     * @param userId The id associated with all other attributes.
     * @return A user object with attributes associated with the given user id.
     */
    public static User getUserData(int userId) {
        System.out.println("Retrieving personal information of user " + userId 
                        + " from database...");
        User user = new User(-1, null, null, null);
        user.setId(userId);
        try {
            DbManager.openConnection();
            String sql = "SELECT " + User.NAME + ", " + User.DOB + " "
                    + "FROM " + User.TABLE + " "
                    + "WHERE " + User.ID + " = " + userId;
            ResultSet result = DbManager.doQuery(sql);
            if (result.next()) {
                user.setNaam(result.getString(User.NAME));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with the database!"
                    + "\n" + DbManager.SQL_EXCEPTION + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }
        
        System.out.println("Retrieved data of user with ID " + userId);
        return user;
    }
    
    public static LinkedList<Patient> getPatientList() {
        System.out.println("Getting patients list...");
        LinkedList<Patient> patients = new LinkedList();
        try {
            DbManager.openConnection();
            String sql = "SELECT * FROM " + Patient.TABLE;
            ResultSet result = DbManager.doQuery(sql);
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
                    + "\n" + DbManager.SQL_EXCEPTION + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }
        System.out.println("Retrieved list of all patients.");
        return patients;
    }
    
    public static LinkedList<Score> getUserScores(int patientid) {
        System.out.println("Getting all scores of patient " + patientid + "...");
        LinkedList<Score> userScores = new LinkedList();
        try {
            DbManager.openConnection();
            String sql = "SELECT " + Score.LVL + "," + Patient.NAME + "," 
                    + Score.DATE + "," + Score.SCORE + "," + Score.COLS + " "
                    + "FROM score s INNER JOIN bewoner b "
                    + "ON b.bewoner_id = s.bewoner_id "
                    + "WHERE b.bewoner_id = " + patientid + " "
                    + "ORDER BY datum";
            ResultSet result = DbManager.doQuery(sql);
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
                    + "\n" + DbManager.SQL_EXCEPTION + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }
        System.out.println("Done getting all scores of patient " + patientid + ".");
        return userScores;
    }
    
    public static void deletePatient(int patientId) {
        DbManager.openConnection();
        System.out.println("Deleting patient with ID " + patientId + "...");
        String sql = "DELETE FROM bewoner WHERE "+ Patient.ID +" = " + patientId;
        DbManager.executeUpdate(sql);
        DbManager.closeConnection();
    }
    
    public static void editPatient(int patientId, String toChange, String newValue) {
        DbManager.openConnection();
        String sql = "UPDATE bewoner " + "SET " + toChange + "='" + newValue + "'"
                + "WHERE bewoner_id='"+patientId+"';";
        DbManager.executeUpdate(sql);
        DbManager.closeConnection();
    }
    
    public static void addPatient(String in, String ik, String ig, String io) {
        DbManager.openConnection();
        String sql = "INSERT INTO bewoner (naam, kamer, geb_datum, opmerkingen) " +
        "VALUES ('"+in+"','"+ik+"','"+ig+"','"+io+"')";
        DbManager.executeUpdate(sql);
        DbManager.closeConnection();
    }
    
    /**
     * Adds apostrophes to the given string so MySQL reads it as a string.
     * @param unreadable The string that is unreadable to MySQL.
     * @return A readable string for MySQL.
     */
    public static String stringify(String unreadable) {
        return "'" + unreadable + "'";
    }
}