package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;


    private DBConnection() throws SQLException {
   connection= DriverManager.getConnection("jdbc:mysql//localhost:3306/thogakade","root","1234");
    }

    public static DBConnection getINSTANCE() throws SQLException {
        if (dbConnection ==null) {
            return dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
