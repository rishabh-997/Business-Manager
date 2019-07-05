package com.example.businessmanager.Cart.Model;

import com.google.gson.annotations.SerializedName;

import java.lang.ref.SoftReference;

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

    @SerializedName("Total_Cost")
    String total_cost;
    @SerializedName("CGST")
    String cgst;
    @SerializedName("SGST")
    String sgst;
    @SerializedName("Total_Cost_WTax")
    String total_cost_tax;

    @SerializedName("cart_id")
    String cartid;

    @SerializedName("Product_Name")
    String prod_name;
    @SerializedName("NVM")
    String nvm;

    public String getCartid() {
        return cartid;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public String getCgst() {
        return cgst;
    }

    public String getSgst() {
        return sgst;
    }

    public String getTotal_cost_tax() {
        return total_cost_tax;
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
