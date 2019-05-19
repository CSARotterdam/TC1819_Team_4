package com.example.mainmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Inventory extends AppCompatActivity{

    ArrayList<String> productName = new ArrayList<>();
    ArrayList<String> product_manufacturer = new ArrayList<>();
    ArrayList<String> product_id = new ArrayList<>();
    ArrayList<String> productCategory = new ArrayList<>();
    ArrayList<String> productTotalStock = new ArrayList<>();
    ArrayList<String> productCurrentStock = new ArrayList<>();
    ArrayList<String> productAmountBroken = new ArrayList<>();
    ArrayList<String> productURL = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray item = obj.getJSONArray("items");
            for (int i = 0; i < item.length(); i++) {
                JSONObject itemDetail = item.getJSONObject(i);
                productName.add(itemDetail.getString("productName"));
                product_manufacturer.add(itemDetail.getString("product_manufacturer"));
                product_id.add(itemDetail.getString("product_id"));
                productCategory.add(itemDetail.getString("productCategory"));
                productTotalStock.add(itemDetail.getString("productTotalStock"));
                productCurrentStock.add(itemDetail.getString("productCurrentStock"));
                productAmountBroken.add(itemDetail.getString("productAmountBroken"));
                productURL.add(itemDetail.getString("productURL"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomAdapter customAdapter = new CustomAdapter(Inventory.this, productName, product_manufacturer, product_id, productCategory, productTotalStock, productCurrentStock, productAmountBroken, productURL);
        recyclerView.setAdapter(customAdapter);

        Button back = findViewById(R.id.inv_backtomenu_btn);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked!");

                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("invelectronics.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;


    }
}