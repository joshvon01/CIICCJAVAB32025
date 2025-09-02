package VCashApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// A simple class to handle the database connection using JDBC.
// NOTE: This uses a hardcoded connection string. In a real application,
// this should be stored securely and not in the source code.
public class VC1DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/gcash_db";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver.
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Make sure it's in your classpath.");
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
