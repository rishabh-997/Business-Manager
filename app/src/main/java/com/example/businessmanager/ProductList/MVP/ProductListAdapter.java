package com.example.businessmanager.ProductList.MVP;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.businessmanager.ProductList.model.ProductList;
import com.example.businessmanager.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>
{
    private Context context;
    private List<ProductList> list;
    private ProductList productList;
    private onNoteClickListener onNoteClickListener;

    public ProductListAdapter(Context context, List<ProductList> list,onNoteClickListener onNoteClickListener) {
        this.context = context;
        this.list = list;
        this.onNoteClickListener=onNoteClickListener;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_prod_list,parent,false);
        return new ViewHolder(view,onNoteClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder viewHolder, int i)
    {
        productList=list.get(i);
        String image_url=productList.getImage_url();
        viewHolder.name.setText(productList.getName());
        viewHolder.id.setText("Product id : "+productList.getId());
        viewHolder.description.setText("Uses : \n"+productList.getDescription());
        viewHolder.subcategory.setText("Sub_category : "+productList.getSub_category());
        if(image_url.isEmpty())
            image_url="http://unbxd.com/blog/wp-content/uploads/2014/02/No-results-found.jpg";
        Picasso.get().load(image_url).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name,id,description,subcategory;
        ImageView imageView;
        ImageView specs;
        onNoteClickListener listener;

        public ViewHolder(@NonNull View itemView, final onNoteClickListener listener)
        {
            super(itemView);
            this.listener=listener;
            name=itemView.findViewById(R.id.prodlist_name);
            id=itemView.findViewById(R.id.prodlist_id);
            description=itemView.findViewById(R.id.prodlist_description);
            subcategory=itemView.findViewById(R.id.prodlist_subcat);
            imageView=itemView.findViewById(R.id.prodlist_image);
            specs=itemView.findViewById(R.id.prodlist_specs);
            itemView.setOnClickListener(this);

            specs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSpecClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            listener.onNoteClick(getAdapterPosition());
        }
    }

    public interface onNoteClickListener
    {
        void onNoteClick(int position);
        void onSpecClick(int position);
    }
}
