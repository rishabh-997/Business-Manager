package com.example.businessmanager.ProductList.MVP;

import com.example.businessmanager.Cart.Model.CartResponse_CUD;
import com.example.businessmanager.Model_common.UnitResponse;
import com.example.businessmanager.ProductList.model.Comapny_response;
import com.example.businessmanager.ProductList.model.Product_Response;
import com.example.businessmanager.ProductList.model.SpecResponse;
import com.example.businessmanager.ProductList.model.SubCat_response;
import com.example.businessmanager.Utilities.Network.ClientAPI;
import com.example.businessmanager.Utilities.Network.Utils;

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
    public void getCompany() {
        clientAPI.getCompany("9935685103").enqueue(new Callback<Comapny_response>() {
            @Override
            public void onResponse(Call<Comapny_response> call, Response<Comapny_response> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showCompanies(response.body());
                    }
                    else
                        mvpview.showtaost(response.message());
                }
            }
            @Override
            public void onFailure(Call<Comapny_response> call, Throwable t) {

            }
        });
    }

    @Override
    public void getSubCategory(String company) {
        clientAPI.getSubCat(company).enqueue(new Callback<SubCat_response>() {
            @Override
            public void onResponse(Call<SubCat_response> call, Response<SubCat_response> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showSubCategories(response.body());
                    }
                    else
                        mvpview.showtaost(response.message());
                }
            }

            @Override
            public void onFailure(Call<SubCat_response> call, Throwable t) {

            }
        });
    }

    @Override
    public void getList(String subcat,String company) {
        clientAPI.getProductList("client",subcat,company).enqueue(new Callback<Product_Response>() {
            @Override
            public void onResponse(Call<Product_Response> call, Response<Product_Response> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showdata(response.body());
                    }
                    else
                        mvpview.showtaost(response.message());
                }
            }

            @Override
            public void onFailure(Call<Product_Response> call, Throwable t) {

            }
        });
    }

    @Override
    public void getSpecs(String name) {
        clientAPI.getSpecs(name).enqueue(new Callback<SpecResponse>() {
            @Override
            public void onResponse(Call<SpecResponse> call, Response<SpecResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showSpecs(response.body());
                    }
                    else
                        mvpview.showtaost(response.message());
                }
            }

            @Override
            public void onFailure(Call<SpecResponse> call, Throwable t) {
                mvpview.showtaost(t.getMessage());
            }
        });
    }

    @Override
    public void addCart(String mobile, String pid, String size, String cost, String unit, String nvm, String product_name) {
        clientAPI.addProduct(mobile, pid, unit, size,cost,nvm,product_name).enqueue(new Callback<CartResponse_CUD>() {
            @Override
            public void onResponse(Call<CartResponse_CUD> call, Response<CartResponse_CUD> response) {
                mvpview.showtaost(response.message());
                mvpview.eraseSheet();
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
