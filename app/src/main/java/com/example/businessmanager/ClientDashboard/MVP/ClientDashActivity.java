package com.example.businessmanager.ClientDashboard.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.businessmanager.ClientDetails.MVP.ClientDetailActivity;
import com.example.businessmanager.History.MVP.HistoryActivity;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.ProductList.MVP.ProductListActivity;
import com.example.businessmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientDashActivity extends AppCompatActivity implements ClientDashContract.view
{
    @BindView(R.id.dashboard_place_order)
    Button place_order;
    @BindView(R.id.dashboard_clientdetail)
    Button details;
    @BindView(R.id.dashboard_history)
    Button history;

    ClientDashContract.presenter presenter;
    ClientModel clientModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        presenter=new ClientDashPresenter(this);
        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(ClientDashActivity.this, ProductListActivity.class);
                intent.putExtra("client_details", clientModel);
                startActivity(intent);
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClientDashActivity.this, ClientDetailActivity.class);
                intent.putExtra("client_details", clientModel);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClientDashActivity.this, HistoryActivity.class);
                intent.putExtra("client_details", clientModel);
                startActivity(intent);
            }
        });
    }
}
