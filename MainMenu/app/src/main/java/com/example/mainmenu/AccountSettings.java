package com.example.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AccountSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);

        Button openMyItems = findViewById(R.id.usr_items_btn2);

        openMyItems.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Pressed My Items Button");

                Intent openMyItems = new Intent(getApplicationContext(), MyItems.class);
                startActivity(openMyItems);
            }
        });
    }
}
