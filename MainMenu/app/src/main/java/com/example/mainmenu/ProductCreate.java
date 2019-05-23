package com.example.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

public class ProductCreate extends AppCompatActivity {

private ImageView image;
public static final int GET_FROM_GALLERY = 3;
private Bitmap imageBitmap;
private String productUri;
private String productName;
private String productDesc;
private int productAmount;
private String productID;
private EditText nameText, descText, idText, URLText, numText, manuText;
private Button creaButton;
private String manu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_create);
        setupUIViews();
        image=(ImageView) findViewById(R.id.productImage);
        image.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.camera));
        image.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                                     }
                                 }
        );
        creaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    productName = nameText.getText().toString().trim();
                    productDesc = descText.getText().toString().trim();
                    productAmount = Integer.parseInt(numText.getText().toString().trim());
                    productID = idText.getText().toString().trim();
                    productUri = URLText.getText().toString().trim();
                    manu = manuText.getText().toString().trim();

                    //Product newProd = new Product(productID,productName,productDesc,productUri,imageBitmap,productAmount,productAmount);
                    Intent openProductInfo = new Intent(getApplicationContext(), ProductInfo.class);
                    openProductInfo.putExtra("product_id",productID);
                    openProductInfo.putExtra("productName", productName);
                    openProductInfo.putExtra("productDescription", productDesc);
                    openProductInfo.putExtra("productURL",productUri);
                    openProductInfo.putExtra("productTotalStock",productAmount);
                    openProductInfo.putExtra("productCurrentStock",productAmount);
                    openProductInfo.putExtra("product_manufacturer", manu);
                    startActivity(openProductInfo);
                }

            }
        });

    }

//https://stackoverflow.com/questions/9107900/how-to-upload-image-from-gallery-in-android
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                image.setImageBitmap(bitmap);
                imageBitmap = bitmap;
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void setupUIViews() {
        nameText = findViewById(R.id.productName);
        descText = findViewById(R.id.productDescription);
        idText = findViewById(R.id.productID);
        URLText = findViewById(R.id.productURL);
        numText = findViewById(R.id.productAmount);
        creaButton = findViewById(R.id.btnCreate);
        manuText = findViewById(R.id.productManu);
    }


    //modified version of validate in RegisterActivity
    private boolean validate(){
        Boolean result = false;

        String name = nameText.getText().toString();
        String description = descText.getText().toString();
        String id = idText.getText().toString();
        String URL = URLText.getText().toString();
        String amount = numText.getText().toString();
        String manufacturer = manuText.getText().toString();

        if(name.isEmpty() || manufacturer.isEmpty() || description.isEmpty() || id.isEmpty() || imageBitmap == null || URLUtil.isValidUrl(URL) == false || amount.isEmpty()){
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }
        return result;
    }

}