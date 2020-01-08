package com.example.auctionapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.auctionapp.Adapters.ProductAdapter;
import com.example.auctionapp.ConnectionClass;
import com.example.auctionapp.Model.ProductInformation;
import com.example.auctionapp.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BidFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ArrayList<ProductInformation> listProducts;
    private ConnectionClass connectionClass;
    private Connection con;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bid, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listProducts = new ArrayList<>();

        connectionClass = new ConnectionClass();
        con = connectionClass.getConnection();

        context = getContext();
        readProducts();

        return view;
    }

   private void readProducts(){

        String name, description;
        Date starting_date, finish_date;
        float  price, final_price;
        boolean sold;
        int    id_product, id_category, id_seller, id_buyer;

       try{
           String querySub = "select * from dbo.Products;";
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


        productAdapter = new ProductAdapter(getContext(),listProducts);
        recyclerView.setAdapter(productAdapter);
    }
}
