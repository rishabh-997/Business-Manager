package com.example.businessmanager.Cart.MVP;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businessmanager.Cart.Model.CartList;
import com.example.businessmanager.Cart.Model.CartResponse;
import com.example.businessmanager.CheckOut.MVP.CheckOutActivity;
import com.example.businessmanager.ClientDashboard.MVP.ClientDashActivity;
import com.example.businessmanager.History.MVP.HistoryActivity;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.Model_common.UnitList;
import com.example.businessmanager.Model_common.UnitResponse;
import com.example.businessmanager.R;
import com.example.businessmanager.Utilities.SharedPref;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements CartContract.view,CartAdapter.onNoteClickListener
{
    ClientModel clientModel;
    CartContract.presenter presenter;

    List<CartList> list=new ArrayList<>();
    List<UnitList> unitList=new ArrayList<>();
    String[] unitlistfinal;
    CartAdapter adapter;

    Animation open,close,forward,backward;
    Boolean isFabOpen=false;
    SharedPref sharedPref;

    @BindView(R.id.cart_final_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab_main;
    @BindView(R.id.fab_placeorder)
    FloatingActionButton fab_place;
    @BindView(R.id.fab_deleteall)
    FloatingActionButton fab_deleteall;
    @BindView(R.id.cart_empty)
    ImageView cartempty;
    @BindView(R.id.cart_bar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar_text)
    TextView title;
    @BindView(R.id.toolbar_history)
    ImageView history;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        presenter=new CartPresenter(this);
        ButterKnife.bind(this);
        sharedPref=new SharedPref(this);
        getSupportActionBar().hide();
        presenter.getUnit();

        title.setText("Cart");
        open= AnimationUtils.loadAnimation(this,R.anim.fab_open);
        close= AnimationUtils.loadAnimation(this,R.anim.fab_close);
        forward= AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        backward= AnimationUtils.loadAnimation(this,R.anim.rotate_back);

        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });

        fab_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                place_order();
            }
        });
        fab_deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                deleteall();
            }
        });

        cartempty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(CartActivity.this, ClientDashActivity.class);
                intent.putExtra("client_details", clientModel);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(CartActivity.this, HistoryActivity.class);
                intent.putExtra("client_details", clientModel);
                startActivity(intent);
            }
        });
    }

    private void place_order()
    {
        final AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("Confirm");
        alert.setMessage("Have you filled the final price and clicked on update ?");
        alert.setCancelable(false);

        alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
                Intent intent=new Intent(CartActivity.this, CheckOutActivity.class);
                intent.putExtra("client_details", clientModel);
                startActivity(intent);
                alert.cancel();
            }
        });
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert.cancel();
            }
        });
        alert.show();
    }

    private void deleteall()
    {
        final AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("Are You Sure ?");
        alert.setMessage("All Order will be Deleted");
        alert.setCancelable(false);

        alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                progressBar.setVisibility(View.VISIBLE);
                presenter.deleteAll(clientModel.getMobile(),sharedPref.getCompany());
                alert.cancel();
            }
        });
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert.cancel();
            }
        });
        alert.show();
    }

    @Override
    public void showToast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCart(CartResponse body)
    {
        progressBar.setVisibility(View.GONE);
        list=body.getList();
        if(list.size()==0)
        {
            cartempty.setVisibility(View.VISIBLE);
            list.clear();
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new CartAdapter(list, this, this);
            adapter.setSpinner(unitlistfinal);
            recyclerView.setAdapter(adapter);
        }
        else {
            cartempty.setVisibility(View.GONE);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new CartAdapter(list, this, this);
            adapter.setSpinner(unitlistfinal);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void clear() {
        list.clear();
        cartempty.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setList(UnitResponse body)
    {
        unitList=body.getPUnitList();
        unitlistfinal=new String[unitList.size()];
        for(int i=0;i<unitList.size();i++)
            unitlistfinal[i]=unitList.get(i).getUnit();

        progressBar.setVisibility(View.VISIBLE);
        presenter.getCart(clientModel.getMobile(),sharedPref.getCompany());
    }

    @Override
    public void delete(int pos,String string)
    {
        presenter.getCart(clientModel.getMobile(),sharedPref.getCompany());
    }

    @Override
    public void onUpdateClick(final int position, final String cost, final String size, final String unit)
    {
        final AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("Are You Sure ?");
        alert.setMessage("Order will be Updated");
        alert.setCancelable(false);

        alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                progressBar.setVisibility(View.VISIBLE);

                String mobile=clientModel.getMobile();
                String pid=list.get(position).getId();
                presenter.updateCart(mobile,pid,size,unit,cost);
                alert.cancel();
            }
        });
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert.cancel();
            }
        });
        alert.show();
    }

    @Override
    public void onDeleteClick(final int position) {

        final AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("Are You Sure ?");
        alert.setMessage("Product will be Deleted");
        alert.setCancelable(false);

        alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                progressBar.setVisibility(View.VISIBLE);
                String mobile=clientModel.getMobile();
                String pid=list.get(position).getCartid();
                presenter.deleteCart(pid,position);
                alert.cancel();
            }
        });
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert.cancel();
            }
        });
        alert.show();
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
