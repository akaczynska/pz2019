package com.example.auctionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    Connection con;
    ConnectionClass connectionClass;
    EditText editText_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        connectionClass = new ConnectionClass();
        con = connectionClass.getConnection();
        editText_login = (EditText)findViewById(R.id.editText_login);

        setContentView(R.layout.activity_login);
    }

    public void openAuction (View view) {
        if (checkLogin()) {
            startActivity(new Intent(this, AuctionActivity.class));
        }
    }

    //Funkcja sprawdzajaca czy login i haslo poprawne
    public boolean checkLogin(){
        //Check internet access and database connection
            if (con != null) {
                //Get login from application
                //String login = editText_login.getText().toString();
                return true;
            }
             else {
                Toast.makeText(this, "Check your internet access!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

}
