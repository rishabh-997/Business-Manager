package com.example.businessmanager.HomeActivity.MVP;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.businessmanager.ClientRegistration.MVP.ClientRegActivity;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.HomeActivity.model.ResponseClient;
import com.example.businessmanager.Login.MVP.LogInActivity;
import com.example.businessmanager.R;
import com.example.businessmanager.Utilities.SharedPref;

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
    List<ClientModel> list=new ArrayList<>();

    @BindView(R.id.home_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.home_search)
    EditText search;

    @BindView(R.id.home_search_button)
    ImageView button;

    @BindView(R.id.home_bar)
    ProgressBar progressBar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        final SharedPref sharedPref=new SharedPref(this);
        Toast.makeText(this, "Access Level is "+sharedPref.getKeyAccessLevel(), Toast.LENGTH_SHORT).show();

        dl=findViewById(R.id.drawer_layout);
        abdt=new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navview=findViewById(R.id.nav_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar.setVisibility(View.VISIBLE);
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
                    startActivity(new Intent(HoomeActivity.this, ClientRegActivity.class));
                }
                else if(id==R.id.nav_logout)
                {
                    sharedPref.setAccessToken("");
                    sharedPref.setKeyAccessLevel("");
                    finish();
                    startActivity(new Intent(HoomeActivity.this, LogInActivity.class));
                }
                return true;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    @Override
    public void showToast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(ResponseClient body)
    {
        progressBar.setVisibility(View.GONE);
        list.clear();
        list=body.getList();
        Collections.sort(list, ClientModel.NameCompare);
        adapter=new Home_Adapter(list,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }


    private void search()
    {
        progressBar.setVisibility(View.VISIBLE);
        if(search.getText().toString().isEmpty())
        {
            progressBar.setVisibility(View.GONE);
            search.setError("Enter Query");
            search.requestFocus();
        }
        else
        {
            presenter.search(search.getText().toString());
        }
    }
}
