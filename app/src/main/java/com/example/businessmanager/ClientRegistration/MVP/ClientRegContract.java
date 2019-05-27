package com.example.businessmanager.ClientRegistration.MVP;

public class ClientRegContract
{
    interface view
    {
        void showToast(String message);
    }
    interface presenter
    {
        void savepost(String name, String mobile, String phone, String email, String pan, String billto, String shipto, String gst, String bankname, String ifsc, String isc, String bankphone, String accno, String msmenumber, String preferred, String regtype);
    }
}
