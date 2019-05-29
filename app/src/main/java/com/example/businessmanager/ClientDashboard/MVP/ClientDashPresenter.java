package com.example.businessmanager.ClientDashboard.MVP;

public class ClientDashPresenter implements ClientDashContract.presenter
{
    ClientDashContract.view mvpview;

    public ClientDashPresenter(ClientDashContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
