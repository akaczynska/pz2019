package com.example.auctionapp.Adapters;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.content.ContextCompat.startActivity;

public class ProductAdapter  extends RecyclerView.Adapter<ProductHolder> {

    private Context context;
    private ArrayList<ProductInformation> listProducts;

    private String nick;
    ConnectionClass connectionClass;
    Connection con;
    int id;
    Date d1, d2;


    public ProductAdapter(Context context, ArrayList<ProductInformation> listProducts, String nick) {
        this.context = context;
        this.listProducts = listProducts;
        this.nick = nick;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, null);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductHolder holder, final int position) {
            holder.productName.setText(listProducts.get(position).getName());
            Date finish_date = listProducts.get(position).getFinish_date();
            holder.productPrice.setText(String.valueOf(listProducts.get(position).getPrice()));
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

//            Date today = Calendar.getInstance().getTime();
//            String today_string = df.format(today);
//            String finish_string = df.format(finish_date);
//                try {
//                    d1 = df.parse(today_string);
//                    d2 = df.parse(finish_string);
//                }
//                catch(Exception e ){}
//            if(d1.getTime()>d2.getTime()){
//                holder.productPrice.setText("Time out!");
//            }
//            holder.productTime.setText(finish_date.toString());


            if(listProducts.get(position).isSold()){
                holder.buttonBid.setVisibility(View.GONE);
                try {
                    if (con == null) {
                        Toast.makeText(context, "Check your internet access!", Toast.LENGTH_SHORT).show();
                    } else {
                        String query= "select AccountName from [dbo].SecurityAccounts where AccountID="+listProducts.get(position).getBuyer_login()+";";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        rs.next();
                        holder.productPrice.setText("Bought by "+rs.getString("AccountName"));

                    }
                }
                catch (Exception e){
                    Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
                }
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
                   // Toast.makeText(view.getContext(),"Bid Clicked, productID: "+listProducts.get(position).getProductId(), Toast.LENGTH_LONG).show();

                    try {
                        if (con == null) {
                            Toast.makeText(context, "Check your internet access!", Toast.LENGTH_SHORT).show();
                        } else {
                            String query_id = "select AccountID from [dbo].SecurityAccounts where AccountName='a';";
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery(query_id);
                            rs.next();
                            id=rs.getInt("AccountID");
                            Toast.makeText(context,id+" "+nick, Toast.LENGTH_LONG).show();
                            holder.productPrice.setText("Bought by "+nick);
                            String query = "update [dbo].[Products] set sold=1, buyer_login="+id+" where id=" + listProducts.get(position).getProductId() + ";";
                            holder.buttonBid.setVisibility(View.GONE);
                            ResultSet rs2 = stmt.executeQuery(query);
                            rs2.next();

                        }
                    }
                    catch (Exception e)
                    {
                       //Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
                    }


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
