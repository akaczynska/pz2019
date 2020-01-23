package com.example.auctionapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import net.sourceforge.jtds.jdbc.DateTime;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


public class HistoryActivity extends AppCompatActivity {

    private ConnectionClass connectionClass;
    private Connection con;
    private Context context;
    private ArrayList<String> labels = new ArrayList<String>();
    //private ArrayList<BigDecimal> entries = new ArrayList<>();
    private ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
    private Date change_date;
    private float price;
    //public final static String EXTRA_PRODUCTID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        context = getApplicationContext();
        Bundle b = getIntent().getExtras();
        int in = b.getInt("PRODUCT_ID");
        Toast.makeText(context, "ID:"+in, Toast.LENGTH_SHORT).show();
        connectionClass = new ConnectionClass();
        context = getApplicationContext();
        con = connectionClass.getConnection();
        if (b != null) {
            try {
                String querySub = "select * from dbo.PriceHistory where product_ID='"+b.getInt("PRODUCT_ID") + "';";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(querySub);
                int i = 0;
                while (rs.next()) {
                    change_date = rs.getDate("change_date");
                    price = rs.getBigDecimal("price").floatValue();
                    labels.add(change_date.toString());
                    i++;
                    BarEntry entry = new BarEntry(Float.valueOf(price), i);
                    entries.add(entry);
                }

            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            BarChart chart = findViewById(R.id.barchart);
            BarDataSet bardataset = new BarDataSet(entries, "Price change over time");
            BarData data = new BarData(bardataset);
            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
            chart.setData(data);
        }
    }
}
