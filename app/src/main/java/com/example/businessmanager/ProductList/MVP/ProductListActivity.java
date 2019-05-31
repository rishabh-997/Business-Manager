package com.example.businessmanager.ProductList.MVP;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.ProductList.model.ProductList;
import com.example.businessmanager.ProductList.model.Product_Response;
import com.example.businessmanager.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends AppCompatActivity implements ProductListContract.view,ProductListAdapter.onNoteClickListener
{
    ProductListContract.presenter presenter;
    @BindView(R.id.prodlist_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.cart_name)
    TextView cart_name;
    @BindView(R.id.cart_id)
    TextView cart_id;
    @BindView(R.id.cart_image)
    ImageView cart_image;
    @BindView(R.id.cart_cross)
    ImageView cart_cross;
    @BindView(R.id.cart_description)
    TextView cart_desciption;
    @BindView(R.id.cart_price)
    EditText cart_price;
    @BindView(R.id.cart_size)
    EditText cart_size;
    @BindView(R.id.cart_increase_quantity)
    TextView cart_increase;
    @BindView(R.id.cart_decrease_quantity)
    TextView cart_decrease;
    @BindView(R.id.cart_item_quantity)
    TextView cart_quantity;
    @BindView(R.id.cart_submit)
    Button cart_submit;
    @BindView(R.id.bottom_sheet_add_to_cart)
    RelativeLayout bottom_sheet;
    BottomSheetBehavior sheetBehavior;

    List<ProductList> list=new ArrayList<>();
    ProductListAdapter adapter;
    boolean isSheetClosed = true;
    String cost,size, unit;
    ClientModel clientModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);
        ButterKnife.bind(this);
        presenter=new ProductListPresenter(this);
        sheetBehavior=BottomSheetBehavior.from(bottom_sheet);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");
        presenter.getList();

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        break;
                    }
                    case BottomSheetBehavior.STATE_DRAGGING: {
                        if (!isSheetClosed)
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    }
                    case BottomSheetBehavior.STATE_SETTLING: {
                        if (!isSheetClosed)
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    }

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        cart_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSheetClosed=true;
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    @Override
    public void showtaost(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showdata(Product_Response body) {
        list=body.getProductListList();
        adapter=new ProductListAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(final int position) {

        ProductList productList=list.get(position);
        cart_name.setText(productList.getName());
        cart_id.setText(productList.getId());
        cart_desciption.setText(productList.getDescription());
        Picasso.get().load(productList.getImage_url()).into(cart_image);

        isSheetClosed = false;
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        unit =cart_quantity.getText().toString();

        cart_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unit =""+(Integer.valueOf(unit)+1);
                cart_quantity.setText(unit);
            }
        });
        cart_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unit =""+(Integer.valueOf(unit)-1);
                cart_quantity.setText(unit);
            }
        });

        cart_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               addinCart(position);
            }
        });
    }

    private void addinCart(int position)
    {
        String mobile=clientModel.getMobile();
        String pid=list.get(position).getId();
        cost=cart_price.getText().toString();
        size=cart_size.getText().toString();
        unit=cart_quantity.getText().toString();

        presenter.addCart(mobile,pid,size,cost,unit);
    }

    @Override
    public void onBackPressed() {
        if(!isSheetClosed)
        {
            isSheetClosed=true;
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        else
            super.onBackPressed();

    }
}
