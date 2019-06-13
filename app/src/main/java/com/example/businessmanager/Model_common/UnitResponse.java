package com.example.businessmanager.Model_common;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UnitResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("unit_list")
    @Expose
    List<UnitList> unitList;

    public UnitResponse(Boolean success, String message, List<UnitList> unitList) {
        this.success = success;
        this.message = message;
        this.unitList = unitList;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<UnitList> getPUnitList() {
        return unitList;
    }}
