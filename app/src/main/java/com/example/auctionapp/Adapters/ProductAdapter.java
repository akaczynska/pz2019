package com.example.auctionapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.auctionapp.Model.ProductInformation;
import com.example.auctionapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class ProductAdapter  extends RecyclerView.Adapter<ProductHolder> {

    private Context context;
    private ArrayList<ProductInformation> listProducts;

    public ProductAdapter(Context context, ArrayList<ProductInformation> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, null);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
            holder.productName.setText(listProducts.get(position).getProductName());
            holder.productPrice.setText(listProducts.get(position).getProductPrice());
            holder.productTime.setText(listProducts.get(position).getProductTime());
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }
}
