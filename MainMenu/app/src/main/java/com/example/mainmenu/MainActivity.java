package com.example.mainmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openTOS = findViewById(R.id.tos_btn);
        Button openInventory = findViewById(R.id.inventory_btn);
        Button openMyItems = findViewById(R.id.usr_items_btn);
        Button openReserveItems = findViewById(R.id.usr_reserve_btn);
        Button openAccountSettings = findViewById(R.id.usr_settings_btn);

        openInventory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Pressed INV Button");

                Intent openInventory = new Intent(getApplicationContext(), Inventory.class);
                startActivity(openInventory);

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

                Intent openAccountSettings = new Intent(getApplicationContext(), AccountSettings.class);
                startActivity(openAccountSettings);
            }
        });
    }
}

