package com.example.businessmanager.History.MVP;

import com.example.businessmanager.History.Model.HistoryDetailResponse;
import com.example.businessmanager.History.Model.HistoryResponse;

public class HistoryContract
{
    interface view
    {
        void showToast(String message);

        void showList(HistoryResponse body);

        void showDetails(HistoryDetailResponse body,String id);
    }
    interface presenter
    {

        void getHistory(String mobile, String client);

        void getDetails(String orderid);
    }
}
