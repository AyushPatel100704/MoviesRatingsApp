package com.example.movieratings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection getConnection() {
        // Define MySQL database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/moviedb";
        String username = "root"; //  MySQL username
        String password = "paras13679"; // MySQL password

        Connection connection = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to MySQL database!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }

        return connection;
    }
}
