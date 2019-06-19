package com.example.businessmanager.History.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businessmanager.History.Model.HistoryDetailList;
import com.example.businessmanager.History.Model.HistoryDetailResponse;
import com.example.businessmanager.History.Model.HistoryList;
import com.example.businessmanager.History.Model.HistoryResponse;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.R;
import com.example.businessmanager.Utilities.SharedPref;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity implements HistoryContract.view,HistoryAdapter.onNoteClickListener
{
    HistoryContract.presenter presenter;
    ClientModel clientModel;
    SharedPref sharedPref;

    @BindView(R.id.history_name)
    TextView name;
    @BindView(R.id.history_recycler)
    RecyclerView recyclerView;

    List<HistoryList> list=new ArrayList<>();
    HistoryAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        sharedPref=new SharedPref(this);

        presenter=new HistoryPresenter(this);
        clientModel=(ClientModel)getIntent().getExtras().getSerializable("client_details");
        name.setText(clientModel.getName());
        presenter.getHistory(clientModel.getMobile(),"Client",sharedPref.getCompany());
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList(HistoryResponse body) {
        list=body.getHistoryList();
        Collections.reverse(list);
        adapter=new HistoryAdapter(this,list,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showDetails(HistoryDetailResponse body,String id,String comment) {
        List<HistoryDetailList> detailList=body.getHistoryDetailList();
        if(comment.trim().isEmpty())
            comment="No Comment Found";

        String title=id;
        String message="Additional Info. \n"+comment+"\n\n";

        for(int i=0;i<detailList.size();i++)
        {
            String one="Product : "+detailList.get(i).getId()+"\n"+"Unit : "+detailList.get(i).getUnit()+"\n"+"Number of Units : "+detailList.get(i).getSize()+"\n"+"Cost per item : "+detailList.get(i).getCost()+"\n"+"Total Cost : "+detailList.get(i).getTotal_cost();
            message=message+one+"\n\n";
        }


        message.trim();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public void onNoteClick(int position) {
        String orderid=list.get(position).getOrderId();
        String comment=list.get(position).getComment();
        presenter.getDetails(orderid,comment);
    }
}
