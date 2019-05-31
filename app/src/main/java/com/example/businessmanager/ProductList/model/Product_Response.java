package com.example.businessmanager.ProductList.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product_Response
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("user_list")
    @Expose
    List<ProductList> productListList;

    public Product_Response(Boolean success, String message, List<ProductList> productListList) {
        this.success = success;
        this.message = message;
        this.productListList = productListList;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<ProductList> getProductListList() {
        return productListList;
    }
}
