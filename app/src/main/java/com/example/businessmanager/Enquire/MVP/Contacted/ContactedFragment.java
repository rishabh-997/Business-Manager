package com.example.businessmanager.Enquire.MVP.Contacted;


import android.content.Context;
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

import com.example.businessmanager.Enquire.MVP.Pending.PendingAdapter;
import com.example.businessmanager.Enquire.MVP.Pending.PendingFragment;
import com.example.businessmanager.Enquire.Mode.EnquiryList;
import com.example.businessmanager.Enquire.Mode.EnquiryResponse;
import com.example.businessmanager.R;
import com.example.businessmanager.Utilities.SharedPref;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactedFragment extends Fragment implements ContactedContract.view
{
    ContactedContract.presenter presenter;
    SharedPref sharedPref;
    ContactListener listener;

    List<EnquiryList> list=new ArrayList<>();
    ContactAdapter adapter;

    @BindView(R.id.contact_bar)
    ProgressBar progressBar;
    @BindView(R.id.contact_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.contat_swipe)
    SwipeRefreshLayout swipe;

    public ContactedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacted, container, false);
        presenter=new ContactedPresenter(this);
        sharedPref=new SharedPref(getContext());
        ButterKnife.bind(this,view);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipe.setRefreshing(false);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar.setVisibility(View.VISIBLE);
        presenter.getList(sharedPref.getAccessToken(),"Contacted");

        return view;
    }

    public void refresh()
    {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getList(sharedPref.getAccessToken(),"Contacted");
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
        adapter=new ContactAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }

    public interface ContactListener
    {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ContactListener)
        {
            listener=(ContactListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()+"must be implemented");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}
