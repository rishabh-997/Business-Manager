package com.example.businessmanager.ProductList.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCat_response
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("unit_list")
    @Expose
    List<SubCat_list> subcat_list;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<SubCat_list> getSubcat_list() {
        return subcat_list;
    }
}
