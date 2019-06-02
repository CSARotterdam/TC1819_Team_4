package com.example.mainmenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory extends AppCompatActivity{

    private List<listItem>ListItem;
    private RecyclerView rv;
    private CustomAdapter adapter;

    ArrayList<String> productName = new ArrayList<>();
    ArrayList<String> product_manufacturer = new ArrayList<>();
    ArrayList<String> product_id = new ArrayList<>();
    ArrayList<String> productCategory = new ArrayList<>();
    ArrayList<String> productTotalStock = new ArrayList<>();
    ArrayList<String> productCurrentStock = new ArrayList<>();
    ArrayList<String> productAmountBroken = new ArrayList<>();
    ArrayList<String> productURL = new ArrayList<>();
    ArrayList<String> productDescription = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ListItem=new ArrayList<>();

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("items");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        listItem l=npsnapshot.getValue(listItem.class);
                        ListItem.add(l);
                    }
                    adapter=new CustomAdapter(ListItem);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button back = findViewById(R.id.inv_backtomenu_btn);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked!");

                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });
    }
}