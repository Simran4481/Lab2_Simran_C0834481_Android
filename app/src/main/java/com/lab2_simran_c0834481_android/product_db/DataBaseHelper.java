package com.lab2_simran_c0834481_android.product_db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lab2_simran_c0834481_android.model.ProductInfo;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_product_item";
    private static final int DATABASE_VERSION = 1;



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( ProductItem.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProductItem.TABLE_NAME);
        onCreate(db);
    }
    public void addProductInfo(List<ProductInfo> listItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i = 0; i < listItem.size(); i++) {
            values.put(ProductItem.COLUMN_PD_NAME, listItem.get(i).getPdName());
            values.put(ProductItem.COLUMN_PD_DESCRIPTION, listItem.get(i).getPdDescription());
            values.put(ProductItem.COLUMN_PD_PRICE, listItem.get(i).getPdPrice());
            db.insert(ProductItem.TABLE_NAME, null, values);

        }
        db.close();
    }


    public void deleteProductItem(ProductItem product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ProductItem.TABLE_NAME, ProductItem.COLUMN_PD_ID + " = ?",
                new String[]{String.valueOf(product.getProductId())});
        db.close();
    }

    @SuppressLint("Range")
    public List<ProductItem> getAllProductsItems() {
        List<ProductItem> quizList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + ProductItem.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ProductItem productDetail = new ProductItem();
                productDetail.setProductId(cursor.getInt(cursor.getColumnIndex(ProductItem.COLUMN_PD_ID)));
                productDetail.setProductName( cursor.getString( cursor.getColumnIndex( ProductItem.COLUMN_PD_NAME ) ) );
                productDetail.setProductDescription( cursor.getString( cursor.getColumnIndex( ProductItem.COLUMN_PD_DESCRIPTION) ) );
                productDetail.setProductPrice( cursor.getInt( cursor.getColumnIndex( ProductItem.COLUMN_PD_PRICE ) ) );

                quizList.add(productDetail);
            } while (cursor.moveToNext());
        }
        db.close();

        return quizList;
    }

    public long insertProductItem(String prodcuctName, String productDescription, int productPrice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ProductItem.COLUMN_PD_NAME, prodcuctName);
        values.put(ProductItem.COLUMN_PD_DESCRIPTION, productDescription);
        values.put(ProductItem.COLUMN_PD_PRICE, productPrice);

        long id = db.insert(ProductItem.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public int updateData(ProductItem productItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductItem.COLUMN_PD_NAME, productItem.getProductName());
        values.put( ProductItem.COLUMN_PD_DESCRIPTION, productItem.getProductDescription() );
        values.put( ProductItem.COLUMN_PD_PRICE, productItem.getProductPrice() );

        return db.update(ProductItem.TABLE_NAME, values, ProductItem.COLUMN_PD_ID + " = ?",
                new String[]{String.valueOf(productItem.getProductId())});
    }



}
