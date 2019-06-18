package com.example.businessmanager.CheckOut.MVP;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businessmanager.Cart.MVP.CartActivity;
import com.example.businessmanager.Cart.Model.CartList;
import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.ClientDashboard.MVP.ClientDashActivity;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.R;
import com.example.businessmanager.Utilities.SharedPref;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckOutActivity extends AppCompatActivity implements CheckOutContract.view
{
    CheckOutContract.presenter presenter;
    ClientModel clientModel;
    List<CartList> list=new ArrayList<>();
    CheckOutAdapter checkOutAdapter;
    SharedPref sharedPref;

    @BindView(R.id.checkout_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.checkout_name)
    TextView name;
    @BindView(R.id.checkout_total)
    TextView total;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.checkout_edit)
    TextView edit;
    @BindView(R.id.checkout_place)
    TextView place;
    @BindView(R.id.commentbox)
    EditText comment;

    String payment_terms="Advance";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);
        sharedPref=new SharedPref(this);

        presenter=new CheckOutPresenter(this);
        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        presenter.getCart(clientModel.getMobile(),sharedPref.getCompany());

        final String[] spinnerValueHoldValue = {"Advance", "15 Days", "30 Days", "60 Days","90 Days"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CheckOutActivity.this, android.R.layout.simple_list_item_1, spinnerValueHoldValue);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payment_terms=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size()==0)
                    Toast.makeText(CheckOutActivity.this, "Please Add Items In The Cart Or Try After Some Time", Toast.LENGTH_LONG).show();
                else
                    placeorder();
            }
        });
        name.setText(clientModel.getName());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(CheckOutActivity.this, CartActivity.class);
                intent.putExtra("client_details", clientModel);
                startActivity(intent);
            }
        });
    }

    private void placeorder()
    {
        presenter.placeorder("Client",clientModel.getName(),clientModel.getMobile(),payment_terms,comment.getText().toString(),sharedPref.getCompany());
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
        showCost();
        checkOutAdapter=new CheckOutAdapter(this,list);
        recyclerView.setAdapter(checkOutAdapter);
    }

    @Override
    public void close() {
        finish();
        startActivity(new Intent(this,ClientDashActivity.class));
    }

    @SuppressLint("SetTextI18n")
    private void showCost()
    {
        Double cost=0.0;
        for(int i=0;i<list.size();i++)
        {
            Double costitem=Double.parseDouble(list.get(i).getTotal_cost_tax());
            cost+=costitem;
        }
        total.setText(""+cost);
    }

}
