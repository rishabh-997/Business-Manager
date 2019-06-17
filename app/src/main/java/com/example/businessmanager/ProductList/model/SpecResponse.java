package com.example.businessmanager.ProductList.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpecResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("specs_list")
    @Expose
    List<SpecList> specList;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<SpecList> getSpecList() {
        return specList;
    }
}
