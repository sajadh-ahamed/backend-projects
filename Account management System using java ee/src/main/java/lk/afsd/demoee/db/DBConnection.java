package lk.afsd.demoee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;
    private DBConnection() throws SQLException, ClassNotFoundException {
        //load the driver class to the ram
        Class.forName("com.mysql.cj.jdbc.Driver");

        //create a connection with database server
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_accounts_management_system", "root", "12345");

    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
