package com.example.businessmanager.Enquire.MVP.Pending;

import com.example.businessmanager.Enquire.Mode.ChangeStatusResponse;
import com.example.businessmanager.Enquire.Mode.EnquiryResponse;
import com.example.businessmanager.HomeActivity.model.ResponseClient;
import com.example.businessmanager.Utilities.Network.ClientAPI;
import com.example.businessmanager.Utilities.Network.Utils;
import com.example.businessmanager.Utilities.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingPresenter implements PendingContract.presenter
{
    PendingContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public PendingPresenter(PendingContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getList(String accessToken, String pending) {
        clientAPI.getProductEnquiry(accessToken,pending).enqueue(new Callback<EnquiryResponse>() {
            @Override
            public void onResponse(Call<EnquiryResponse> call, Response<EnquiryResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("success"))
                    {
                        mvpview.showList(response.body());
                    }
                    else
                    {
                        mvpview.toast(response.body().getMessage());
                    }
                }
                else
                    mvpview.toast(response.message());
            }

            @Override
            public void onFailure(Call<EnquiryResponse> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }

    @Override
    public void update(String accessToken, String id) {
        clientAPI.changeProductEnquiry(accessToken, id).enqueue(new Callback<ChangeStatusResponse>() {
            @Override
            public void onResponse(Call<ChangeStatusResponse> call, Response<ChangeStatusResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Enquiry status Changed"))
                        mvpview.updated();
                    else
                        mvpview.toast(response.body().getMessage());
                }
                else
                    mvpview.toast(response.message());
            }

            @Override
            public void onFailure(Call<ChangeStatusResponse> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }

    @Override
    public void search(String mobile) {
        clientAPI.search("0",mobile).enqueue(new Callback<ResponseClient>() {
            @Override
            public void onResponse(Call<ResponseClient> call, Response<ResponseClient> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                        mvpview.showDashboard(response.body());
                    else
                        mvpview.toast(response.body().getMessage());
                }
                else
                    mvpview.toast(response.message());
            }

            @Override
            public void onFailure(Call<ResponseClient> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }
}
