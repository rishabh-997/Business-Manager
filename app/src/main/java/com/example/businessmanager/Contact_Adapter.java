package com.example.businessmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Contact_Adapter extends RecyclerView.Adapter<Contact_Adapter.ViewHolder>
{

    Context context;
    ArrayList<Contact_model> arrayList;
    private onNoteListener monNoteListener;

    public Contact_Adapter(Context context, ArrayList<Contact_model> arrayList,onNoteListener monNoteListener) {
        this.context = context;
        this.monNoteListener=monNoteListener;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Contact_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_contact_display,parent,false);
        return new ViewHolder(view,monNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Contact_Adapter.ViewHolder viewHolder, int i)
    {
        Contact_model model=arrayList.get(i);
        String name=model.getName();
        String number=model.getContact();
        viewHolder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name,number;
        onNoteListener listener;
        public ViewHolder(@NonNull View itemView,onNoteListener listener)
        {
            super(itemView);
            name=itemView.findViewById(R.id.contact);
            this.listener=listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onNoteClick(getAdapterPosition());
        }
    }
    public interface onNoteListener
    {
        void onNoteClick(int position);
    }
}
