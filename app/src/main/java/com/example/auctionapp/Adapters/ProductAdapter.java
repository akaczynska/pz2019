package com.example.auctionapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.auctionapp.AuctionActivity;
import com.example.auctionapp.Model.ProductInformation;
import com.example.auctionapp.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    public void onBindViewHolder(@NonNull ProductHolder holder, final int position) {
            holder.productName.setText(listProducts.get(position).getName());
            Date date = listProducts.get(position).getFinish_date();
            holder.productTime.setText(date.toString());
            holder.buttonHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"History Clicked, productID: "+listProducts.get(position).getProductId(), Toast.LENGTH_LONG).show();

                }
            });
            //holder.productPrice.setText(listProducts.get(position).getStarting_price());
            holder.buttonBid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"Bid Clicked, productID: "+listProducts.get(position).getProductId(), Toast.LENGTH_LONG).show();

                }
            });
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }
}
