package com.example.hydrostat.adapter;

import java.util.ArrayList;

public class CommunicationProduct {

    private String productName;
    private String productDescription;
    private int imageID;

    private CommunicationProduct() {
    }

    public CommunicationProduct(int imageID, String productName, String productDescription) {
        this.imageID = imageID;
        this.productName = productName;
        this.productDescription = productDescription;
    }


    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
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

    public static ArrayList<CommunicationProduct> getData() {
        ArrayList<CommunicationProduct> productList = new ArrayList<CommunicationProduct>();

        String[] productNames = {"20.02.2020", "20.03.2020"};
        String[] Production = {"10$", "20$"};

        for (int i = 0; i < productNames.length; i++) {
            CommunicationProduct temp = new CommunicationProduct();
            temp.setProductName(productNames[i]);
            temp.setProductDescription(Production[i]);

            productList.add(temp);
        }
        return productList;


    }
}
