package com.example.auctionapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auctionapp.ConnectionClass;
import com.example.auctionapp.Model.ProductInformation;
import com.example.auctionapp.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SellFragment extends Fragment implements View.OnClickListener {

    Context context;

    EditText editTextProductName;
    EditText editTextDescription;
    EditText editTextStartingPrice;
    EditText editTextFinalPrice;
    EditText editTextTime;
    TextView textViewSubcategory;

    public Connection con;
    ConnectionClass connectionClass;

    Button buttonPut;

    Spinner spinnerCategory;
    Spinner spinnerSubcategory;

    boolean selectedCategory = false;
    boolean selectedSubcategory = false;

    String category;
    String subcategory;

    List<String> listCategory = new ArrayList<String>();
    List<String> listSubcategory = new ArrayList<String>();

    int id_category = 24007;
    String login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sell, container, false);

        connectionClass = new ConnectionClass();
        con = connectionClass.getConnection();

        editTextProductName = view.findViewById(R.id.editTextProductName);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextStartingPrice = view.findViewById(R.id.editTextStartingPrice);
        editTextFinalPrice = view.findViewById(R.id.editTextFinalPrice);
        editTextTime = view.findViewById(R.id.editTextTime);
        buttonPut = view.findViewById(R.id.buttonPut);
        buttonPut.setOnClickListener(this);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);
        spinnerSubcategory = view.findViewById(R.id.spinnerSubcategory);
        textViewSubcategory = view.findViewById(R.id.textViewSubcategory);

        context = getContext();

        setupCategories();

        return view;
    }

    public void putProductForAuction(){
        String productName = editTextProductName.getText().toString();
        String description = editTextDescription.getText().toString().trim();
        String startingPriceString = editTextStartingPrice.getText().toString().trim();
        String finalPriceString = editTextFinalPrice.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();
        if(productName.matches("")) {
            Toast.makeText(context, "Enter product name", Toast.LENGTH_LONG).show();
        }
        else if(startingPriceString.matches("")){
            Toast.makeText(context, "Enter starting price", Toast.LENGTH_LONG).show();
        }
        else if(finalPriceString.matches("")){
            Toast.makeText(context, "Enter final price", Toast.LENGTH_LONG).show();
        }
        else if(time.matches("")) {
            Toast.makeText(context, "Enter time", Toast.LENGTH_LONG).show();
        }
        else{
           //ProductInformation productInformation = new ProductInformation(1, productName, description, startingPrice, finalPrice, 0, 0, login, "null", time);
                float startingPrice = Float.valueOf(startingPriceString);
                float finalPrice = Float.valueOf(finalPriceString);
            try {
                if (con == null) {
                    Toast.makeText(context, "Check your internet access!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String query =  "DECLARE @Result TINYINT;\n" +
                            "\n" + "EXEC @Result = dbo.CreateProduct @NewProductName = '" + productName +
                            "', @NewDescription = '" + description + "', @NewStartingPrice = "+
                            startingPrice + ", @NewFinishPrice =" + finalPrice +
                            ", @NewStartDate ='2020-01-01', @NewFinishDate = '2020-01-03', @NewSold = 0, @NewIdCategory ="
                            + id_category + ", @NewSellerLogin = null, @NewBuyerLogin = null;" +
                            "SELECT @Result Status;";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs.next())
                    {
                            Toast.makeText(context,"Adding product successful", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(context,"Adding product unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }
            }
            catch (Exception e)
            {
                Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }


    public void setupCategories(){

        try {
            if (con == null) {
                Toast.makeText(context, "Check your internet access!", Toast.LENGTH_SHORT).show();

            } else {
                String query = "select name from dbo.Categories where category is null;";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                listCategory.clear();
                listCategory.add("*Select Category*");
                while (rs.next()) {
                    listCategory.add(rs.getString("name"));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, listCategory);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategory.setAdapter(adapter);
                spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int idSub;
                        category = parent.getItemAtPosition(position).toString();
                        try {
                            String queryIdSub = "select id from dbo.Categories where name ='" + category + "';";
                            Statement stmt2 = con.createStatement();
                            ResultSet rs2 = stmt2.executeQuery(queryIdSub);
                            if (rs2.next()) {
                                selectedCategory = true;
                                idSub = rs2.getInt("id");
                                try{
                                    String querySub = "select name from dbo.Categories where category =" + idSub + ";";
                                    Statement stmt3 = con.createStatement();
                                    ResultSet rs3 = stmt3.executeQuery(querySub);
                                    listSubcategory.clear();
                                    listSubcategory.add("*Select Category*");
                                    while (rs3.next()) {
                                        listSubcategory.add(rs3.getString("name"));
                                    }
                                    ArrayAdapter<String> adapterSub = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, listSubcategory);
                                    adapterSub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinnerSubcategory.setAdapter(adapterSub);
                                    spinnerSubcategory.setVisibility(View.VISIBLE);
                                    textViewSubcategory.setVisibility(View.VISIBLE);
                                    spinnerSubcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            subcategory = parent.getItemAtPosition(position).toString();
                                            if(!subcategory.equals("*Select Category*")){
                                                selectedSubcategory = true;
                                            }
                                            else{
                                                Toast.makeText(context,"Select Subcategory!", Toast.LENGTH_LONG).show();
                                                selectedSubcategory = false;
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }
                                catch (Exception e3){
                                    Toast.makeText(context,e3.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(context, "Select category", Toast.LENGTH_LONG).show();
                                spinnerSubcategory.setVisibility(View.INVISIBLE);
                                textViewSubcategory.setVisibility(View.INVISIBLE);
                                selectedCategory = false;
                            }
                        }
                        catch (Exception e2){
                            Toast.makeText(context,e2.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
        }
        catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public void onClick(View v) {
        if(v==buttonPut){
            if(selectedCategory && selectedSubcategory){
                putProductForAuction();
            }
        }
    }


}
