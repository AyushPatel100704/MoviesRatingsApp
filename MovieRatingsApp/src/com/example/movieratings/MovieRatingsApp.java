package com.example.movieratings;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieRatingsApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the primary stage
        primaryStage.setTitle("Movie Ratings Comparison");

        // Create database connection and fetch data
        List<Movie> movies = fetchDataFromDatabase();

        // Create BarChart components
        final CategoryAxis xAxis = new CategoryAxis(); // X-axis for movie titles
        final NumberAxis yAxis = new NumberAxis(); // Y-axis for ratings
        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis); // Bar chart for movie ratings
        barChart.setTitle("Movie Ratings Comparison"); // Title of the bar chart
        xAxis.setLabel("Movies"); // Label for the X-axis
        yAxis.setLabel("Rating"); // Label for the Y-axis

        // Prepare data series for each director
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Christopher Nolan"); // Series for Christopher Nolan's movies
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Steven Spielberg"); // Series for Steven Spielberg's movies

        // Populate data series from fetched data
        for (Movie movie : movies) {
            // Check the director and add the movie to the appropriate series
            if ("Christopher Nolan".equals(movie.getDirector())) {
                series1.getData().add(new XYChart.Data<>(movie.getTitle(), movie.getRating()));
            } else if ("Steven Spielberg".equals(movie.getDirector())) {
                series2.getData().add(new XYChart.Data<>(movie.getTitle(), movie.getRating()));
            }
        }

        // Add both series to the bar chart
        barChart.getData().addAll(series1, series2);

        // Create a button for navigating to the TableView
        Button tableViewButton = new Button("Show Movie Ratings Table");
        // Set an action on the button to show the TableView when clicked
        tableViewButton.setOnAction(event -> showTableView());

        // Create a VBox layout to arrange the bar chart and button vertically
        VBox vbox = new VBox(barChart, tableViewButton);

        // Create a scene with the VBox layout and set the scene on the primary stage
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to show the TableView scene
    private void showTableView() {
        // Create a new instance of MovieTableView
        MovieTableView tableView = new MovieTableView();
        // Create a new stage for the TableView
        Stage stage = new Stage();
        // Start the TableView on the new stage
        tableView.start(stage);
    }

    // Method to fetch data from the MySQL database
    private List<Movie> fetchDataFromDatabase() {
        List<Movie> movies = new ArrayList<>();
        // Create a new instance of DatabaseConnection
        DatabaseConnection dbConnection = new DatabaseConnection();
        // Get a connection to the database
        Connection connection = dbConnection.getConnection();

        try (Statement statement = connection.createStatement()) {
            // SQL query to select movie data
            String query = "SELECT id, title, director, rating, reviews FROM movies";
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set
            while (resultSet.next()) {
                // Extract movie data from the result set
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String director = resultSet.getString("director");
                double rating = resultSet.getDouble("rating");
                int reviews = resultSet.getInt("reviews");

                // Create a new Movie object and add it to the list
                Movie movie = new Movie(id, title, director, rating, reviews);
                movies.add(movie);
            }
        } catch (Exception e) {
            // Print stack trace if an exception occurs
            e.printStackTrace();
        }

        // Return the list of movies
        return movies;
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}
