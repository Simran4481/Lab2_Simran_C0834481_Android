package com.lab2_simran_c0834481_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lab2_simran_c0834481_android.model.ProductInfo;
import com.lab2_simran_c0834481_android.product_db.ProductItem;

public class AddProductInfo extends AppCompatActivity {

    private ProductItem product;
    private EditText name, price, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_product_info );
        Button submit = (Button) findViewById( R.id.submit );
        AppCompatImageView back = (AppCompatImageView) findViewById( R.id.back_icon );
        description = (EditText) findViewById( R.id.pd_description );
        price = (EditText) findViewById( R.id.pd_price );
        name = (EditText) findViewById( R.id.pd_name );

        product = (ProductItem) getIntent().getSerializableExtra( "productInfo" );
        if(product != null){
            setProductInfo(product);
        }

        submit.setOnClickListener( view -> validation() );

        back.setOnClickListener( view -> finish() );
    }

    private void setProductInfo(ProductItem product) {
        name.setText(product.getProductName());
        description.setText( product.getProductDescription() );
        price.setText( ""+product.getProductPrice() );

    }

    private void validation() {
        String pdDescritpion, pdPrice, pdName;
        pdName = name.getText().toString();
        pdDescritpion = description.getText().toString();
        pdPrice = price.getText().toString();

        if(pdName.isEmpty()){
            name.setError( "Please enter Product Name" );
            return;
        }

        if(pdDescritpion.isEmpty()){
            description.setError( "Please enter Product Description" );
            return;
        }
        if(pdPrice.isEmpty()){
            price.setError( "Please enter Product Price" );
            return;
        }


        if(product!=null && product.getProductId()!=0){
            ProductItem products = new ProductItem(pdName, product.getProductId(),Integer.parseInt( pdPrice ),pdDescritpion);
            Intent data = new Intent();
            data.putExtra( "product_update",products );
            setResult( 1, data);
        }else {
            ProductItem products = new ProductItem( pdName,pdDescritpion, Integer.parseInt( pdPrice ));
            Intent data = new Intent();
            data.putExtra( "product_add",products );
            setResult( -1, data);
        }
        finish();
    }

}