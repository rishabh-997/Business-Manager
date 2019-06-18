package com.example.businessmanager.ClientDashboard.MVP;

import com.example.businessmanager.ProductList.model.Comapny_response;

public class ClientDashContract
{
    interface view
    {

        void showCompanies(Comapny_response body);

        void showtaost(String message);
    }
    interface presenter
    {

        void getCompany();
    }
}
