package com.example.businessmanager.Enquire.MVP.Pending;

import com.example.businessmanager.Enquire.Mode.EnquiryResponse;
import com.example.businessmanager.HomeActivity.model.ResponseClient;

public class PendingContract
{
    interface view{

        void toast(String message);

        void showList(EnquiryResponse body);

        void updated();

        void showDashboard(ResponseClient body);
    }
    interface presenter{

        void getList(String accessToken, String pending);

        void update(String accessToken, String id);

        void search(String mobile);
    }
}
