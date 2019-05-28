package com.example.businessmanager.HomeActivity.MVP;

import com.example.businessmanager.HomeActivity.model.ResponseClient;

import java.util.ArrayList;
import java.util.List;

public class HomeActContract
{
    interface view
    {
        void showToast(String message);

        void showData(ResponseClient body);
    }
    interface presenter
    {

        void getList(String s);
    }
}
