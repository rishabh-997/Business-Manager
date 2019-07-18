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
        viewHolder.id.setText(enquiryList.getId());
        viewHolder.update.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,mobile,id;
        Button update;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.enquire_name);
            mobile=itemView.findViewById(R.id.enquire_mobile);
            id=itemView.findViewById(R.id.enquire_id);
            update=itemView.findViewById(R.id.enquire_update);
        }
    }
}
