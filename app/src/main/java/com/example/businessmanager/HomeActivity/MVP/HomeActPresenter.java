package com.example.businessmanager.HomeActivity.MVP;

import com.example.businessmanager.HomeActivity.model.ResponseClient;
import com.example.businessmanager.Utilities.Network.ClientAPI;
import com.example.businessmanager.Utilities.Network.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActPresenter implements HomeActContract.presenter
{
    HomeActContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public HomeActPresenter(HomeActContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void search(String query)
    {
        clientAPI.search("0",query).enqueue(new Callback<ResponseClient>() {
            @Override
            public void onResponse(Call<ResponseClient> call, Response<ResponseClient> response)
            {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                        mvpview.showData(response.body());
                    else
                        mvpview.showToast(response.body().getMessage());
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<ResponseClient> call, Throwable t) {
                    mvpview.showToast(t.getMessage());
            }
        });
    }

    @Override
    public void getList(String s)
    {
        clientAPI.getClientList(s).enqueue(new Callback<ResponseClient>() {
            @Override
            public void onResponse(Call<ResponseClient> call, Response<ResponseClient> response) {
                if(response.isSuccessful()) {
                    mvpview.showData(response.body());
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<ResponseClient> call, Throwable t) {

            }
        });
    }
}
