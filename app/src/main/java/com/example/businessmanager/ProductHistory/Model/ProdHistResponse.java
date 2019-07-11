package com.example.businessmanager.ProductHistory.Model;

import com.example.businessmanager.ProductList.model.ProductList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProdHistResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("detail_list")
    @Expose
    List<ProdHistList> productList;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<ProdHistList> getProductList() {
        return productList;
    }
}
