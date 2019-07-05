package com.example.businessmanager.ClientRegistration.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.businessmanager.HomeActivity.MVP.HoomeActivity;
import com.example.businessmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientRegActivity extends AppCompatActivity implements ClientRegContract.view
{
    ClientRegContract.presenter presenter;

    @BindView(R.id.client_name)
    EditText client_name;
    @BindView(R.id.client_mobile)
    EditText client_mobile;
    @BindView(R.id.client_phone)
    EditText client_phone;
    @BindView(R.id.client_email)
    EditText client_email;
    @BindView(R.id.client_panno)
    EditText client_panno;
    @BindView(R.id.client_billto)
    EditText client_billto;
    @BindView(R.id.client_shipto)
    EditText client_shipto;
    @BindView(R.id.client_gst)
    EditText client_gst;
    @BindView(R.id.client_bankname)
    EditText client_bankname;
    @BindView(R.id.client_ifsc)
    EditText client_ifsc;
    @BindView(R.id.client_isc)
    EditText client_isc;
    @BindView(R.id.client_bankphone)
    EditText client_bankphone;
    @BindView(R.id.client_accno)
    EditText client_accno;
    @BindView(R.id.client_msmeno)
    EditText client_msmeno;
    @BindView(R.id.client_preferred_transport)
    EditText client_preferred;
    @BindView(R.id.client_checkclient)
    CheckBox client;
    @BindView(R.id.client_checksupplier)
    CheckBox supplier;
    @BindView(R.id.client_checktransport)
    CheckBox transport;
    @BindView(R.id.client_submit)
    Button submit;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    String name="",mobile="",phone="phone",email="email",pan="pam",billto="",shipto="",gst="gst";
    String bankname="name",ifsc="ifsc",isc="isc",bankphone="bankpone", accno="accno",msmenumber="msme",preferred="preffered",regtype="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientreg);

        ButterKnife.bind(this);
        presenter=new ClientRegPresenter(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                name=client_name.getText().toString().trim();
                mobile=client_mobile.getText().toString().trim();
                phone=client_phone.getText().toString().trim();
                email=client_email.getText().toString().trim();
                pan=client_panno.getText().toString().trim();
                billto=client_billto.getText().toString().trim();
                shipto=client_shipto.getText().toString().trim();
                gst=client_gst.getText().toString().trim();
                bankname=client_bankname.getText().toString().trim();
                ifsc=client_ifsc.getText().toString().trim();
                isc=client_isc.getText().toString().trim();
                bankphone=client_bankphone.getText().toString().trim();
                accno=client_accno.getText().toString().trim();
                msmenumber=client_msmeno.getText().toString().trim();
                preferred=client_preferred.getText().toString().trim();

                if(client.isChecked())
                    regtype="client";
                else if(supplier.isChecked())
                    regtype="supplier";
                else
                    regtype="transport";


                if(client_name.getText().toString().trim().isEmpty())
                    Toast.makeText(ClientRegActivity.this, "Name Cannot be empty", Toast.LENGTH_SHORT).show();
                if(client_mobile.getText().toString().trim().isEmpty())
                    Toast.makeText(ClientRegActivity.this, "Mobile Cannot be empty", Toast.LENGTH_SHORT).show();
                if(client_billto.getText().toString().trim().isEmpty())
                    Toast.makeText(ClientRegActivity.this, "BillTo Cannot be empty", Toast.LENGTH_SHORT).show();
                if(client_shipto.getText().toString().trim().isEmpty())
                    Toast.makeText(ClientRegActivity.this, "ShipTo Cannot be empty", Toast.LENGTH_SHORT).show();
                if(regtype=="")
                    Toast.makeText(ClientRegActivity.this, "Select Type", Toast.LENGTH_SHORT).show();
                if((!client_name.getText().toString().trim().isEmpty()) && (!client_mobile.getText().toString().trim().isEmpty())
                    && (!client_billto.getText().toString().trim().isEmpty()) && (!client_shipto.getText().toString().trim().isEmpty()))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    presenter.savepost(name,mobile,phone,email,pan,billto,shipto,gst,bankname,ifsc,isc,bankphone,accno,msmenumber,preferred,regtype);
                }
            }
        });
    }

    @Override
    public void showToast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotohome()
    {
        progressBar.setVisibility(View.GONE);
        startActivity(new Intent(this, HoomeActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        gotohome();
    }
}
