package lk.acpt.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC Driver
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/afsd_09", // DB URL
                    "root",                                // DB Username
                    "1234"                                 // DB Password
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Database connection failed ❌", e);
        }
    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
