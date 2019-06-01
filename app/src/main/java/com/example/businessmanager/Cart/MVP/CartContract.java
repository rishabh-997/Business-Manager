package com.example.businessmanager.Cart.MVP;

import com.example.businessmanager.Cart.Model.CartResponse;

public class CartContract
{
    interface view
    {
        void showToast(String message);
        void showCart(CartResponse body);
    }
    interface presenter
    {
        void updateCart(String mobile,String pid,String size,String unit,String cost);
        void deleteCart(String mobile,String pid);
        void deleteAll(String mobile);
        void getCart(String mobile);
    }
}
