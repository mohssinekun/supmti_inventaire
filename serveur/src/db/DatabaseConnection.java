package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Database URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/inventaire";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Mysql+123";
    private static boolean isInitialized = false;

    // Method to establish and return the connection
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver (optional for modern Java versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            throw new SQLException("Driver not found.", e);
        }

        // Establish connection
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // Initialize the database only once
        if (!isInitialized) {
            try {
                DatabaseInitializer.initializeDatabase(connection);
                isInitialized = true;
            } catch (Exception e) {
                System.out.println("Error initializing database:");
                e.printStackTrace();
            }
        }

        return connection;
    }
}
