package com.example.auctionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openAuction (View view) {
        if (checkLogin()) {
            startActivity(new Intent(this, AuctionActivity.class));
        }
    }

    //Funkcja sprawdzajaca czy login i haslo poprawne
    public boolean checkLogin(){
        return true;
    }
}
