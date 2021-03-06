package com.example.mainmenu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

    Object position1;
    Object ChosenItem;
    String ChosenName;
    String ChosenItem2;

    ArrayList<Integer> selectedPosition = new ArrayList<>();
    ArrayList<String> Selection = new ArrayList<>();
    ArrayList<String> nameSelection = new ArrayList<>();

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
        holder.stock.setText("Currently " + productCurrentStock.get(position) + " out of " + productTotalStock.get(position) + " in stock.");
        Intent intent = new Intent("message_subject_intent");
        intent.putExtra("itemsChosen" , Selection);
        intent.putExtra("itemNamesChosen", nameSelection);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        if(selectedPosition.contains(position))
            holder.resrow_layout.setBackgroundColor(Color.parseColor("#A8A8A8"));
        else
           holder.resrow_layout.setBackgroundColor(Color.parseColor("#ffffff"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(productCurrentStock.get(position)) < 1){
                    Toast.makeText(context,"This item is currently unavailable.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(selectedPosition.contains(position)) {
                        position1 = position;
                        selectedPosition.remove(position1);
                        notifyItemChanged(position);
                        ChosenItem = product_id.get(position);
                        ChosenName = productName.get(position);
                        nameSelection.remove(ChosenName);
                        Selection.remove(ChosenItem);
                        System.out.println(nameSelection);
                    }
                    else {
                        if(selectedPosition.size() < 3) {
                            selectedPosition.add(position);
                            notifyItemChanged(position);
                            ChosenItem2 = product_id.get(position);
                            Selection.remove(ChosenItem);
                            Selection.add(ChosenItem2);
                            ChosenName = productName.get(position);
                            nameSelection.add(ChosenName);
                            System.out.println(nameSelection);
                        }
                        else {
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(view.getRootView().getContext());

                            builder.setTitle("Too many items selected");

                            builder.setMessage("You may only select up to 3 items as once. Please deselect a different item to select this one.");
                            builder.setNeutralButton("OK", null);
                            builder.show();
                        }
                    }
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return productName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, manufacturer, stock;
        LinearLayout resrow_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            resrow_layout = itemView.findViewById(R.id.resrow_layout);
            name = itemView.findViewById(R.id.name);
            manufacturer = itemView.findViewById(R.id.manufacturer);
            stock = itemView.findViewById(R.id.stock);

        }
    }
}