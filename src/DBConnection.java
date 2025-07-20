

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/emsdb"; // Change "emsdb" to your DB name
    private static final String USER = "root";                            // Change if you use another username
    private static final String PASSWORD = "Bhumika@2003";              // Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }
}
