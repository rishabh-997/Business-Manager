package com.example.businessmanager.CheckOut.MVP;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businessmanager.Cart.MVP.CartActivity;
import com.example.businessmanager.Cart.Model.CartList;
import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.ClientDashboard.MVP.ClientDashActivity;
import com.example.businessmanager.History.MVP.HistoryActivity;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.R;
import com.example.businessmanager.Utilities.SharedPref;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckOutActivity extends AppCompatActivity implements CheckOutContract.view
{
    CheckOutContract.presenter presenter;
    ClientModel clientModel;
    List<CartList> list=new ArrayList<>();
    SharedPref sharedPref;

    @BindView(R.id.checkout_companyname)
    TextView company;
    @BindView(R.id.checkout_quantity)
    TextView quantity;
    @BindView(R.id.checkout_price)
    TextView checkout_price;
    @BindView(R.id.checkout_cgst)
    TextView checkout_cgst;
    @BindView(R.id.checkout_sgst)
    TextView checkout_sfst;
    @BindView(R.id.checkout_pricetaxable)
    TextView checkout_pricetotal;
    @BindView(R.id.checkout_clientname)
    TextView clientname;
    @BindView(R.id.checkout_clientbillto)
    TextView billto;
    @BindView(R.id.checkout_shipto)
    TextView shipto;
    @BindView(R.id.checkout_contact)
    TextView contact;
    @BindView(R.id.checkout_spinner)
    Spinner spinners;
    @BindView(R.id.checkout_comment)
    EditText comments;
    @BindView(R.id.checkout_place)
    TextView placeorder;
    @BindView(R.id.checkout__image_empty)
    ImageView imageempty;
    @BindView(R.id.checkout_image_placed)
    ImageView imageplaced;
    @BindView(R.id.checkout_linearlayout)
    LinearLayout linearLayout;
    @BindView(R.id.checkout_bar)
    ProgressBar progressBar;

    String payment_terms="Advance";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);
        sharedPref=new SharedPref(this);

        presenter=new CheckOutPresenter(this);
        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");

        presenter.getCart(clientModel.getMobile(),sharedPref.getCompany());

        final String[] spinnerValueHoldValue = {"Advance", "15 Days", "30 Days", "60 Days","90 Days"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CheckOutActivity.this, android.R.layout.simple_list_item_1, spinnerValueHoldValue);
        spinners.setAdapter(adapter);
        spinners.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payment_terms=spinners.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imageempty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(CheckOutActivity.this, ClientDashActivity.class);
                intent.putExtra("client_details", clientModel);
                startActivity(intent);
            }
        });

        imageplaced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(CheckOutActivity.this, HistoryActivity.class);
                intent.putExtra("client_details", clientModel);
                startActivity(intent);
            }
        });

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size()==0)
                    Toast.makeText(CheckOutActivity.this, "Please Add Items In The Cart Or Try After Some Time", Toast.LENGTH_LONG).show();
                else
                    placeorder();
            }
        });


        company.setText(sharedPref.getCompany());
        clientname.setText(clientModel.getName());
        billto.setText(clientModel.getBillTo());
        shipto.setText(clientModel.getShipTo());
        contact.setText(clientModel.getMobile());
    }

    private void placeorder()
    {
        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Place Order");
        alertDialog.setMessage("Have you cross checked the details ?");
        alertDialog.setCancelable(false);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                progressBar.setVisibility(View.VISIBLE);
                presenter.placeorder("Client",clientModel.getName(),clientModel.getMobile(),payment_terms,comments.getText().toString(),sharedPref.getCompany(),sharedPref.getAccessToken());
                alertDialog.cancel();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showCart(CartResponse body)
    {
        list.clear();
        list=body.getList();
        if(list.size()==0)
        {
            placeorder.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            imageplaced.setVisibility(View.GONE);
            imageempty.setVisibility(View.VISIBLE);
        }
        else {
            imageempty.setVisibility(View.GONE);
            imageplaced.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            placeorder.setVisibility(View.VISIBLE);
            showCost();
        }
    }

    @Override
    public void close()
    {
        placeorder.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        imageplaced.setVisibility(View.VISIBLE);
        imageempty.setVisibility(View.GONE);
    }

    @SuppressLint("SetTextI18n")
    private void showCost()
    {
        Double cost=0.0,cgst=0.0,sgst=0.0,total=0.0;
        int quan=list.size();

        for(int i=0;i<list.size();i++)
        {
            cost=cost+Double.parseDouble(list.get(i).getTotal_cost());
            cgst=cgst+Double.parseDouble(list.get(i).getCgst());
            sgst=sgst+Double.parseDouble(list.get(i).getSgst());
            total=total+Double.parseDouble(list.get(i).getTotal_cost_tax());
        }

        quantity.setText(quan+"");
        checkout_price.setText(cost+"");
        checkout_cgst.setText(cgst+"");
        checkout_sfst.setText(sgst+"");
        checkout_pricetotal.setText(total+"");
    }

}
