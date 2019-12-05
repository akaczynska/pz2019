package com.example.auctionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Connection;

public class RegisterActivity extends AppCompatActivity {

    public Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void openAuction (View view){
        if(checkNick()&&checkPassword()&&checkCorrectPassword()) {
            addUserToDatabase();
            startActivity(new Intent(this, AuctionActivity.class));
        }
    }

    //Funkcja sprawdzajaca czy nick zostal wpisany i czy sie powtarza w bazie danych
    public boolean checkNick()
    {

        return true;
    }
    //Funkcja sprawdzajaca czy haslo zostalo wpisane spelnia wymagania (np. duze i male litery)
    public boolean checkPassword()
    {

        return true;
    }

    //Funkcja sprawdzajaca czy potwierdzenie hasla zostalo wpisane i czy jest takie samo
    public boolean checkCorrectPassword()
    {

        return true;
    }
    //Dodawanie uzytkownika do bazy danych
    public void addUserToDatabase(){

    }
}
