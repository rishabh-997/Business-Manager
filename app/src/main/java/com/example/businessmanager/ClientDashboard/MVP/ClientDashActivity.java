package com.example.businessmanager.ClientDashboard.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.businessmanager.ClientDetails.MVP.ClientDetailActivity;
import com.example.businessmanager.History.MVP.HistoryActivity;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.ProductList.MVP.ProductListActivity;
import com.example.businessmanager.ProductList.model.Comapany_list;
import com.example.businessmanager.ProductList.model.Comapny_response;
import com.example.businessmanager.R;
import com.example.businessmanager.Utilities.SharedPref;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.spinner_companyname)
    Spinner spinner_company;

    ClientDashContract.presenter presenter;
    ClientModel clientModel;
    List<Comapany_list> comapany_list=new ArrayList<>();
    String CompanyNameFull="",CompanyNameShort="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        presenter=new ClientDashPresenter(this);
        presenter.getCompany();
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

    @Override
    public void showCompanies(Comapny_response body) {
        comapany_list=body.getComapany_list();

        String[] companylistfinal=new String[comapany_list.size()];
        for(int i=0;i<comapany_list.size();i++){
            companylistfinal[i]=comapany_list.get(i).getComapanyfull();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(ClientDashActivity.this, android.R.layout.simple_list_item_1, companylistfinal);
        spinner_company.setAdapter(adapter);
        spinner_company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                CompanyNameFull=spinner_company.getSelectedItem().toString();
                setCategory(spinner_company.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void setCategory(String Company)
    {
        for(int i=0;i<comapany_list.size();i++)
        {
            if(Company.equals(comapany_list.get(i).getComapanyfull())) {
                CompanyNameShort = comapany_list.get(i).getCompanyshort();
                break;
            }
        }

        SharedPref sharedPref=new SharedPref(this);
        sharedPref.setCompany(CompanyNameShort);
    }

    @Override
    public void showtaost(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
