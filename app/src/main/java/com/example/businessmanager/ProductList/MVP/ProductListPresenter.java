package com.example.businessmanager.ProductList.MVP;

import com.example.businessmanager.Cart.Model.CartResponse_CUD;
import com.example.businessmanager.Model_common.UnitResponse;
import com.example.businessmanager.ProductList.model.Product_Response;
import com.example.businessmanager.Utilities.ClientAPI;
import com.example.businessmanager.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListPresenter implements ProductListContract.presenter
{
    ProductListContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public ProductListPresenter(ProductListContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getList() {
        clientAPI.getProductList("client").enqueue(new Callback<Product_Response>() {
            @Override
            public void onResponse(Call<Product_Response> call, Response<Product_Response> response) {
                if(response.isSuccessful())
                    mvpview.showdata(response.body());
                else
                    mvpview.showtaost(response.message());
            }

            @Override
            public void onFailure(Call<Product_Response> call, Throwable t) {

            }
        });
    }

    @Override
    public void addCart(String mobile, String pid, String size, String cost, String unit) {
        clientAPI.addProduct(mobile, pid, unit, size,cost).enqueue(new Callback<CartResponse_CUD>() {
            @Override
            public void onResponse(Call<CartResponse_CUD> call, Response<CartResponse_CUD> response) {
                mvpview.showtaost(response.message());
            }

            @Override
            public void onFailure(Call<CartResponse_CUD> call, Throwable t) {

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
                mvpview.showtaost(t.getMessage());
            }
        });
    }
}
