package com.example.businessmanager.Cart.MVP;

public class CartContract
{
    interface view
    {
        void showToast(String message);
    }
    interface presenter
    {
        void updateCart(String mobile,String pid,String size,String unit,String cost);
        void deleteCart(String mobile,String pid);
        void deleteAll(String mobile);
        void getCart(String mobile);
    }
}
