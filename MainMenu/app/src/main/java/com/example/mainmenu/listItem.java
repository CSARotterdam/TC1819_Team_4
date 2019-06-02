package com.example.mainmenu;

import java.util.ArrayList;

public class listItem {
    public String product_id;
    public String productName;
    public String product_manufacturer;
    public String productCategory;
    public String productTotalStock;
    public String productCurrentStock;
    public String productAmountBroken;
    public String productURL;
    public String productDescription;

    public listItem(String productName, String product_manufacturer, String product_id, String productCategory, String productTotalStock, String productCurrentStock, String productAmountBroken, String productURL, String productDescription) {
        this.productName = productName;
        this.product_manufacturer = product_manufacturer;
        this.product_id = product_id;
        this.productCategory = productCategory;
        this.productTotalStock = productTotalStock;
        this.productCurrentStock = productCurrentStock;
        this.productAmountBroken = productAmountBroken;
        this.productURL = productURL;
        this.productDescription = productDescription;
    }
    public String getProduct_id(){
        return product_id;
    }
    public String getProduct_manufacturer(){
        return product_manufacturer;
    }
    public String getProductName(){
        return productName;
    }
    public String getProductCategory(){
        return productCategory;
    }
    public String getProductTotalStock(){
        return productTotalStock;
    }
    public String getProductCurrentStock(){
        return productCurrentStock;
    }
    public String getProductAmountBroken(){
        return productAmountBroken;
    }
    public String getProductURL(){
        return productURL;
    }
    public String getProductDescription(){
        return productDescription;
    }
}
