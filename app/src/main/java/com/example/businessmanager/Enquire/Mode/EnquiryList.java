package com.example.businessmanager.Enquire.Mode;

import com.google.gson.annotations.SerializedName;

public class EnquiryList
{
    @SerializedName("id")
    String id;
    @SerializedName("Name")
    String name;
    @SerializedName("Mobile")
    String mobile;
    @SerializedName("Enquiry_Status")
    String status;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getStatus() {
        return status;
    }
}
