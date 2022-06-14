package com.lab2_simran_c0834481_android.product_db;

import java.io.Serializable;

public class ProductItem implements Serializable {
    private int productPrice;
    private String productDescription;
    private String productName;
    private int productId;

    public ProductItem() {

    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public ProductItem( String productName, String productDescription, int productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }


    public static final String TABLE_NAME = "productItem";

    public static final String COLUMN_PD_DESCRIPTION = "productDescription";
    public static final String COLUMN_PD_PRICE = "productPrice";
    public static final String COLUMN_PD_ID = "productId";
    public static final String COLUMN_PD_NAME = "productName";



    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_PD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PD_NAME + " TEXT,"
                    + COLUMN_PD_DESCRIPTION + " TEXT,"
                    + COLUMN_PD_PRICE  + " INTEGER)";


    public ProductItem(String productName, int productId, int productPrice, String productDescription) {
        this.productName = productName;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }
}
