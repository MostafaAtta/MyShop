package com.atta.myshop.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductsResult {

    @SerializedName("products")
    private ArrayList<Product> products;

    public ProductsResult(Boolean error, ArrayList<Product> products) {

        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
