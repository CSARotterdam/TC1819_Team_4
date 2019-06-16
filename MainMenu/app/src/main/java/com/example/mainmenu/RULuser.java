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
        final String Uid = ruluser.getStringExtra("Uid");
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
                    productName.clear();
                    product_manufacturer.clear();
                    product_id.clear();
                    productCategory.clear();
                    productTotalStock.clear();
                    productCurrentStock.clear();
                    productAmountBroken.clear();
                    productURL.clear();
                    productDescription.clear();
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
                        rulborrowedadapter rulborrowedadapter = new rulborrowedadapter(RULuser.this, productName, product_manufacturer, product_id, productCategory, productTotalStock, productCurrentStock, productAmountBroken, productURL, productDescription, Uid);
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
                        if (UserItemsReserved.containsValue(newproduct_id)) {
                            productNameres.add(newproductName);
                            product_manufacturerres.add(newproduct_manufacturer);
                            product_idres.add(newproduct_id);
                            productCategoryres.add(newproductCategory);
                            productTotalStockres.add(newproductTotalStock);
                            productCurrentStockres.add(newproductCurrentStock);
                            productAmountBrokenres.add(newproductAmountBroken);
                            productURLres.add(newproductURL);
                            productDescriptionres.add(newproductDescription);
                        }
                        rulreserveadapter rulreserveadapter = new rulreserveadapter(RULuser.this, productNameres, product_manufacturerres, product_idres, productCategoryres, productTotalStockres, productCurrentStockres, productAmountBrokenres, productURLres, productDescriptionres, Uid);
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
