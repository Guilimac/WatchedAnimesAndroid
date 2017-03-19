package br.com.fiap.watchedanimesandroid.model;

/**
 * Created by guilherme on 19/03/17.
 */

public class Anime {

    private int id;
    private String name;
    private String description;
    private double rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
