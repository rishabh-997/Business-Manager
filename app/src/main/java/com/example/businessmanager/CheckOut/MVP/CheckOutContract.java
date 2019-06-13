package com.example.businessmanager.CheckOut.MVP;

import com.example.businessmanager.Cart.Model.CartResponse;

public class CheckOutContract
{
    interface view
    {
        void showToast(String message);
        void showCart(CartResponse body);
    }
    interface presenter
    {

        void getCart(String mobile);

        void placeorder(String client, String name, String mobile, String payment_terms);
    }
}
