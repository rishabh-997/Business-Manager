package com.example.businessmanager.Cart.Model;

import com.google.gson.annotations.SerializedName;

public class CartList
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

    @SerializedName("Size")
    String size;
    @SerializedName("Unit")
    String unit;
    @SerializedName("Cost")
    String cost;

    public CartList(String name, String description, String id, String image_url, String sub_category, String size, String unit, String cost) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.image_url = image_url;
        this.sub_category = sub_category;
        this.size = size;
        this.unit = unit;
        this.cost = cost;
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

    public String getSize() {
        return size;
    }

    public String getUnit() {
        return unit;
    }

    public String getCost() {
        return cost;
    }
}
