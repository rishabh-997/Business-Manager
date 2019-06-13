package com.example.businessmanager.Model_common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UnitList implements Serializable
{

    @SerializedName("unit")
    String unit;

    public String getUnit() {
        return unit;
    }

    public UnitList(String unit) {
        this.unit = unit;
    }
}
