package connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Connection to the database is managed here. This file contains url, username,
 * and password to the database and also methods to access the database.
 * @author Jelle Mogony, AMS04
 */
public class DbManager {

    public static final String JDBC_EXCEPTION = "JDBC Exception: ";
    public static final String SQL_EXCEPTION = "SQL Exception: ";
    public static Connection connection;

    public DbManager() throws IllegalAccessException {
        throw new IllegalAccessException("DbManager is an utility class. It is not meant to be declared or initialized.");
    }

    /**
     * Connects to the database. Do not forget to call the method closeConnection()
     * when the connection is no longer needed.
     */
    public static void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            /* Db Server settings */
            String url = "jdbc:mysql://localhost:3306/amsta";//?zeroDateTimeBehavior=convertToNull";
            String user = "root", pass = "";

            /* Open connection */
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception");
            System.err.println(JDBC_EXCEPTION + e);
        } catch (java.sql.SQLException e) {
            System.out.println("java sql SQLException");
            System.err.println(SQL_EXCEPTION + e);
        }
    }

    /**
     * This method closes the database connection.
     */
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Executes a query without result.
     * @param query, the SQl query
     */
    public static void executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
    }
    
    /**
     * Executes an update query like UPDATE, SET or INSERT. Returns no result.
     * @param query 
     */
    public static void executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
    }

    /**
     * Executes a query with result.
     * @param query Query that should be executed
     * @return Results of the query
     */
    public static ResultSet doQuery(String query) {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
        return result;        
    }

    /**
     * Executes a query with result.
     * @param query, the SQL query
     * @return 
     */
    public static ResultSet insertQuery(String query) {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            result = statement.getGeneratedKeys();
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
        return result;
    }
}