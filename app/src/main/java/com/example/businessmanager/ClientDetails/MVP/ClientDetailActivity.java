package com.example.businessmanager.ClientDetails.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientDetailActivity extends AppCompatActivity implements ClientDetailContract.view
{
    ClientDetailContract.presenter presenter;
    ClientModel clientModel;

    @BindView(R.id.client_details_name)
    TextView name;
    @BindView(R.id.client_details_mobile)
    TextView mobile;
    @BindView(R.id.client_details_phone)
    TextView phone;
    @BindView(R.id.client_details_email)
    TextView email;
    @BindView(R.id.client_details_pan)
    TextView pan;
    @BindView(R.id.client_details_billto)
    TextView billto;
    @BindView(R.id.client_details_shipto)
    TextView shipto;
    @BindView(R.id.client_details_gst)
    TextView gst;
    @BindView(R.id.client_details_bankname)
    TextView bankname;
    @BindView(R.id.client_details_ifsc)
    TextView ifsc;
    @BindView(R.id.client_details_isc)
    TextView isc;
    @BindView(R.id.client_details_bankphone)
    TextView bankphone;
    @BindView(R.id.client_details_accno)
    TextView accno;
    @BindView(R.id.client_details_msme)
    TextView msme;
    @BindView(R.id.client_details_transport)
    TextView transport;

    String blank="Not Available";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);
        ButterKnife.bind(this);

        presenter=new ClientDetailPresenter(this);
        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");

        name.setText(clientModel.getName());
        mobile.setText(clientModel.getMobile());
        billto.setText(clientModel.getBillTo());
        shipto.setText(clientModel.getShipTo());

        phone.setText(clientModel.getPhone());
        email.setText(clientModel.getEmail());
        pan.setText(clientModel.getPan());
        gst.setText(clientModel.getGSTNo());
        bankname.setText(clientModel.getBankName());
        bankphone.setText(clientModel.getBankPhone());
        ifsc.setText(clientModel.getIFSCNo());
        isc.setText(clientModel.getISCCode());
        accno.setText(clientModel.getAccountNo());
        msme.setText(clientModel.getMSMENo());
        transport.setText(clientModel.getTransportNo());
    }
}
