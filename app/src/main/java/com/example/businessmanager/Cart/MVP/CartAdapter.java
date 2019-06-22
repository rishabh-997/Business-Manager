package com.example.businessmanager.Cart.MVP;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.businessmanager.Cart.Model.CartList;
import com.example.businessmanager.ProductList.MVP.ProductListActivity;
import com.example.businessmanager.ProductList.MVP.ProductListAdapter;
import com.example.businessmanager.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>
{
    List<CartList> list;
    Context context;
    private CartAdapter.onNoteClickListener onNoteClickListener;
    String[] unitlistfinal;

    public CartAdapter(List<CartList> list, Context context,onNoteClickListener onNoteClickListener) {
        this.list = list;
        this.context = context;
        this.onNoteClickListener=onNoteClickListener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_cart_list,viewGroup,false);
        return new ViewHolder(view,onNoteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder viewHolder, int i)
    {
        final CartList cartList=list.get(i);
        viewHolder.name.setText(cartList.getName());
        viewHolder.cost.setText(cartList.getCost());
        viewHolder.size.setText(cartList.getSize());
        Picasso.get().load(cartList.getImage_url()).into(viewHolder.image);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, unitlistfinal);
        viewHolder.unit.setAdapter(adapter);
        int position=adapter.getPosition(cartList.getUnit());
        viewHolder.unit.setSelection(position);
        viewHolder.unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setSpinner(String[] unitlistfinal)
    {
        this.unitlistfinal=unitlistfinal;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name;
        EditText cost,size;
        Spinner unit;
        ImageView image;
        TextView delete,update;
        onNoteClickListener listener;

        public ViewHolder(@NonNull View itemView , final onNoteClickListener listener) {
            super(itemView);
            this.listener=listener;
            name=itemView.findViewById(R.id.cart_final_name);
            cost=itemView.findViewById(R.id.cart_final_price);
            size=itemView.findViewById(R.id.cart_final_size);
            unit=itemView.findViewById(R.id.cart_final_unit);
            image=itemView.findViewById(R.id.cart_final_image);
            update=itemView.findViewById(R.id.cart_final_update);
            delete=itemView.findViewById(R.id.cart_final_delete);

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUpdateClick(getAdapterPosition(),cost.getText().toString(),size.getText().toString(),unit.getSelectedItem().toString());
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
    public interface onNoteClickListener
    {
        void onUpdateClick(int position,String cost,String size,String unit);
        void onDeleteClick(int position);
    }
}
