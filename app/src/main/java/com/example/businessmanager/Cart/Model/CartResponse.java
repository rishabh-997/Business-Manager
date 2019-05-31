package com.example.businessmanager.Cart.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartResponse
{
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private Boolean result;

    @SerializedName("user_list")
    @Expose
    private
    List<CartList> list;

    public CartResponse(String message, Boolean result, List<CartList> list) {
        this.message = message;
        this.result = result;
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getResult() {
        return result;
    }

    public List<CartList> getList() {
        return list;
    }
}
