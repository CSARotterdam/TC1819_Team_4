package com.example.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class RULuser extends AppCompatActivity {

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

    private FirebaseDatabase firebaseDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ruluser);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView6);
        final RecyclerView recyclerView1 = findViewById(R.id.recyclerView7);
        recyclerView.setHasFixedSize(true);
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView1.setLayoutManager(linearLayoutManager2);

        Intent ruluser = getIntent();
        String UserName = ruluser.getStringExtra("UserName");
        String UserSurname = ruluser.getStringExtra("UserSurname");
        String UserClass = ruluser.getStringExtra("UserClass");
        final HashMap<String, Object> UserItemsBorrowed = (HashMap<String, Object>) ruluser.getSerializableExtra("UserItemsBorrowed");
        final HashMap<String, Object> UserItemsReserved = (HashMap<String, Object>) ruluser.getSerializableExtra("UserItemsReserved");

        TextView name = findViewById(R.id.nameclass);
        name.setText(UserName + " " + UserSurname + ", " + UserClass);
        if (UserItemsBorrowed != null){
            firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference().child("items");
            databaseReference.addValueEventListener((new ValueEventListener() {
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
                        if (UserItemsBorrowed.containsValue(newproduct_id)) {
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
                        rulborrowedadapter rulborrowedadapter = new rulborrowedadapter(RULuser.this, productName, product_manufacturer, product_id, productCategory, productTotalStock, productCurrentStock, productAmountBroken, productURL, productDescription);
                        recyclerView1.setAdapter(rulborrowedadapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            }));
        }
        if (UserItemsReserved != null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference().child("items");
            databaseReference.addValueEventListener((new ValueEventListener() {
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
                        if (UserItemsReserved.containsValue(newproduct_id)) {
                            productName.add(newproductName);
                            product_manufacturer.add(newproduct_manufacturer);
                            product_id.add(newproduct_id);
                            productCategory.add(newproductCategory);
                            productTotalStock.add(newproductTotalStock);
                            productCurrentStock.add(newproductCurrentStock);
                            productAmountBroken.add(newproductAmountBroken);
                            productURL.add(newproductURL);
                            productDescription.add(newproductDescription);
                            System.out.println(productName);
                        }
                        rulreserveadapter rulreserveadapter = new rulreserveadapter(RULuser.this, productName, product_manufacturer, product_id, productCategory, productTotalStock, productCurrentStock, productAmountBroken, productURL, productDescription);
                        recyclerView.setAdapter(rulreserveadapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            }));
        }
    }
}
