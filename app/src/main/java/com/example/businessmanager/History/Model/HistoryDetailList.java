package com.example.businessmanager.History.Model;

import com.google.gson.annotations.SerializedName;

public class HistoryDetailList
{
    @SerializedName("Size")
    String size;
    @SerializedName("Unit")
    String unit;
    @SerializedName("Cost")
    String cost;
    @SerializedName("TotalCost")
    String total_cost;
    @SerializedName("Product_Name")
    String id;

    public String getSize() {
        return size;
    }

    public String getUnit() {
        return unit;
    }

    public String getCost() {
        return cost;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public String getId() {
        return id;
    }
}
