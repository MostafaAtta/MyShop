package com.atta.myshop.model;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;

    private double price;

    private String name, description,  category, brand;

    private String[] images;

    public Product(int id, double price, String name, String description, String category, String brand, String[] images) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String[] getImages() {
        return images;
    }
}
