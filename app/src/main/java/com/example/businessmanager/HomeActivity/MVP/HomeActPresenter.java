package com.example.businessmanager.HomeActivity.MVP;

import com.example.businessmanager.HomeActivity.model.ResponseClient;
import com.example.businessmanager.Utilities.ClientAPI;
import com.example.businessmanager.Utilities.Utils;

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
