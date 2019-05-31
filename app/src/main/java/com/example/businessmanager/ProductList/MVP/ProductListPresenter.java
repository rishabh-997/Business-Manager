package com.example.businessmanager.ProductList.MVP;

import com.example.businessmanager.HomeActivity.model.ClientModel;
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
}
