package com.example.businessmanager.History.MVP;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.businessmanager.History.Model.HistoryList;
import com.example.businessmanager.ProductList.MVP.ProductListAdapter;
import com.example.businessmanager.R;

import org.w3c.dom.Text;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>
{
    Context context;
    List<HistoryList> historyList;
    private HistoryAdapter.onNoteClickListener onNoteClickListener;

    public HistoryAdapter(Context context, List<HistoryList> historyList,onNoteClickListener onNoteClickListener) {
        this.context = context;
        this.historyList = historyList;
        this.onNoteClickListener=onNoteClickListener;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_history_list,parent,false);
        return new HistoryAdapter.ViewHolder(view,onNoteClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder viewHolder, int i)
    {
        HistoryList history=historyList.get(i);
        viewHolder.id.setText(history.getOrderId());
        viewHolder.date.setText(history.getDateOfPurchase());
        viewHolder.cost.setText("COST : "+history.getTotalCost());
        viewHolder.totalcost.setText("TOATL COST : "+history.getTotal_Cost_WTax());
        viewHolder.paymentterms.setText("PAYMENT TERMS : "+history.getPaymentTerms());
        viewHolder.paymentstatus.setText("PAYMENT STATUS : "+history.getPaymentStatus());
        viewHolder.orderstatus.setText("ORDER STATUS : "+history.getOrder_Status());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView id,date,cost,totalcost,paymentstatus,paymentterms,orderstatus;
        HistoryAdapter.onNoteClickListener listener;

        public ViewHolder(@NonNull View itemView, onNoteClickListener listener)
        {
            super(itemView);
            this.listener=listener;
            id=itemView.findViewById(R.id.history_orderid);
            date=itemView.findViewById(R.id.history_date);
            cost=itemView.findViewById(R.id.history_cost);
            totalcost=itemView.findViewById(R.id.history_totalcost);
            paymentstatus=itemView.findViewById(R.id.history_paymentstatus);
            paymentterms=itemView.findViewById(R.id.history_paymentterms);
            orderstatus=itemView.findViewById(R.id.history_orderstatus);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onNoteClick(getAdapterPosition());
        }
    }
    public interface onNoteClickListener
    {
        void onNoteClick(int position);
    }
}
