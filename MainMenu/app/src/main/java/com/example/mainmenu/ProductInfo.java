package com.example.mainmenu;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ProductInfo extends AppCompatActivity {

    //Product testProduct = new Product("idIguess", "VR Headset", "test string about VR headsets or something I don't know I just programmed this page I didn't make VR headsets", Uri.parse("https://en.wikipedia.org/wiki/Virtual_reality_headset"), BitmapFactory.decodeResource(getResources(),R.drawable.hrlogo), 2, 4);
    int currAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_content);
        //Intent i = getIntent();
        //final Product testProduct = new Product(i.getStringExtra("id"),i.getStringExtra("name"),i.getStringExtra("desc"),Uri.parse(i.getStringExtra("uri")),BitmapFactory.decodeResource(getResources(),R.drawable.hrlogo),i.getIntExtra("amount",0),i.getIntExtra("amount",0));

        Intent prodinfo = getIntent();
        String productName = prodinfo.getStringExtra("productName");
        String product_manufacturer = prodinfo.getStringExtra("product_manufacturer");
        String product_id = prodinfo.getStringExtra("product_id");
        //String productCategory = prodinfo.getStringExtra("productCategory");
        int productTotalStock = prodinfo.getIntExtra("productTotalStock", 0);
        int productCurrentStock = prodinfo.getIntExtra("productCurrentStock", 0);
        //String productAmountBroken = prodinfo.getStringExtra("productAmountBroken");
        String productURL = prodinfo.getStringExtra("productURL");
        String productDescription = prodinfo.getStringExtra("productDescription");

        final Product testProduct = new Product(product_id, productName, product_manufacturer, productDescription, Uri.parse(productURL), BitmapFactory.decodeResource(getResources(),R.drawable.hrlogo), productCurrentStock, productTotalStock);


        //final ArrayList<String> testList = new ArrayList<String>();
        //testList.add("https://en.wikipedia.org/wiki/Virtual_reality_headset");
        //testList.add("VR Headset");
        //testList.add("test string about VR headsets or something I don't know I just programmed this page I didn't make VR headsets");
        TextView title = (TextView) findViewById(R.id.itemName);
        TextView body = findViewById(R.id.itemInfo);
        TextView wiki = findViewById(R.id.MoreInfo);
        TextView manufacturer = findViewById(R.id.itemManufacturer);
        ImageView exampleImage = findViewById(R.id.imageFile);
        Button reserve = findViewById(R.id.ReserveButton);
        title.setText(testProduct.getName());
        body.setText(testProduct.getDescription());
        manufacturer.setText(product_manufacturer);
        exampleImage.setImageBitmap(testProduct.getPicture());
        currAmount = testProduct.getCurrentAmount();
        wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, testProduct.getWebPage());
                startActivity(browserIntent);

            }
        });
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currAmount > 0) {
                    showChoiceDialog(ProductInfo.this, "Reserve Item", "Would you like to reserve this item?");
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductInfo.this);

                    builder.setTitle("Product Unavailable");

                    builder.setMessage("This product is currently unavailable");
                    builder.setNeutralButton("OK", null);
                    builder.show();

                }
            }
        });
    }


    public void showChoiceDialog(Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", null);
        builder.show();
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
