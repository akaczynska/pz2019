package com.example.auctionapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.auctionapp.Model.ProductInformation;

import java.sql.Connection;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ConnectionClass connectionClass;
    private Connection con;
    private Context context;
    private ArrayList priceHistory = new ArrayList();
    public final static String EXTRA_PRODUCTID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);
        Intent intent = getIntent();


        Bundle args = getArguments();
        login = args.getString("EXTRA_LOGIN");

        connectionClass = new ConnectionClass();
        con = connectionClass.getConnection();
/*
try{
           String querySub = "select * from dbo.PriceHistory where id=productID;";
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery(querySub);
           while (rs.next()) {
               name = rs.getString("name");
               description = rs.getString("description");
               starting_date = rs.getDate("start_date");
               finish_date = rs.getDate("finish_date");
               price = rs.getFloat("price");
               final_price = rs.getFloat("price");
               id_product = rs.getInt("id");
               sold = rs.getBoolean("sold");
               id_category = rs.getInt("id_category");
               id_seller = rs.getInt("seller_login");
               id_buyer = rs.getInt("buyer_login");
               ProductInformation productInformation = new ProductInformation(id_product,name,description,price,final_price,starting_date, finish_date, sold, id_category, id_seller, id_buyer);
               listProducts.add(productInformation);
           }

       }
       catch (Exception e){
           Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
       }
* */


    }

}
