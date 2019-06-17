package com.example.businessmanager.CheckOut.MVP;

import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.CheckOut.Model.PlaceOrderResponse;
import com.example.businessmanager.Utilities.Network.ClientAPI;
import com.example.businessmanager.Utilities.Network.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutPresenter implements CheckOutContract.presenter
{
    CheckOutContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public CheckOutPresenter(CheckOutContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getCart(String mobile)
    {
        clientAPI.getCart(mobile).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if(response.isSuccessful())
                    mvpview.showCart(response.body());
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void placeorder(String client, String name, String mobile, String payment_terms,String comment) {
        clientAPI.placeorder(client,name,mobile,payment_terms,comment).enqueue(new Callback<PlaceOrderResponse>() {
            @Override
            public void onResponse(Call<PlaceOrderResponse> call, Response<PlaceOrderResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Successful"))
                    {
                        mvpview.showToast("Your Order Id is "+response.body().getOrderid());
                    }
                    else
                        mvpview.showToast("Error Occured ! Try Again");
                }
            }

            @Override
            public void onFailure(Call<PlaceOrderResponse> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }
}
