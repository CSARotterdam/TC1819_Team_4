package com.example.mainmenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class ResAdapter extends RecyclerView.Adapter<ResAdapter.MyViewHolder> {

    ArrayList<String> productName;
    ArrayList<String> product_manufacturer;
    ArrayList<String> product_id;
    ArrayList<String> productCategory;
    ArrayList<String> productTotalStock;
    ArrayList<String> productCurrentStock;
    ArrayList<String> productAmountBroken;
    ArrayList<String> productURL;
    ArrayList<String> productDescription;

    ArrayList<Integer> selectedPositions = new ArrayList<>();
    int selectedPosition=-1;


    Context context;

    public ResAdapter(Context context, ArrayList<String> productName, ArrayList<String> product_manufacturer, ArrayList<String> product_id, ArrayList<String> productCategory, ArrayList<String> productTotalStock, ArrayList<String> productCurrentStock, ArrayList<String> productAmountBroken, ArrayList<String> productURL, ArrayList<String> productDescription) {
        this.context = context;
        this.productName = productName;
        this.product_manufacturer = product_manufacturer;
        this.product_id = product_id;
        this.productCategory = productCategory;
        this.productTotalStock = productTotalStock;
        this.productCurrentStock = productCurrentStock;
        this.productAmountBroken = productAmountBroken;
        this.productURL = productURL;
        this.productDescription = productDescription;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(productName.get(position));
        holder.manufacturer.setText(product_manufacturer.get(position));
        holder.prodID.setText(product_id.get(position));
        if(selectedPosition==position)
            holder.rowlayout.setBackgroundColor(Color.parseColor("#000000"));
        else
           holder.rowlayout.setBackgroundColor(Color.parseColor("#ffffff"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = position;
                //System.out.println(selectedPosition);
                notifyItemChanged(position);
                System.out.println("Clicked: " + productName.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return productName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, manufacturer, prodID, rowlayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            rowlayout = itemView.findViewById(R.id.row_layout);
            name = itemView.findViewById(R.id.name);
            manufacturer = itemView.findViewById(R.id.manufacturer);
            prodID = itemView.findViewById(R.id.prodid);

        }
    }
}