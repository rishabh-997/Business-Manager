package com.example.businessmanager.Enquire.MVP.Contacted;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.businessmanager.Enquire.Mode.EnquiryList;
import com.example.businessmanager.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>
{
    Context context;
    List<EnquiryList> list;

    public ContactAdapter(Context context, List<EnquiryList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_enquiry,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder viewHolder, int i) {
        EnquiryList enquiryList=list.get(i);
        viewHolder.name.setText(enquiryList.getName());
        viewHolder.mobile.setText(enquiryList.getMobile());
        viewHolder.company.setText(enquiryList.getCompany());
        viewHolder.update.setVisibility(View.GONE);
        if(i==list.size())
            viewHolder.divider.setVisibility(View.GONE);
        else
            viewHolder.divider.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,mobile,company;
        Button update;
        View divider;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.enquire_name);
            mobile=itemView.findViewById(R.id.enquire_mobile);
            company=itemView.findViewById(R.id.enquire_company);
            update=itemView.findViewById(R.id.enquire_update);
            divider=itemView.findViewById(R.id.divider);
        }
    }
}
