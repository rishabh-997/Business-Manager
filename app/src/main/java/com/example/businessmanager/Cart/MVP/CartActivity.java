package com.example.businessmanager.Cart.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.businessmanager.Cart.Model.CartList;
import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements CartContract.view,CartAdapter.onNoteClickListener
{
    ClientModel clientModel;
    CartContract.presenter presenter;

    List<CartList> list=new ArrayList<>();
    CartAdapter adapter;

    Animation open,close,forward,backward;
    Boolean isFabOpen=false;

    @BindView(R.id.cart_final_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab_main;
    @BindView(R.id.fab_placeorder)
    FloatingActionButton fab_place;
    @BindView(R.id.fab_deleteall)
    FloatingActionButton fab_deleteall;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        presenter=new CartPresenter(this);
        ButterKnife.bind(this);

        open= AnimationUtils.loadAnimation(this,R.anim.fab_open);
        close= AnimationUtils.loadAnimation(this,R.anim.fab_close);
        forward= AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        backward= AnimationUtils.loadAnimation(this,R.anim.rotate_back);

        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");
        presenter.getCart(clientModel.getMobile());

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });
        fab_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fab_deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteAll(clientModel.getMobile());
            }
        });
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
        adapter=new CartAdapter(list,this,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onUpdateClick(int position,String cost,String size,String unit) {
        String mobile=clientModel.getMobile();
        String pid=list.get(position).getId();
        presenter.updateCart(mobile,pid,size,unit,cost);
    }

    @Override
    public void onDeleteClick(int position) {

        String mobile=clientModel.getMobile();
        String pid=list.get(position).getId();
        presenter.deleteCart(mobile,pid);
    }

    void animateFab()
    {
        if(isFabOpen)
        {
            fab_main.startAnimation(forward);
            fab_place.startAnimation(close);
            fab_deleteall.startAnimation(close);
            fab_place.setClickable(false);
            fab_deleteall.setClickable(false);
            isFabOpen=false;
        }
        else
        {
            fab_main.startAnimation(backward);
            fab_place.startAnimation(open);
            fab_deleteall.startAnimation(open);
            fab_place.setClickable(true);
            fab_deleteall.setClickable(true);
            isFabOpen=true;
        }
    }
}
