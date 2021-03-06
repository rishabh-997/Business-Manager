package com.example.businessmanager.HomeActivity.MVP;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.businessmanager.ClientDashboard.MVP.ClientDashActivity;
import com.example.businessmanager.HomeActivity.model.ClientModel;
import com.example.businessmanager.R;

import java.io.Serializable;
import java.util.List;

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.ViewHolder>
{
    List<ClientModel> list;
    Context context;

    public Home_Adapter(List<ClientModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Home_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_contact_display,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Home_Adapter.ViewHolder viewHolder, final int i)
    {
        ClientModel model=list.get(i);
        String name=model.getName();
        viewHolder.name.setText(model.getName());

        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ClientDashActivity.class);
                intent.putExtra("client_details", (Serializable) list.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.contact);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ClientDashActivity.class);
                    intent.putExtra("client_details", (Serializable) list.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
