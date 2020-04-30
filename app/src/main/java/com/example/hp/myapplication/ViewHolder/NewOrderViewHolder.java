package com.example.hp.myapplication.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.hp.myapplication.Interface.ItemClickListener;
import com.example.hp.myapplication.R;

public class NewOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

    public TextView newTxtOrderId,newTxtOrderStatus,newTxtOrderPhone,newTxtOrderAdress;

    private ItemClickListener itemClickListener;

    public NewOrderViewHolder(View itemView) {
        super(itemView);

        newTxtOrderAdress = (TextView)itemView.findViewById(R.id.order_address);
        newTxtOrderId = (TextView)itemView.findViewById(R.id.order_id);
        newTxtOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
        newTxtOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select The Action");

        menu.add(0,0,getAdapterPosition(),"Update");
        menu.add(0,0,getAdapterPosition(),"Delete");
    }
}
