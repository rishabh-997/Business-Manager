package com.example.businessmanager.Enquire.MVP.Contacted;

import com.example.businessmanager.Enquire.Mode.EnquiryResponse;

public class ContactedContract
{
    interface view{
        void toast(String message);

        void showList(EnquiryResponse body);
    }
    interface presenter{

        void getList(String accessToken, String contacted);
    }
}
