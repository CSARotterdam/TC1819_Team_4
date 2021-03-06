package com.example.mainmenu;

//  https://console.firebase.google.com/u/0/project/database-a772f/overview //

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivityAdmin extends AppCompatActivity{

    ArrayList<String> itemsBorrowed = new ArrayList<>();
    ArrayList<String> itemsReserved = new ArrayList<>();

    private FirebaseAuth firebaseAuth;
    public FirebaseDatabase firebaseDatabase;
    private Button logout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        Button openTOS = findViewById(R.id.tos_btn2);
        Button openInventory = findViewById(R.id.inventory_btn2);
        Button openMyItems = findViewById(R.id.usr_items_btn2);
        Button openReserveItems = findViewById(R.id.usr_reserve_btn2);
        Button openAccountSettings = findViewById(R.id.usr_settings_btn2);
        Button openStatistics = findViewById(R.id.statistics_btn2);
        Button openCreateItem = findViewById(R.id.create_item_btn_2);

        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button)findViewById(R.id.logout_btn2);


        openInventory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Pressed INV Button");

                Intent openInventory = new Intent(getApplicationContext(), Inventory.class);
                startActivity(openInventory);
                //Intent openCreateProduct = new Intent(getApplicationContext(), ProductCreate.class);
                //startActivity(openCreateProduct);
            }
        });

        openTOS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Pressed ToS Button");

                Intent openTOS = new Intent(getApplicationContext(), TOS.class);
                startActivity(openTOS);
            }
        });

        openMyItems.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Pressed My Items Button");

                Intent openMyItems = new Intent(getApplicationContext(), MyItems.class);
                openMyItems.putExtra("itemsBorrowed" , itemsBorrowed);
                openMyItems.putExtra("itemsReserved", itemsReserved);
                startActivity(openMyItems);

                //Intent openProductInfo = new Intent(getApplicationContext(), ProductInfo.class);
                //startActivity(openProductInfo);
            }
        });

        openReserveItems.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Pressed Reserve Items Button");

                Intent openReserveItems = new Intent(getApplicationContext(), ReserveItems.class);
                startActivity(openReserveItems);
            }
        });

        openAccountSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Pressed Account Settings Button");

                Intent openAccountSettings = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(openAccountSettings);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MainActivityAdmin.this, LoginActivityActivity.class));
            }
        });

        openStatistics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Pressed Account Settings Button");

                Intent openAccountSettings = new Intent(getApplicationContext(), ReserveUserlist.class);
                startActivity(openAccountSettings);
            }
        });

        openCreateItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Pressed Account Settings Button");

                Intent openAccountSettings = new Intent(getApplicationContext(), ProductCreate.class);
                startActivity(openAccountSettings);
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference MyRef = firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("itemsBorrowed");
        DatabaseReference MyRef2 = firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("itemsReserved");
        MyRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    String item = (String) dataSnapshot1.getValue();
                    itemsBorrowed.add(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        MyRef2.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    String item = (String) dataSnapshot1.getValue();
                    itemsReserved.add(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
