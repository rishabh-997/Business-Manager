package com.example.businessmanager.ProductList.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.businessmanager.ProductList.model.ProductList;
import com.example.businessmanager.ProductList.model.Product_Response;
import com.example.businessmanager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends AppCompatActivity implements ProductListContract.view
{
    ProductListContract.presenter presenter;
    @BindView(R.id.prodlist_recycler_view)
    RecyclerView recyclerView;

    List<ProductList> list=new ArrayList<>();
    ProductListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);
        ButterKnife.bind(this);
        presenter=new ProductListPresenter(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter.getList();
    }

    @Override
    public void showtaost(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showdata(Product_Response body) {
        list=body.getProductListList();
        adapter=new ProductListAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
}
