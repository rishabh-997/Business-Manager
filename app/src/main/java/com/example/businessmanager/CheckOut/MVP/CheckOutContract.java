package com.example.businessmanager.CheckOut.MVP;

import com.example.businessmanager.Cart.Model.CartResponse;

public class CheckOutContract
{
    interface view
    {
        void showToast(String message);
        void showCart(CartResponse body);

        void close();
    }
    interface presenter
    {

        void getCart(String mobile,String company);

        void placeorder(String client, String name, String mobile, String payment_terms, String comment,String company,String token);
    }
}
