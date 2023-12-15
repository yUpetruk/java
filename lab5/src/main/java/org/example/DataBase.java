package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    Connection connection;

    public DataBase() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DataBase(String dbPath) {
        if (dbPath == null || dbPath.isEmpty()) {
            throw new RuntimeException("No path to db file was provided, if you meant to use in-memory database, " +
                    "use new DataBase()");
        }

        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
