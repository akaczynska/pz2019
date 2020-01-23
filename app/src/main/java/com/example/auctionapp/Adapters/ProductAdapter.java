package com.example.auctionapp.Adapters;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.auctionapp.AuctionActivity;
import com.example.auctionapp.ConnectionClass;
import com.example.auctionapp.Fragments.BidFragment;
import com.example.auctionapp.Fragments.SellFragment;
import com.example.auctionapp.HistoryActivity;
import com.example.auctionapp.Model.ProductInformation;
import com.example.auctionapp.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.content.ContextCompat.startActivity;

public class ProductAdapter  extends RecyclerView.Adapter<ProductHolder> {

    private Context context;
    private ArrayList<ProductInformation> listProducts;
    ConnectionClass connectionClass;
    Connection con;


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
            Date finish_date = listProducts.get(position).getFinish_date();
            connectionClass = new ConnectionClass();
            con = connectionClass.getConnection();
            //time out when finish_date is passed
//            Date date;
//
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Calendar c = Calendar.getInstance();
//            c.add(Calendar.HOUR, 1);
//            String current_date = df.format(c.getTime());
//
//            String dtStart = "2010-10-15T09:27:37Z";
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//            try {
//                date = format.parse(dtStart);
//                System.out.println(date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            if(finish_date.getTime()<date.getTime()){
//                holder.productPrice.setText("Time out!");
//            }
            holder.productTime.setText(finish_date.toString());


            if(listProducts.get(position).isSold()){
                holder.productPrice.setText("Sold");
                holder.buttonBid.setVisibility(View.GONE);
            }
            holder.buttonHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle b = new Bundle();
                    b.putInt("PRODUCT_ID",listProducts.get(position).getProductId());
                    Toast.makeText(view.getContext(),"History Clicked, productID: "+listProducts.get(position).getProductId(), Toast.LENGTH_LONG).show();
                    Intent intent =  new Intent (context, HistoryActivity.class);
                    intent.putExtras(b);
                    context.startActivity(intent);

                }
            });
            //holder.productPrice.setText(listProducts.get(position).getStarting_price());
            holder.buttonBid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"Bid Clicked, productID: "+listProducts.get(position).getProductId(), Toast.LENGTH_LONG).show();

                    try {
                        if (con == null) {
                            Toast.makeText(context, "Check your internet access!", Toast.LENGTH_SHORT).show();
                        } else {
                            String query = "update [dbo].[Products] set sold=1 where id=" + listProducts.get(position).getProductId() + ";";
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery(query);

                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
                    }


                }
            });
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }
}
