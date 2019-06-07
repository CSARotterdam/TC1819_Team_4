package com.example.mainmenu;

//  https://console.firebase.google.com/u/0/project/database-a772f/overview //

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivityAdmin extends AppCompatActivity{

    private FirebaseAuth firebaseAuth;
    private Button logout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        Button openTOS = findViewById(R.id.tos_btn2);
        Button openInventory = findViewById(R.id.inventory_btn2);
        Button openMyItems = findViewById(R.id.usr_items_btn2);
        Button openReserveItems = findViewById(R.id.usr_reserve_btn2);
        Button openAccountSettings = findViewById(R.id.usr_settings_btn2);
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

    }
}
