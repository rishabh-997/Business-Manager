package com.example.businessmanager.CheckOut.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceOrderResponse
{
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private Boolean result;
    @SerializedName("order_id")
    @Expose
    private String orderid;

    public String getMessage() {
        return message;
    }

    public Boolean getResult() {
        return result;
    }

    public String getOrderid() {
        return orderid;
    }
}
