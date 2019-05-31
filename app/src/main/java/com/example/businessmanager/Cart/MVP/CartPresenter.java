package com.example.businessmanager.Cart.MVP;

import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.Cart.Model.CartResponse_CUD;
import com.example.businessmanager.Utilities.ClientAPI;
import com.example.businessmanager.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter implements CartContract.presenter
{
    CartContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public CartPresenter(CartContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void updateCart(String mobile, String pid, String size, String unit, String cost) {
        clientAPI.updateProduct(mobile,pid,size,unit,cost).enqueue(new Callback<CartResponse_CUD>() {
            @Override
            public void onResponse(Call<CartResponse_CUD> call, Response<CartResponse_CUD> response) {

            }

            @Override
            public void onFailure(Call<CartResponse_CUD> call, Throwable t) {

            }
        });
    }

    @Override
    public void deleteCart(String mobile, String pid)
    {
        clientAPI.deleteProduct(mobile,pid).enqueue(new Callback<CartResponse_CUD>() {
            @Override
            public void onResponse(Call<CartResponse_CUD> call, Response<CartResponse_CUD> response) {

            }

            @Override
            public void onFailure(Call<CartResponse_CUD> call, Throwable t) {

            }
        });
    }

    @Override
    public void deleteAll(String mobile) {
        clientAPI.deleteallProduct(mobile).enqueue(new Callback<CartResponse_CUD>() {
            @Override
            public void onResponse(Call<CartResponse_CUD> call, Response<CartResponse_CUD> response) {

            }

            @Override
            public void onFailure(Call<CartResponse_CUD> call, Throwable t) {

            }
        });
    }

    @Override
    public void getCart(String mobile)
    {
        clientAPI.getCart(mobile).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {

            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {

            }
        });
    }
}
