package com.jpets.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    public static Connection connect() {
        String url = "jdbc:sqlite:./src/main/resources/db/JPetsData.db"; // Cambia el path

        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Conexión establecida.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
