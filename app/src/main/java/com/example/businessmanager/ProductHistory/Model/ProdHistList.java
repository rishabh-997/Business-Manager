package com.example.businessmanager.ProductHistory.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProdHistList implements Serializable
{
    @SerializedName("OrderId")
    String orderId;
    @SerializedName("Size")
    String size;
    @SerializedName("Unit")
    String unit;
    @SerializedName("Cost")
    String cost;
    @SerializedName("NVM")
    String nvm;
    @SerializedName("date")
    String date;

    public String getOrderId() {
        return orderId;
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

    public String getNvm() {
        return nvm;
    }

    public String getDate() {
        return date;
    }
}
