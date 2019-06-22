package com.example.businessmanager.CheckOut.MVP;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.businessmanager.Cart.Model.CartList;
import com.example.businessmanager.R;

import java.util.List;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.ViewHolder>
{
    Context context;
    List<CartList> list;

    public CheckOutAdapter(Context context, List<CartList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CheckOutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_checkout_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CheckOutAdapter.ViewHolder viewHolder, int i)
    {
        CartList cartList=list.get(i);
        viewHolder.name.setText(cartList.getName());
        viewHolder.unit.setText("Unit : "+cartList.getUnit());
        viewHolder.size.setText("Quantity"+cartList.getSize());
        viewHolder.costper.setText("Cost "+cartList.getCost()+"/-");

        viewHolder.cost.setText(cartList.getTotal_cost());
        viewHolder.gst.setText(cartList.getSgst());
        viewHolder.total.setText(cartList.getTotal_cost_tax());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,size,unit,costper,cost,gst,total;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.checkoutlist_name);
            size=itemView.findViewById(R.id.checkoutlist_size);
            unit=itemView.findViewById(R.id.checkoutlist_unit);
            costper=itemView.findViewById(R.id.checkoutlist_costper);
            cost=itemView.findViewById(R.id.checkoutlist_cost);
            gst=itemView.findViewById(R.id.checkoutlist_gst);
            total=itemView.findViewById(R.id.checkoutlist_total);
        }
    }
}
