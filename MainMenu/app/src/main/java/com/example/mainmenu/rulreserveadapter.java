package com.example.mainmenu;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.content.DialogInterface;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;


public class rulreserveadapter extends RecyclerView.Adapter<rulreserveadapter.MyViewHolder> {

    ArrayList<String> productName;
    ArrayList<String> product_manufacturer;
    ArrayList<String> product_id;
    ArrayList<String> productCategory;
    ArrayList<String> productTotalStock;
    ArrayList<String> productCurrentStock;
    ArrayList<String> productAmountBroken;
    ArrayList<String> productURL;
    ArrayList<String> productDescription;
    String Uid;
    String currentID;

    FirebaseDatabase firebaseDatabase;

    public Button button;

    Context context;

    public rulreserveadapter(Context context, ArrayList<String> productName, ArrayList<String> product_manufacturer, ArrayList<String> product_id, ArrayList<String> productCategory, ArrayList<String> productTotalStock, ArrayList<String> productCurrentStock, ArrayList<String> productAmountBroken, ArrayList<String> productURL, ArrayList<String> productDescription, String Uid) {
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
        this.Uid = Uid;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rulreservedlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(productName.get(position));
        holder.manufacturer.setText(product_manufacturer.get(position));
        final String baseID = product_id.get(position);
        holder.prodID.setText(baseID);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog(position, "Lend out an item", "Accepting this will lend this item out to a user. Are you sure you want to do this?");
                currentID = product_id.get(position);

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
            button = itemView.findViewById(R.id.rulreservedbutton);
            name = itemView.findViewById(R.id.name);
            manufacturer = itemView.findViewById(R.id.manufacturer);
            prodID = itemView.findViewById(R.id.prodid);

        }
    }

    public void showChoiceDialog(final int position, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final String baseID = product_id.get(position);
        if (title != null) builder.setTitle(title);

        DialogInterface.OnClickListener acceptitem = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference().child("Users").child(Uid).child("itemsReserved");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            String productID = dataSnapshot1.getValue(String.class);
                            if(productID.equals(baseID)) {
                                dataSnapshot1.getRef().removeValue();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                final DatabaseReference databaseReference1 =  FirebaseDatabase.getInstance().getReference().child("Users").child(Uid).child("itemsBorrowed");
                databaseReference1.push().setValue(baseID);

                productName.remove(position);
                product_manufacturer.remove(position);
                product_id.remove(position);
                productCategory.remove(position);
                productCurrentStock.remove(position);
                productTotalStock.remove(position);
                productAmountBroken.remove(position);
                productURL.remove(position);
                productDescription.remove(position);
                notifyItemChanged(position);
                notifyDataSetChanged();

            }
        };

        builder.setMessage(message);
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", acceptitem);
        builder.show();
    }
}