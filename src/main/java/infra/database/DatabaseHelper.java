package infra.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private String connectionString;
    private Connection databaseConnection = null;

    public DatabaseHelper(String connectionString) {
        this.connectionString = connectionString;
    }

    public Connection makeDatabaseConnection() {
        try {
            this.databaseConnection = DriverManager.getConnection(this.connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.databaseConnection;
    }

    public boolean destroyDatabaseConnection() {
        try {
           if(this.databaseConnection != null && !this.databaseConnection.isClosed()) {
               this.databaseConnection.close();
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public Connection getDatabaseConnection() {
        return databaseConnection;
    }
}
