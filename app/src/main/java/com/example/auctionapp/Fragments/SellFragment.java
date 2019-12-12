package com.example.auctionapp.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.auctionapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SellFragment extends Fragment implements View.OnClickListener {

    EditText editTextProductName;
    EditText editTextDescription;
    EditText editTextStartingPrice;
    EditText editTextFinalPrice;
    EditText editTextTime;

    Button buttonPut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sell, container, false);

        editTextProductName = view.findViewById(R.id.editTextProductName);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextStartingPrice = view.findViewById(R.id.editTextStartingPrice);
        editTextFinalPrice = view.findViewById(R.id.editTextFinalPrice);
        editTextTime = view.findViewById(R.id.editTextTime);
        buttonPut = view.findViewById(R.id.buttonPut);

        return inflater.inflate(R.layout.fragment_sell, container, false);
    }

    public void putProductForAuction(){
        String productName = editTextProductName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String startingPrice = editTextStartingPrice.getText().toString().trim();
        String finalPrice = editTextFinalPrice.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();
        //if(TextUtils.isEmpty(productName)) {
            Toast.makeText(getContext(), "Test", Toast.LENGTH_LONG).show();
        //}
    }

    @Override
    public void onClick(View v) {
        if(v==buttonPut){
            putProductForAuction();;
        }
    }
}
