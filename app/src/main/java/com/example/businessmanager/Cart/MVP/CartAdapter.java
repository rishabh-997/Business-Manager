package com.example.businessmanager.Cart.MVP;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businessmanager.Cart.Model.CartList;
import com.example.businessmanager.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>
{
    List<CartList> list;
    Context context;

    public CartAdapter(List<CartList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_cart_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder viewHolder, int i)
    {
        final CartList cartList=list.get(i);
        viewHolder.name.setText(cartList.getName());
        viewHolder.cost.setText(cartList.getCost());
        viewHolder.size.setText(cartList.getSize());
        viewHolder.unit.setText(cartList.getUnit());
        Picasso.get().load(cartList.getImage_url()).into(viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        EditText cost,size,unit;
        ImageView image;
        TextView delete,update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.cart_final_name);
            cost=itemView.findViewById(R.id.cart_final_price);
            size=itemView.findViewById(R.id.cart_final_size);
            unit=itemView.findViewById(R.id.cart_final_unit);
            image=itemView.findViewById(R.id.cart_final_image);
            update=itemView.findViewById(R.id.cart_final_update);
            delete=itemView.findViewById(R.id.cart_final_delete);
        }
    }
}
