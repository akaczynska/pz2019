package com.example.auctionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    public final static String EXTRA_LOGIN = "";

    Connection con;
    ConnectionClass connectionClass;
    EditText editText_login;
    EditText editText_password;
    String login;
    String password;
    Boolean loginStatus = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        connectionClass = new ConnectionClass();
        con = connectionClass.getConnection();
        editText_login = findViewById(R.id.editText_login);
        editText_password = findViewById(R.id.editText_haslo);

    }

    public void openAuction (View view) {
        if (checkLogin()) {
            Intent intent = new Intent(this, AuctionActivity.class);
            intent.putExtra(EXTRA_LOGIN, login);
            startActivity(intent);
        }
    }

    //Funkcja sprawdzajaca czy login i haslo poprawne
    public boolean checkLogin(){
        //Toast.makeText(this,"janush" , Toast.LENGTH_SHORT).show();
        //Check internet access and database connection
        try {
            if (con == null) {
                Toast.makeText(this, "Check your internet access!", Toast.LENGTH_SHORT).show();
                return false;

            } else {
                login = editText_login.getText().toString();
                password = editText_password.getText().toString();
                //String query = "DECLARE @Result TINYINT; EXEC @Result = dbo.VerifyAccount @AccountName = 'Brian', @AccountPwd = 'Str0ngP4ssw0rd!'; SELECT @Result 'Status;";
                String query = "DECLARE @Result TINYINT;\n" +
                        "\n" +
                        "EXEC @Result = dbo.VerifyAccount @AccountName = '" + login + "', @AccountPwd = '" + password + "';\n" +

                        "SELECT @Result Status;";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs.next())
                {
                    if(rs.getInt("Status")==0)
                    {
                        loginStatus=true;
                        Toast.makeText(this,"Login successful", Toast.LENGTH_LONG).show();
                        con.close();
                    }
                    else if(rs.getInt("Status")==1)
                        Toast.makeText(this,"Login unsuccessful \n Invalid login or password", Toast.LENGTH_LONG).show();
                }
               else
                {
                    Toast.makeText(this,"Login unsuccessful", Toast.LENGTH_LONG).show();
                    loginStatus = false;
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return loginStatus;
    }

}
