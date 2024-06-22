package com.example.movieratings;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieTableView extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Movie Ratings Table");

        // Create TableView to display movie data
        TableView<Movie> tableView = new TableView<>();
        TableColumn<Movie, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Movie, String> directorCol = new TableColumn<>("Director");
        directorCol.setCellValueFactory(new PropertyValueFactory<>("director"));

        TableColumn<Movie, Double> ratingCol = new TableColumn<>("Rating");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));

        TableColumn<Movie, Integer> reviewsCol = new TableColumn<>("Reviews");
        reviewsCol.setCellValueFactory(new PropertyValueFactory<>("reviews"));

        tableView.getColumns().addAll(titleCol, directorCol, ratingCol, reviewsCol);

        // Fetch data from database
        List<Movie> movies = fetchDataFromDatabase();

        // Add data to TableView
        ObservableList<Movie> data = FXCollections.observableArrayList(movies);
        tableView.setItems(data);

        // Create VBox layout for TableView
        VBox vbox = new VBox(tableView);

        // Create scene and show stage
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    // Method to fetch data from MySQL database
    private List<Movie> fetchDataFromDatabase() {
        List<Movie> movies = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT id, title, director, rating, reviews FROM movies";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String director = resultSet.getString("director");
                double rating = resultSet.getDouble("rating");
                int reviews = resultSet.getInt("reviews");

                Movie movie = new Movie(id, title, director, rating, reviews);
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
