package com.example.businessmanager.CheckOut.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businessmanager.Cart.Model.CartList;
import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.R;

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

    String payment_terms="Advance";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);

        presenter=new CheckOutPresenter(this);
        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");

        presenter.getCart(clientModel.getMobile());

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
                Toast.makeText(CheckOutActivity.this, "Ruk Jao Chcha", Toast.LENGTH_SHORT).show();
            }
        });
        name.setText(clientModel.getName());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void showCart(CartResponse body)
    {
        list=body.getList();
        showCost();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkOutAdapter=new CheckOutAdapter(this,list);
        recyclerView.setAdapter(checkOutAdapter);
    }

    private void showCost()
    {
        Double cost=0.0;
        for(int i=0;i<list.size();i++)
        {
            Double costitem=Double.parseDouble(list.get(i).getCost())*Double.parseDouble(list.get(i).getUnit());
            cost+=costitem;
        }
        cost=cost+(cost*0.18);
        total.setText(""+cost);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
