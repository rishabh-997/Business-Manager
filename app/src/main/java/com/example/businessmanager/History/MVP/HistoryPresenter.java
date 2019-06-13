package com.example.businessmanager.History.MVP;

public class HistoryPresenter implements HistoryContract.presenter
{
    HistoryContract.view mvpview;

    public HistoryPresenter(HistoryContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
