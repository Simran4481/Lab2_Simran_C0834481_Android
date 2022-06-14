package com.lab2_simran_c0834481_android.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.lab2_simran_c0834481_android.MainActivity;
import com.lab2_simran_c0834481_android.R;
import com.lab2_simran_c0834481_android.product_db.ProductItem;

import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private List<ProductItem> mData;


    public ProductItemAdapter(Context context, List<ProductItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context =  context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate( R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductItem productItem = mData.get(position);
        holder.productId.setText( "Product Id: "+productItem.getProductId() );
        holder.productDescription.setText( productItem.getProductDescription() );
        holder.productPrice.setText( "$"+productItem.getProductPrice()+"/-" );
        holder.productName.setText( productItem.getProductName() );
        holder.delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new AlertDialog.Builder(context)
                        .setMessage("Are you sure want to Delete this product?")
                        .setCancelable(false)
                        .setPositiveButton( Html.fromHtml("<font color='#000000'>Yes</font>"), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ((MainActivity)context).deleteProductInfo(productItem);
                            }
                        })
                        .setNegativeButton(Html.fromHtml("<font color='#000000'>NO</font>"), null)
                        .show();
            }
        } );
        holder.edit.setOnClickListener( view -> ((MainActivity)context).addProductinfo(productItem) );
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setList(List<ProductItem> mList) {
        this.mData = mList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView productId, productName, productDescription,productPrice;
        AppCompatImageView edit, delete;

        ViewHolder(View itemView) {
            super(itemView);
            productId = itemView.findViewById(R.id.id);
            productName= itemView.findViewById( R.id.name );
            productDescription= itemView.findViewById( R.id.description );
            productPrice= itemView.findViewById( R.id.price );
            edit = itemView.findViewById( R.id.edit );
            delete = itemView.findViewById( R.id.delete );
        }
    }
}