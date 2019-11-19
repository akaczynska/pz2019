package com.example.auctionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openLogin (View view){
        startActivity(new Intent(this, LoginActivity.class));
    }
    public void openRegister (View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
