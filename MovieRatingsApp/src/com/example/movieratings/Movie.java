package com.example.movieratings;

/**
 * The Movie class represents a movie with its details.
 */
public class Movie {
    // Private fields to store movie details
    private int id;           // Unique identifier for the movie
    private String title;     // Title of the movie
    private String director;  // Director of the movie
    private double rating;    // Rating of the movie
    private int reviews;      // Number of reviews for the movie

    /**
     * Constructor to initialize the Movie object with the provided details.
     *
     * @param id        Unique identifier for the movie
     * @param title     Title of the movie
     * @param director  Director of the movie
     * @param rating    Rating of the movie
     * @param reviews   Number of reviews for the movie
     */
    public Movie(int id, String title, String director, double rating, int reviews) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.rating = rating;
        this.reviews = reviews;
    }

    /**
     * Get the unique identifier for the movie.
     *
     * @return the id of the movie
     */
    public int getId() {
        return id;
    }

    /**
     * Set the unique identifier for the movie.
     *
     * @param id the new id of the movie
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the title of the movie.
     *
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the movie.
     *
     * @param title the new title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the director of the movie.
     *
     * @return the director of the movie
     */
    public String getDirector() {
        return director;
    }

    /**
     * Set the director of the movie.
     *
     * @param director the new director of the movie
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Get the rating of the movie.
     *
     * @return the rating of the movie
     */
    public double getRating() {
        return rating;
    }

    /**
     * Set the rating of the movie.
     *
     * @param rating the new rating of the movie
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Get the number of reviews for the movie.
     *
     * @return the number of reviews for the movie
     */
    public int getReviews() {
        return reviews;
    }

    /**
     * Set the number of reviews for the movie.
     *
     * @param reviews the new number of reviews for the movie
     */
    public void setReviews(int reviews) {
        this.reviews = reviews;
    }
}
