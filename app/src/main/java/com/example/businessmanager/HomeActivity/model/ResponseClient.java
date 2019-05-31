package com.example.businessmanager.HomeActivity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseClient
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
    List<ClientModel> list;

    public ResponseClient(String message, Boolean result, List<ClientModel> list) {
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

    public List<ClientModel> getList() {
        return list;
    }
}
