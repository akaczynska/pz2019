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
import java.util.Date;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.formatter.ValueFormatter;


public class HistoryActivity extends AppCompatActivity {

    private ConnectionClass connectionClass;
    private Connection con;
    private Context context;
    private ArrayList<String> labelz = new ArrayList<String>();
    //private ArrayList<BigDecimal> entries = new ArrayList<>();
    private ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
    private String change_date;
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
                    change_date = rs.getString("change_date");
                    price = rs.getBigDecimal("price").floatValue();
                    labelz.add(change_date.substring(0,19));
                    i++;
                    BarEntry entry = new BarEntry(i, Float.valueOf(price));
                    entries.add(entry);
                }

            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            BarChart chart = findViewById(R.id.barchart);
            BarDataSet bardataset = new BarDataSet(entries, "Price change over time");
            //bardataset.setAxisDependency(YAxis.AxisDependency.LEFT);
            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
            BarData data = new BarData(bardataset);
            data.setBarWidth(0.9f);
            chart.setData(data);
            //chart.setFitBars(true);
            //chart.setNoDataText("No data to display");
            chart.invalidate();
            //chart.setDescription(new Description());

            final String [] labels = labelz.toArray(new String[0]);

            ValueFormatter formatter = new ValueFormatter() {
                @Override
                public String getAxisLabel(float value, AxisBase axis) {
                    return labels[(int) value];
                }
            };
            XAxis xAxis = chart.getXAxis();
            xAxis.setLabelRotationAngle(45);
            xAxis.setGranularity(5f);
            xAxis.setValueFormatter(formatter);

        }
    }

}
