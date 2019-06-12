package com.example.mainmenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;


public class MyItemsAdapter extends RecyclerView.Adapter<MyItemsAdapter.MyViewHolder> {

    ArrayList<String> productName;
    ArrayList<String> product_manufacturer;
    ArrayList<String> product_id;
    ArrayList<String> productCategory;
    ArrayList<String> productTotalStock;
    ArrayList<String> productCurrentStock;
    ArrayList<String> productAmountBroken;
    ArrayList<String> productURL;
    ArrayList<String> productDescription;

    public Button button;

    //ArrayList<Integer> selectedPositions = new ArrayList<>();
    //int selectedPosition=-1;


    Context context;

    public MyItemsAdapter(Context context, ArrayList<String> productName, ArrayList<String> product_manufacturer, ArrayList<String> product_id, ArrayList<String> productCategory, ArrayList<String> productTotalStock, ArrayList<String> productCurrentStock, ArrayList<String> productAmountBroken, ArrayList<String> productURL, ArrayList<String> productDescription) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.resrowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(productName.get(position));
        holder.manufacturer.setText(product_manufacturer.get(position));
        holder.prodID.setText(product_id.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Removed: " + productName.get(position));
                DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("items").child(productName.get(position)).child("productCurrentStock");
                increaseAmount(dbr);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, manufacturer, prodID, button;

        public MyViewHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.resremovebtn);
            name = itemView.findViewById(R.id.name);
            manufacturer = itemView.findViewById(R.id.manufacturer);
            prodID = itemView.findViewById(R.id.prodid);

        }
    }

    public void increaseAmount(DatabaseReference DBR) {
        final DatabaseReference newDBR = DBR;
        newDBR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long amount = dataSnapshot.getValue(Long.class);
                amount += 1;
                newDBR.setValue(amount);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}