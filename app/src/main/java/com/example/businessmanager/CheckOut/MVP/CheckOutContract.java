package com.example.businessmanager.CheckOut.MVP;

import com.example.businessmanager.Cart.Model.CartResponse;

public class CheckOutContract
{
    interface view
    {

        void showCart(CartResponse body);
    }
    interface presenter
    {

        void getCart(String mobile);
    }
}
