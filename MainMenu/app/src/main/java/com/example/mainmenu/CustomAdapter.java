package com.example.mainmenu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> productName;
    ArrayList<String> product_manufacturer;
    ArrayList<String> product_id;
    ArrayList<String> productCategory;
    ArrayList<String> productTotalStock;
    ArrayList<String> productCurrentStock;
    ArrayList<String> productAmountBroken;
    ArrayList<String> productURL;

    Context context;

    public CustomAdapter(Context context, ArrayList<String> productName, ArrayList<String> product_manufacturer, ArrayList<String> product_id, ArrayList<String> productCategory, ArrayList<String> productTotalStock, ArrayList<String> productCurrentStock, ArrayList<String> productAmountBroken, ArrayList<String> productURL) {
        this.context = context;
        this.productName = productName;
        this.product_manufacturer = product_manufacturer;
        this.product_id = product_id;
        this.productCategory = productCategory;
        this.productTotalStock = productTotalStock;
        this.productCurrentStock = productCurrentStock;
        this.productAmountBroken = productAmountBroken;
        this.productURL = productURL;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prodinfo = new Intent(view.getContext(), ProductInfo.class);
                prodinfo.putExtra("productName", productName.get(position));
                prodinfo.putExtra("product_manufacturer", product_manufacturer.get(position));
                prodinfo.putExtra("product_id", product_id.get(position));
                prodinfo.putExtra("productCategory", productCategory.get(position));
                prodinfo.putExtra("productTotalStock", productTotalStock.get(position));
                prodinfo.putExtra("productCurrentStock", productCurrentStock.get(position));
                prodinfo.putExtra("productAmountBroken", productAmountBroken.get(position));
                prodinfo.putExtra("productURL", productURL.get(position));
                context.startActivity(prodinfo);
            }
        });

    }


    @Override
    public int getItemCount() {
        return productName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, manufacturer, prodID;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            manufacturer = itemView.findViewById(R.id.manufacturer);
            prodID = itemView.findViewById(R.id.prodid);

        }
    }
}