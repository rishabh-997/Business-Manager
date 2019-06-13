package com.example.businessmanager.History.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.R;

public class HistoryActivity extends AppCompatActivity implements HistoryContract.view
{
    HistoryContract.presenter presenter;
    ClientModel clientModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        presenter=new HistoryPresenter(this);
        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");
    }
}
