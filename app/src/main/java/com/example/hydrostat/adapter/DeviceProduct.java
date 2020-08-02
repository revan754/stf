package com.example.hydrostat.adapter;

import java.util.ArrayList;


public class DeviceProduct {

    private String cihazNo;
    private String cailsmaZamani;
    private String sonYikama;

    public DeviceProduct() {
    }

    public DeviceProduct(String cihazNo, String cailsmaZamani, String sonYikama) {
        this.cihazNo = cihazNo;
        this.cailsmaZamani = cailsmaZamani;
        this.sonYikama= sonYikama;
    }


    public String getCihazNo() {
        return cihazNo;
    }

    public void setCihazNo(String cihazNo) {
        this.cihazNo = cihazNo;
    }

    public String getCailsmaZamani() {
        return cailsmaZamani;
    }

    public String getSonYikama() {
        return sonYikama;
    }

    public void setSonYikama(String sonYikama) {
        this.sonYikama = sonYikama;
    }

    public void setCailsmaZamani(String cailsmaZamani) {
        this.cailsmaZamani = cailsmaZamani;
    }

    public static ArrayList<DeviceProduct> getData() {
        ArrayList<DeviceProduct> productList = new ArrayList<>();


        String[] cihazNo = {"1", "2","3", "4","5","6", "7"};
        String[] calismaZamani = {"20", "10","30", "43","20", "10","30", "5","5"};
        String[] sonYikama = {"10.06.2020", "30.05.2020","02.06.2020", "15.06.2020","10.06.2020", "30.05.2020","02.06.2020", "15.06.2020"};

        for (int i = 0; i < cihazNo.length; i++) {

            DeviceProduct temp = new DeviceProduct();
            temp.setCihazNo(cihazNo[i]);
            temp.setCailsmaZamani(calismaZamani[i]);
            temp.setSonYikama(sonYikama[i]);
            productList.add(temp);

        }
        return productList;
    }
}
