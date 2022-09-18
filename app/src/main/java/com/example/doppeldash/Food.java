package com.example.doppeldash;

public class Food {
    private int id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int restaurant;

    public Food(int id, String name, String description, String image, double price, int restaurant) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.restaurant = restaurant;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public int getRestaurant() {
        return restaurant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRestaurant(int restaurant) {
        this.restaurant = restaurant;
    }
}