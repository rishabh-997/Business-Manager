package com.example.businessmanager.Cart.MVP;

import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.Cart.Model.CartResponse_CUD;
import com.example.businessmanager.Model_common.UnitResponse;
import com.example.businessmanager.Utilities.Network.ClientAPI;
import com.example.businessmanager.Utilities.Network.Utils;

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
                mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<CartResponse_CUD> call, Throwable t) {

            }
        });
    }

    @Override
    public void deleteCart(String mobile, String pid, final int pos)
    {
        clientAPI.deleteProduct(mobile,pid).enqueue(new Callback<CartResponse_CUD>() {
            @Override
            public void onResponse(Call<CartResponse_CUD> call, Response<CartResponse_CUD> response)
            {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Successful"))
                    {
                        mvpview.delete(pos,response.message());
                    }
                    else
                    {
                        mvpview.showToast(response.body().getMessage());
                    }
                }
                else
                {
                    mvpview.showToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<CartResponse_CUD> call, Throwable t) {

            }
        });
    }

    @Override
    public void deleteAll(String mobile,String company) {
        clientAPI.deleteallProduct(mobile,company).enqueue(new Callback<CartResponse_CUD>() {
            @Override
            public void onResponse(Call<CartResponse_CUD> call, Response<CartResponse_CUD> response)
            {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Successful"))
                    {
                        mvpview.showToast("Deleted Everything");
                        mvpview.clear();
                    }
                    else
                    {
                        mvpview.showToast(response.body().getMessage());
                    }
                }
                else
                {
                    mvpview.showToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<CartResponse_CUD> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }

    @Override
    public void getCart(String mobile,String company)
    {
        clientAPI.getCart(mobile,company).enqueue(new Callback<CartResponse>() {
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
    public void getUnit() {
        clientAPI.getUnits("").enqueue(new Callback<UnitResponse>() {
            @Override
            public void onResponse(Call<UnitResponse> call, Response<UnitResponse> response) {
                mvpview.setList(response.body());
            }

            @Override
            public void onFailure(Call<UnitResponse> call, Throwable t) {
            }
        });
    }
}
