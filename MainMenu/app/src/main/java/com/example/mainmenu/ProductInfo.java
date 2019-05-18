package com.example.mainmenu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ProductInfo extends AppCompatActivity {

    //Product testProduct = new Product("idIguess", "VR Headset", "test string about VR headsets or something I don't know I just programmed this page I didn't make VR headsets", Uri.parse("https://en.wikipedia.org/wiki/Virtual_reality_headset"), BitmapFactory.decodeResource(getResources(),R.drawable.hrlogo), 2, 4);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_content);
        Intent i = getIntent();
        final Product testProduct = new Product(i.getStringExtra("id"),i.getStringExtra("name"),i.getStringExtra("desc"),Uri.parse(i.getStringExtra("uri")),BitmapFactory.decodeResource(getResources(),R.drawable.hrlogo),i.getIntExtra("amount",0),i.getIntExtra("amount",0));

        //final Product testProduct = new Product("idIguess", "VR Headset", "test string about VR headsets or something I don't know I just programmed this page I didn't make VR headsets", Uri.parse("https://en.wikipedia.org/wiki/Virtual_reality_headset"), BitmapFactory.decodeResource(getResources(),R.drawable.hrlogo), 2, 4);


        //final ArrayList<String> testList = new ArrayList<String>();
        //testList.add("https://en.wikipedia.org/wiki/Virtual_reality_headset");
        //testList.add("VR Headset");
        //testList.add("test string about VR headsets or something I don't know I just programmed this page I didn't make VR headsets");
        TextView title = (TextView) findViewById(R.id.itemName);
        TextView body = findViewById(R.id.itemInfo);
        TextView wiki = findViewById(R.id.MoreInfo);
        ImageView exampleImage = findViewById(R.id.imageFile);
        title.setText(testProduct.getName());
        body.setText(testProduct.getDescription());
        exampleImage.setImageBitmap(testProduct.getPicture());
        wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, testProduct.getWebPage());
                startActivity(browserIntent);

            }
        });
    }





    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
