package com.example.tasarimj.adapter;

import java.util.ArrayList;


public class NotificationProduct {

    private String productName;
    private String productDescription;
    public NotificationProduct() {
    }

    public NotificationProduct(String productName, String productDescription) {
        this.productName = productName;
        this.productDescription = productDescription;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public static ArrayList<NotificationProduct> getData() {
        ArrayList<NotificationProduct> productList = new ArrayList<>();

        String[] productNames = {"Notification Title ", "Notification Title","Notification Title ", "Notification Title"};
        String[] Production = {"Filter clogged on device 1", "Filter clogged on device 2","Filter clogged on device 1", "Filter clogged on device 2"};

        for (int i = 0; i < productNames.length; i++) {
            NotificationProduct temp = new NotificationProduct();
            temp.setProductName(productNames[i]);
            temp.setProductDescription(Production[i]);

            productList.add(temp);

        }


        return productList;


    }
}
