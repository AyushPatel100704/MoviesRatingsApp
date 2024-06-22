package com.example.movieratings;

import java.sql.Connection;

public class DatabaseTest {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Connection connection = databaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Connection successful!");
                // You can perform further database operations here if needed
                // For example, querying data or updating records
                connection.close(); // Close the connection when done
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
