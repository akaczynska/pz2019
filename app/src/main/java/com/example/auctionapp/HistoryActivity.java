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
    private ArrayList<Number> priceHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);

        connectionClass = new ConnectionClass();
        con = connectionClass.getConnection();



    }

}
