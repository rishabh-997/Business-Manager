package com.example.businessmanager.Enquire.MVP.Pending;

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

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.ViewHolder>
{
    Context context;
    List<EnquiryList> list;
    OnNoteClickListener onNoteClickListener;

    public PendingAdapter(Context context, List<EnquiryList> list, OnNoteClickListener onNoteClickListener) {
        this.context = context;
        this.onNoteClickListener=onNoteClickListener;
        this.list = list;
    }

    @NonNull
    @Override
    public PendingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_enquiry,viewGroup,false);
        return new ViewHolder(view,onNoteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingAdapter.ViewHolder viewHolder, int i)
    {
        EnquiryList enquiryList=list.get(i);
        viewHolder.name.setText(enquiryList.getName());
        viewHolder.mobile.setText(enquiryList.getMobile());
        viewHolder.company.setText(enquiryList.getCompany());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,mobile,company;
        Button update;
        OnNoteClickListener listen;

        public ViewHolder(@NonNull View itemView,OnNoteClickListener onNoteClickListener)
        {
            super(itemView);
            listen=onNoteClickListener;
            name=itemView.findViewById(R.id.enquire_name);
            mobile=itemView.findViewById(R.id.enquire_mobile);
            company=itemView.findViewById(R.id.enquire_company);
            update=itemView.findViewById(R.id.enquire_update);

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listen.updateState(getAdapterPosition());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listen.goToDashBoard(getAdapterPosition());
                }
            });
        }
    }

    interface OnNoteClickListener
    {
        void updateState(int position);

        void goToDashBoard(int adapterPosition);
    }
}
