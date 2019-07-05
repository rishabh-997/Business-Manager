package com.example.businessmanager.ClientRegistration.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBody
{
    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("message")
    @Expose
    private String message;

    public ResponseBody(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean Success() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
