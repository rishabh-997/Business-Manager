package com.example.businessmanager.ClientDetails.MVP;

import com.example.businessmanager.Utilities.Network.ClientAPI;
import com.example.businessmanager.Utilities.Network.Utils;

public class ClientDetailPresenter implements ClientDetailContract.presenter
{
    ClientDetailContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public ClientDetailPresenter(ClientDetailContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
