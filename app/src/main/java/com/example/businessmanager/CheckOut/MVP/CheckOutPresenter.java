package com.example.businessmanager.CheckOut.MVP;

import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.Utilities.ClientAPI;
import com.example.businessmanager.Utilities.Utils;

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
}
