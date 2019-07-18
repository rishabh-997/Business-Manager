package com.example.businessmanager.Enquire.MVP.Contacted;

import com.example.businessmanager.Enquire.Mode.EnquiryResponse;
import com.example.businessmanager.Utilities.Network.ClientAPI;
import com.example.businessmanager.Utilities.Network.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactedPresenter implements ContactedContract.presenter
{
    ContactedContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public ContactedPresenter(ContactedContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getList(String accessToken, String contacted) {
        clientAPI.getProductEnquiry(accessToken,contacted).enqueue(new Callback<EnquiryResponse>() {
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
}
