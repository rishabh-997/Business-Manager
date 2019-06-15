package com.example.businessmanager.ProductList.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comapny_response
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("unit_list")
    @Expose
    List<Comapany_list> comapany_list;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Comapany_list> getComapany_list() {
        return comapany_list;
    }
}
