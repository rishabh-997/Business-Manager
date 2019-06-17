package com.example.businessmanager.ClientRegistration.MVP;

import com.example.businessmanager.Utilities.Network.ClientAPI;
import com.example.businessmanager.Utilities.Network.Utils;
import com.example.businessmanager.ClientRegistration.model.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientRegPresenter implements ClientRegContract.presenter
{
    private ClientRegContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    ClientRegPresenter(ClientRegContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void savepost(String name, String mobile, String phone, String email, String pan, String billto, String shipto, String gst, String bankname, String ifsc, String isc, String bankphone, String accno, String msmenumber, String preferred, final String regtype) {
        clientAPI.createClient(name,mobile,phone,email,pan,billto,shipto,gst,bankname,ifsc,isc,bankphone,accno,msmenumber,preferred,regtype).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    mvpview.showToast("Registered Successfully");
                    mvpview.gotohome();
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
