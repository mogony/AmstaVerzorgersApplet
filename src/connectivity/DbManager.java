package connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jelle Mogony, AMS04
 */
public class DbManager {

    public final String JDBC_EXCEPTION = "JDBC Exception: ";
    public final String SQL_EXCEPTION = "SQL Exception: ";
    
    public Connection connection;

    /**
     * Open database connection
     */
    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

//            String url = "http://rieke.lt";
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
     * Close database connection
     */
    public void closeConnection() {
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
    public void executeQuery(String query) {
        openConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
    }

    /**
     * Executes a query with result.
     * @param query Query that should be executed
     * @return Results of the query
     */
    public ResultSet doQuery(String query) {
        openConnection();
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
//        closeConnection();
        return result;        
    }

    /**
     * Executes a query with result.
     *
     * @param query, the SQL query
     * @return 
     */
    public ResultSet insertQuery(String query) {
        openConnection();
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
