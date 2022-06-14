package com.lab2_simran_c0834481_android.model;

import java.io.Serializable;

public class ProductInfo implements Serializable {


    private String pdName;
    private int pdId;
    private int pdPrice;
    private String pdDescription;

    public ProductInfo(String pdName, int pdPrice, String pdDescription) {
        this.pdName = pdName;
        this.pdPrice = pdPrice;
        this.pdDescription = pdDescription;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public int getPdId() {
        return pdId;
    }

    public void setPdId(int pdId) {
        this.pdId = pdId;
    }

    public int getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(int pdPrice) {
        this.pdPrice = pdPrice;
    }

    public String getPdDescription() {
        return pdDescription;
    }

    public void setPdDescription(String pdDescription) {
        this.pdDescription = pdDescription;
    }

    public ProductInfo(String pdName, int pdId, int pdPrice, String pdDescription) {
        this.pdName = pdName;
        this.pdId = pdId;
        this.pdPrice = pdPrice;
        this.pdDescription = pdDescription;
    }



}
