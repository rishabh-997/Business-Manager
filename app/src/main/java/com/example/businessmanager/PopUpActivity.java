package com.example.businessmanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.ProductHistory.Model.ProdHistList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopUpActivity extends Activity
{
    ProdHistList prodHist;

    @BindView(R.id.orderid)
    TextView orderid;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.nvm)
    TextView nvm;
    @BindView(R.id.cost)
    TextView cost;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.unit)
    TextView unit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        ButterKnife.bind(this);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height=dm.heightPixels;
        int width=dm.widthPixels;

        getWindow().setLayout((int)(width*0.7),(int)(height*0.4));

        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;

        getWindow().setAttributes(params);

        prodHist=(ProdHistList) getIntent().getExtras().getSerializable("history");
        orderid.setText(prodHist.getOrderId());
        date.setText("Ordered on "+prodHist.getDate());
        nvm.setText(prodHist.getNvm());
        size.setText(prodHist.getSize());
        unit.setText(prodHist.getUnit());
        cost.setText(prodHist.getCost());

    }
}
