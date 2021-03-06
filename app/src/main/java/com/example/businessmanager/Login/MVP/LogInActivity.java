package com.example.businessmanager.Login.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.businessmanager.HomeActivity.MVP.HoomeActivity;
import com.example.businessmanager.Login.Model.LogInResponse;
import com.example.businessmanager.R;
import com.example.businessmanager.Utilities.MyApplication;
import com.example.businessmanager.Utilities.SharedPref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity implements LogInContract.view
{
    LogInContract.presenter presenter;
    @BindView(R.id.login_login)
    Button login;
    @BindView(R.id.login_email)
    EditText mobile;
    @BindView(R.id.login_password)
    EditText password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        presenter=new LogInPresenter(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.requestlogin(mobile.getText().toString(),password.getText().toString(), MyApplication.getFcm());
            }
        });


    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enterApp(LogInResponse body) {
        SharedPref sharedPref=new SharedPref(this);
        sharedPref.setAccessToken(body.getAccess_token());
        sharedPref.setKeyAccessLevel(body.getAccess_level());

        Log.i("Access Token",sharedPref.getAccessToken());
        Log.i("Fcm",MyApplication.getFcm());

        finish();
        startActivity(new Intent(this,HoomeActivity.class));
    }
}
