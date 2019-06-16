package com.example.mainmenu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
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

    ArrayList<String> productNameres = new ArrayList<>();
    ArrayList<String> product_manufacturerres = new ArrayList<>();
    ArrayList<String> product_idres = new ArrayList<>();
    ArrayList<String> productCategoryres = new ArrayList<>();
    ArrayList<String> productTotalStockres = new ArrayList<>();
    ArrayList<String> productCurrentStockres = new ArrayList<>();
    ArrayList<String> productAmountBrokenres = new ArrayList<>();
    ArrayList<String> productURLres = new ArrayList<>();
    ArrayList<String> productDescriptionres = new ArrayList<>();

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_items);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        final RecyclerView recyclerView1 = findViewById(R.id.recyclerView3);
        recyclerView.setHasFixedSize(true);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView1.setLayoutManager(linearLayoutManager2);


        final ArrayList<String> itemsBorrowed = getIntent().getStringArrayListExtra("itemsBorrowed");
        final ArrayList<String> itemsReserved = getIntent().getStringArrayListExtra("itemsReserved");

        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        DatabaseReference databaseReference1 = firebaseDatabase.getReference().child("items");

        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productName.clear();
                product_manufacturer.clear();
                product_id.clear();
                productCategory.clear();
                productTotalStock.clear();
                productCurrentStock.clear();
                productAmountBroken.clear();
                productURL.clear();
                productDescription.clear();

                productNameres.clear();
                product_manufacturerres.clear();
                product_idres.clear();
                productCategoryres.clear();
                productTotalStockres.clear();
                productCurrentStockres.clear();
                productAmountBrokenres.clear();
                productURLres.clear();
                productDescriptionres.clear();
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
                    if (itemsReserved.contains(newproduct_id)){
                        productNameres.add(newproductName);
                        product_manufacturerres.add(newproduct_manufacturer);
                        product_idres.add(newproduct_id);
                        productCategoryres.add(newproductCategory);
                        productTotalStockres.add(newproductTotalStock);
                        productCurrentStockres.add(newproductCurrentStock);
                        productAmountBrokenres.add(newproductAmountBroken);
                        productURLres.add(newproductURL);
                        productDescriptionres.add(newproductDescription);
                        MyItemsAdapter MyItemsAdapter = new MyItemsAdapter(MyItems.this, productNameres, product_manufacturerres, product_idres, productCategoryres, productTotalStockres, productCurrentStockres, productAmountBrokenres, productURLres, productDescriptionres);
                        recyclerView.setAdapter(MyItemsAdapter);
                    }
                    if (itemsBorrowed.contains(newproduct_id)){
                        productName.add(newproductName);
                        product_manufacturer.add(newproduct_manufacturer);
                        product_id.add(newproduct_id);
                        productCategory.add(newproductCategory);
                        productTotalStock.add(newproductTotalStock);
                        productCurrentStock.add(newproductCurrentStock);
                        productAmountBroken.add(newproductAmountBroken);
                        productURL.add(newproductURL);
                        productDescription.add(newproductDescription);
                        borrowAdapter borrowAdapter = new borrowAdapter(MyItems.this, productName, product_manufacturer, product_id, productCategory, productTotalStock, productCurrentStock, productAmountBroken, productURL, productDescription);
                        recyclerView1.setAdapter(borrowAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
