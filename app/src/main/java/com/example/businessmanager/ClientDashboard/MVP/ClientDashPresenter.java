package com.example.businessmanager.ClientDashboard.MVP;

import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.ProductList.model.Comapny_response;
import com.example.businessmanager.Utilities.Network.ClientAPI;
import com.example.businessmanager.Utilities.Network.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientDashPresenter implements ClientDashContract.presenter
{
    ClientDashContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public ClientDashPresenter(ClientDashContract.view mvpview) {
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
}
