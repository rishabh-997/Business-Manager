package com.example.businessmanager.Cart.MVP;

import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.Model_common.UnitResponse;

public class CartContract
{
    interface view
    {
        void showToast(String message);
        void showCart(CartResponse body);

        void clear();

        void setList(UnitResponse body);

        void delete(int pos,String response);
    }
    interface presenter
    {
        void updateCart(String mobile,String pid,String size,String unit,String cost);
        void deleteCart(String pid,int position);
        void deleteAll(String mobile,String company);
        void getCart(String mobile,String company);

        void getUnit();
    }
}
