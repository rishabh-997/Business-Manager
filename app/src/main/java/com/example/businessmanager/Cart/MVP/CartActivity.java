package com.example.businessmanager.Cart.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.businessmanager.Cart.Model.CartList;
import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements CartContract.view
{
    ClientModel clientModel;
    CartContract.presenter presenter;

    List<CartList> list=new ArrayList<>();
    CartAdapter adapter;

    @BindView(R.id.cart_final_recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        presenter=new CartPresenter(this);
        ButterKnife.bind(this);

        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");
        presenter.getCart(clientModel.getMobile());
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCart(CartResponse body)
    {
        list=body.getList();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new CartAdapter(list,this);
        recyclerView.setAdapter(adapter);
    }
}
