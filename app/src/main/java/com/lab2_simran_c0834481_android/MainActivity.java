package com.lab2_simran_c0834481_android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lab2_simran_c0834481_android.adapter.ProductItemAdapter;
import com.lab2_simran_c0834481_android.model.ProductInfo;
import com.lab2_simran_c0834481_android.product_db.DataBaseHelper;
import com.lab2_simran_c0834481_android.product_db.ProductItem;
import com.lab2_simran_c0834481_android.sharedPreference.SharedPreference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ProductItem> dbProductList = new ArrayList<>();
    private List<ProductInfo> mList;
    private ProductItemAdapter adapter;
    private AppCompatEditText search;
    private DataBaseHelper dbHelper;
    private RecyclerView pdRecyclerView;
    private static final int REQ_CODE = 125;
    private FloatingActionButton addFloatButton;

    public SharedPreference mSharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        setData();
    }

    private void setData() {
        addDefaultProductinfo();
        mSharedPreference = new SharedPreference(this);
        pdRecyclerView = findViewById(R.id.product_recycler_view);
        pdRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        search = (AppCompatEditText) findViewById( R.id.search );
        addFloatButton = (FloatingActionButton)findViewById( R.id.add_float_button );
        dbHelper = new DataBaseHelper(this);
        if(!mSharedPreference.getIsUpdated()){
            dbHelper.addProductInfo(mList);
            mSharedPreference.setIsUpdated(true);
        }
        dbProductList.addAll(dbHelper.getAllProductsItems());

        adapter = new ProductItemAdapter(this, dbProductList );
        pdRecyclerView.setAdapter(adapter);

        search.setText( "" );

        search.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterProductInfo(editable.toString());
            }
        } );


        addFloatButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductinfo(null);
            }
        } );

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if(requestCode == REQ_CODE)
        {
            if(resultCode == -1)
            {
                ProductItem product = (ProductItem) data.getExtras().getSerializable("product_add");
                dbHelper.insertProductItem(product.getProductName(),product.getProductDescription(),product.getProductPrice());
                updateUI();
            }else if (resultCode  == 1){
                ProductItem products = (ProductItem) data.getExtras().getSerializable("product_update");
                dbHelper.updateData(products);
                updateUI();
                Toast.makeText( this, "Product Updated", Toast.LENGTH_SHORT ).show();
            }
        }
    }



    private void addDefaultProductinfo() {
        mList = new ArrayList<>();
        mList.add( new ProductInfo( "Mercedes-Benz",125, "BENZ MAN Eau de Toilette - 100 ml  (For Men)" ) );
        mList.add( new ProductInfo( "BVLGARI",34, "Man In Black Eau de Parfum - 100 ml  (For Men)" ) );
        mList.add( new ProductInfo( "SKINN by TITAN",96, "Mens Raw Eau de Parfum - 100 ml  (For Men)" ) );
        mList.add( new ProductInfo( "JAGUAR Classic",35, "Black Eau de Toilette - 100 ml  (For Men)" ) );
        mList.add( new ProductInfo( "SKINN by TITAN",55, "Steele Eau de Parfum - 50 ml  (For Men)" ) );
        mList.add( new ProductInfo( "SKINN by TITAN",75, "sheer Eau de Parfum - 50 ml  (For Women)" ) );
        mList.add( new ProductInfo( "SKINN by TITAN",115, "nude Eau de Parfum - 100 ml  (For Women)" ) );
        mList.add( new ProductInfo( "NAUTICA Voyage Sport",23, "Eau de Toilette Eau de Toilette - 50 ml  (For Men)" ) );
        mList.add( new ProductInfo( "SKINN by TITAN",43, "verge Eau de Parfum - 100 ml  (For Men)" ) );
        mList.add( new ProductInfo( "BEARDO Origin Perfume",32, "Parfum - 300 ml  (For Men)" ) );
    }

    private void filterProductInfo(String str) {
        List<ProductItem> tempList = new ArrayList();
        for(ProductItem productItem: dbProductList){
            if(productItem.getProductName().contains(str)){
                tempList.add(productItem);
            }
        }
        adapter.setList( tempList );
    }

    public void deleteProductInfo(ProductItem productItem) {
        dbHelper.deleteProductItem( productItem );
        updateUI();
        Toast.makeText( this, "Product Deleted", Toast.LENGTH_SHORT ).show();
    }

    public void addProductinfo(ProductItem productItem) {
        Intent intent = new Intent(this, AddProductInfo.class);
        intent.putExtra( "productInfo", productItem );
        startActivityForResult(intent, REQ_CODE );
    }

    private void updateUI() {
        dbProductList.clear();
        dbProductList = dbHelper.getAllProductsItems();
        adapter.setList( dbProductList );
    }



}