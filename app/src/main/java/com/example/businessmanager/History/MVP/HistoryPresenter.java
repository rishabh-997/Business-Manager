package com.example.businessmanager.History.MVP;

import com.example.businessmanager.History.Model.HistoryDetailResponse;
import com.example.businessmanager.History.Model.HistoryResponse;
import com.example.businessmanager.Utilities.ClientAPI;
import com.example.businessmanager.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPresenter implements HistoryContract.presenter
{
    HistoryContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public HistoryPresenter(HistoryContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getHistory(String mobile, String client)
    {
        clientAPI.getOrderHistory(mobile,client).enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showList(response.body());
                    }
                    else
                        mvpview.showToast(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }

    @Override
    public void getDetails(final String orderid) {
        clientAPI.getOrderDetailsHistory(orderid).enqueue(new Callback<HistoryDetailResponse>() {
            @Override
            public void onResponse(Call<HistoryDetailResponse> call, Response<HistoryDetailResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showDetails(response.body(),orderid);
                    }
                    else
                        mvpview.showToast(response.body().getMessage());
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<HistoryDetailResponse> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }
}
