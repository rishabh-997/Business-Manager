package com.example.businessmanager.ProductList.MVP;

import com.example.businessmanager.ProductList.model.Product_Response;

public class ProductListContract
{
    interface view
    {
        void showtaost(String s);

        void showdata(Product_Response body);
    }
    interface presenter
    {

        void getList();
        void addCart(String mobile,String pid, String size, String cost, String unit);
    }
}
