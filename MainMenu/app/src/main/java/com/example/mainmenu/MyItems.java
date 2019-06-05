package com.example.mainmenu;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyItems extends AppCompatActivity{

    private FirebaseDatabase firebaseDatabase;
    public List<listItem> itemList;

    ArrayList<String> productName = new ArrayList<>();
    ArrayList<String> product_manufacturer = new ArrayList<>();
    ArrayList<String> product_id = new ArrayList<>();
    ArrayList<String> productCategory = new ArrayList<>();
    ArrayList<String> productTotalStock = new ArrayList<>();
    ArrayList<String> productCurrentStock = new ArrayList<>();
    ArrayList<String> productAmountBroken = new ArrayList<>();
    ArrayList<String> productURL = new ArrayList<>();
    ArrayList<String> productDescription = new ArrayList<>();

    public String newproductName;
    public String newproduct_manufacturer;
    public String newproduct_id;
    public String newproductCategory;
    public String newproductTotalStock;
    public String newproductCurrentStock;
    public String newproductAmountBroken;
    public String newproductURL;
    public String newproductDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_items);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference MyRef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("itemsBorrowed");
        DatabaseReference databaseReference1 = firebaseDatabase.getReference().child("items");

        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    listItem listItem = dataSnapshot1.getValue(listItem.class);
                    newproductName = listItem.getProductName();
                    newproduct_manufacturer = listItem.getProduct_manufacturer();
                    newproduct_id = listItem.getProduct_id();
                    newproductCategory = listItem.getProductCategory();
                    newproductTotalStock = listItem.getProductTotalStock();
                    newproductCurrentStock = listItem.getProductCurrentStock();
                    newproductAmountBroken = listItem.getProductAmountBroken();
                    newproductURL = listItem.getProductURL();
                    newproductDescription = listItem.getProductDescription();

                    productName.add(newproductName);
                    product_manufacturer.add(newproduct_manufacturer);
                    product_id.add(newproduct_id);
                    productCategory.add(newproductCategory);
                    productTotalStock.add(newproductTotalStock);
                    productCurrentStock.add(newproductCurrentStock);
                    productAmountBroken.add(newproductAmountBroken);
                    productURL.add(newproductURL);
                    productDescription.add(newproductDescription);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button back = findViewById(R.id.mitems_backtomenu_btn);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked!");

                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });
    }
}
