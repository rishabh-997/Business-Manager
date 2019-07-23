package com.example.businessmanager.Enquire.MVP.Pending;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.businessmanager.Cart.MVP.CartActivity;
import com.example.businessmanager.ClientDashboard.MVP.ClientDashActivity;
import com.example.businessmanager.Enquire.Mode.EnquiryList;
import com.example.businessmanager.Enquire.Mode.EnquiryResponse;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.HomeActivity.model.ResponseClient;
import com.example.businessmanager.R;
import com.example.businessmanager.Utilities.SharedPref;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendingFragment extends Fragment implements PendingContract.view,PendingAdapter.OnNoteClickListener
{
    PendingContract.presenter presenter;
    SharedPref sharedPref;
    PendingListener listener;

    List<EnquiryList> list=new ArrayList<>();
    PendingAdapter adapter;

    @BindView(R.id.pend_bar)
    ProgressBar progressBar;
    @BindView(R.id.pend_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.pend_swipe)
    SwipeRefreshLayout swipe;

    public PendingFragment() {    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        presenter=new PendingPresenter(this);
        sharedPref=new SharedPref(getContext());
        ButterKnife.bind(this,view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipe.setRefreshing(false);
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        presenter.getList(sharedPref.getAccessToken(),"Pending");

        return view;
    }

    private void refresh()
    {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getList(sharedPref.getAccessToken(),"Pending");
    }

    @Override
    public void toast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList(EnquiryResponse body) {
        progressBar.setVisibility(View.GONE);
        list.clear();
        list=body.getEnquiry_List();
        adapter=new PendingAdapter(getContext(),list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateState(int position)
    {
        progressBar.setVisibility(View.VISIBLE);
        presenter.update(sharedPref.getAccessToken(),list.get(position).getId());
    }

    @Override
    public void updated() {
        Toast.makeText(getContext(), "Enquiry status Changed", Toast.LENGTH_SHORT).show();
        refresh();
    }

    @Override
    public void goToDashBoard(int adapterPosition) {
        String mobile=list.get(adapterPosition).getMobile();
        sharedPref.setCompany(list.get(adapterPosition).getCompany());
        progressBar.setVisibility(View.VISIBLE);
        presenter.search(mobile);
    }

    @Override
    public void showDashboard(ResponseClient body)
    {
        progressBar.setVisibility(View.GONE);
        if(body.getList().size()>0)
        {
            ClientModel clientModel=body.getList().get(0);
            Intent intent=new Intent(getContext(), CartActivity.class);
            intent.putExtra("client_details", (Serializable) clientModel);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getContext(), "No Such Client Found", Toast.LENGTH_SHORT).show();
        }
    }


    public interface PendingListener
    {
        void UpdateContact();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof PendingListener)
        {
            listener=(PendingListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()+"must be implemented");
        }
    }
}
