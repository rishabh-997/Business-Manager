package com.example.businessmanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.businessmanager.HomeActivity.MVP.HoomeActivity;
import com.example.businessmanager.Login.MVP.LogInActivity;
import com.example.businessmanager.Utilities.MyApplication;
import com.example.businessmanager.Utilities.SharedPref;

public class SplachActivity extends AppCompatActivity
{
    android.os.Handler Handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        SharedPref sharedPref=new SharedPref(this);

        if(sharedPref.getAccessToken()=="")
        {
            Handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    Intent intent=new Intent(SplachActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
            },1500);
        }
        else
        {
            Handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    Intent intent=new Intent(SplachActivity.this, HoomeActivity.class);
                    startActivity(intent);
                }
            },1500);
        }
    }
}
