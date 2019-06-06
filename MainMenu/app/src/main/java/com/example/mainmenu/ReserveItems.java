package com.example.mainmenu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReserveItems extends AppCompatActivity{

    private FirebaseDatabase firebaseDatabase;

    String item1;
    String item2;
    String item3;

    ArrayList<String> productName = new ArrayList<>();
    ArrayList<String> product_manufacturer = new ArrayList<>();
    ArrayList<String> product_id = new ArrayList<>();
    ArrayList<String> productCategory = new ArrayList<>();
    ArrayList<String> productTotalStock = new ArrayList<>();
    ArrayList<String> productCurrentStock = new ArrayList<>();
    ArrayList<String> productAmountBroken = new ArrayList<>();
    ArrayList<String> productURL = new ArrayList<>();
    ArrayList<String> productDescription = new ArrayList<>();
    ArrayList<String> itemsChosen = new ArrayList<>();

    public String newproductName;
    public String newproduct_manufacturer;
    public String newproduct_id;
    public String newproductCategory;
    public String newproductTotalStock;
    public String newproductCurrentStock;
    public String newproductAmountBroken;
    public String newproductURL;
    public String newproductDescription;

    BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            itemsChosen= intent.getStringArrayListExtra("itemsChosen");
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_item);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReserve);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("message_subject_intent"));
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("items");

        databaseReference.addValueEventListener(new ValueEventListener() {
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
                    ResAdapter ResAdapter = new ResAdapter(ReserveItems.this, productName, product_manufacturer, product_id, productCategory, productTotalStock, productCurrentStock, productAmountBroken, productURL, productDescription);
                    recyclerView.setAdapter(ResAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button back = findViewById(R.id.reserve_backtomenu_btn);
        Button confirm = findViewById(R.id.reserve_confirm);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked!");

                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
                if (itemsChosen.size() == 1){
                    item1 = itemsChosen.get(0);
                    myRef.child("itemsBorrowed").push().setValue(item1);
                }
                else if(itemsChosen.size() == 2){
                    item1 = itemsChosen.get(0);
                    myRef.child("itemsBorrowed").push().setValue(item1);
                    item2 = itemsChosen.get(1);
                    myRef.child("itemsBorrowed").push().setValue(item2);
                }
                else if(itemsChosen.size() == 3){
                    item1 = itemsChosen.get(0);
                    myRef.child("itemsBorrowed").push().setValue(item1);
                    item2 = itemsChosen.get(1);
                    myRef.child("itemsBorrowed").push().setValue(item2);
                    item3 = itemsChosen.get(2);
                    myRef.child("itemsBorrowed").push().setValue(item3);
                }
                else{
                    Toast.makeText(ReserveItems.this,"Something has gone wrong. Try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
