package com.example.businessmanager.Enquire.Mode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EnquiryResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("Enquiry_List")
    @Expose
    List<EnquiryList> enquiry_List;


    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<EnquiryList> getEnquiry_List() {
        return enquiry_List;
    }
}
