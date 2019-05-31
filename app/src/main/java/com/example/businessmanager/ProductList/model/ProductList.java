package com.example.businessmanager.ProductList.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductList implements Serializable
{
    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description;

    @SerializedName("id")
    String id;

    @SerializedName("image")
    String image_url;

    @SerializedName("sub_category")
    String sub_category;

    public ProductList(String name, String description, String id, String image_url, String sub_category) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.image_url = image_url;
        this.sub_category = sub_category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getSub_category() {
        return sub_category;
    }
}
