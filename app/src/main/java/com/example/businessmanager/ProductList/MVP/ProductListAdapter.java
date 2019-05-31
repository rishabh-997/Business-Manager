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
    Context context;
    List<ProductList> list;
    ProductList productList;

    public ProductListAdapter(Context context, List<ProductList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_prod_list,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder viewHolder, int i)
    {
        productList=list.get(i);
        String image_url=productList.getImage_url();
        viewHolder.name.setText(productList.getName());
        viewHolder.id.setText("Product id : "+productList.getId());
        viewHolder.description.setText(productList.getDescription());
        viewHolder.subcategory.setText("Sub_category : "+productList.getSub_category());
        if(image_url.isEmpty())
            image_url="http://unbxd.com/blog/wp-content/uploads/2014/02/No-results-found.jpg";
        Picasso.get().load(image_url).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,id,description,subcategory;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.prodlist_name);
            id=itemView.findViewById(R.id.prodlist_id);
            description=itemView.findViewById(R.id.prodlist_description);
            subcategory=itemView.findViewById(R.id.prodlist_subcat);
            imageView=itemView.findViewById(R.id.prodlist_image);
        }
    }
}
