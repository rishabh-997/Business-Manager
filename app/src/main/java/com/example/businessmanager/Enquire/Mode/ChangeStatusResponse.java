package com.example.businessmanager.Enquire.Mode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChangeStatusResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
