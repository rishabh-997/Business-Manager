package com.example.businessmanager.Login.MVP;

import com.example.businessmanager.Login.Model.LogInResponse;

public class LogInContract {
    interface view
    {
        void showToast(String message);

        void enterApp(LogInResponse body);
    }
    interface presenter
    {
        void requestlogin(String mob,String pass,String fcm);
    }
}
