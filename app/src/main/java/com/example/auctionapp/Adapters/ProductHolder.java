package com.example.auctionapp.Adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.auctionapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView productName;
    TextView productPrice;
    TextView productTime;
    Button buttonHistory;

    public ProductHolder(@NonNull View itemView){
        super(itemView);
        this.imageView = itemView.findViewById(R.id.product_image);
        this.productName = itemView.findViewById(R.id.textViewProduct);
        this.productPrice = itemView.findViewById(R.id.textViewPrice);
        this.productTime = itemView.findViewById(R.id.textViewTime);
        this.buttonHistory = itemView.findViewById(R.id.buttonHistory);
    }

}
