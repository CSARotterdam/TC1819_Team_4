package com.example.mainmenu;

import android.media.Image;

import java.net.URL;

public class Product {

    String ID;
    String name;
    String description;
    URL webPage;
    Image picture;
    int currentAmount;
    int maxAmount;

    Product(String ID, String name, String description, URL webPage, Image picture, int currentAmount, int maxAmount) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.webPage = webPage;
        this.picture = picture;
        this.currentAmount = currentAmount;
        this.maxAmount = maxAmount;

    }

    public Image getPicture() {
        return picture;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public String getDescription() {
        return description;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public URL getWebPage() {
        return webPage;
    }

    public void lowerAmount() {
        this.currentAmount = this.currentAmount - 1;
    }

    public void increaseAmount() {
        this.currentAmount = this.currentAmount + 1;
    }

}

