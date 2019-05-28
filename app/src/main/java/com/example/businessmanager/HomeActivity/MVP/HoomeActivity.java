package com.example.businessmanager.HomeActivity.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.businessmanager.ClientRegistration.MVP.ClientRegActivity;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.HomeActivity.model.ResponseClient;
import com.example.businessmanager.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HoomeActivity extends AppCompatActivity implements HomeActContract.view
{
    DrawerLayout dl;
    ActionBarDrawerToggle abdt;

    HomeActContract.presenter presenter;
    Home_Adapter adapter;
    List<ClientModel> list;

    @BindView(R.id.home_recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        dl=findViewById(R.id.drawer_layout);
        abdt=new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navview=findViewById(R.id.nav_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter=new HomeActPresenter(this);
        presenter.getList("0");

        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int id=item.getItemId();
                if(id==R.id.nav_delete)
                {
                    Toast.makeText(HoomeActivity.this, "Inactive", Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.nav_client)
                {
                    finish();
                    startActivity(new Intent(HoomeActivity.this, ClientRegActivity.class));
                }
                return true;
            }
        });
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(ResponseClient body) {
        list=new ArrayList<>();
        list=body.getList();
        Collections.sort(list, ClientModel.NameCompare);
        adapter=new Home_Adapter(list,this);
        recyclerView.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
