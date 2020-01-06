package com.example.auctionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    public Connection con;
    ConnectionClass connectionClass;
    EditText editText_login;
    EditText editText_password;
    EditText editText_rpassword;
    Boolean nickStatus;
    String login;
    String password;
    String rpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        connectionClass = new ConnectionClass();
        con = connectionClass.getConnection();
        editText_login = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_haslo);
        editText_rpassword = findViewById(R.id.editText_rhaslo);
    }

    public void openAuction (View view){
        if(checkNick() && checkPassword() && checkCorrectPassword()) {
            addUserToDatabase();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    //Funkcja sprawdzajaca czy nick zostal wpisany i czy sie powtarza w bazie danych
    public boolean checkNick()
    {
        nickStatus = false;
        try {
            if (con == null) {
                Toast.makeText(this, "Check your internet access!", Toast.LENGTH_SHORT).show();
                return false;

            } else {
                login = editText_login.getText().toString();
                if (login.compareTo("")==0)
                    return false;
                String query = "select AccountName from dbo.SecurityAccounts\n" +
                        "where AccountName = '" + login + "' ;";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs.next())
                {
                    if(rs.getString("AccountName").compareTo(login)==0)
                    {
                        nickStatus=false;
                        Toast.makeText(this,"Registration unsuccessful \n Nickname occupied", Toast.LENGTH_LONG).show();

                    }
                    else if(!(rs.getString("AccountName").compareTo(login)==0))
                        Toast.makeText(this,"ed", Toast.LENGTH_LONG).show();
                    //con.close();
                }
                else
                {
                    //Toast.makeText(this,"Login successful", Toast.LENGTH_LONG).show();
                    nickStatus = true;
                    //con.close();
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return nickStatus;
    }
    //Funkcja sprawdzajaca czy haslo zostalo wpisane spelnia wymagania (np. duze i male litery)
    public boolean checkPassword()
    {

        password = editText_password.getText().toString();
        rpassword = editText_rpassword.getText().toString();
        Pattern p = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
        Matcher m = p.matcher(password);
        if(password.length()>7 && !password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase()) && m.find())
            return true;
        else {
            Toast.makeText(this,"Password does not meet the security criteria", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    //Funkcja sprawdzajaca czy potwierdzenie hasla zostalo wpisane i czy jest takie samo
    public boolean checkCorrectPassword()
    {
        password = editText_password.getText().toString();
        rpassword = editText_rpassword.getText().toString();
        if(password.compareTo(rpassword)==0)
            return true;
        else {
            Toast.makeText(this,"Passwords do not match", Toast.LENGTH_LONG).show();
            return false;
        }
    }
    //Dodawanie uzytkownika do bazy danych
    public void addUserToDatabase(){
        try {
            if (con == null) {
                Toast.makeText(this, "Check your internet access!", Toast.LENGTH_SHORT).show();
            }
            else {
                String query = "EXEC dbo.CreateAccount @NewAccountName = '" + login + "', @NewAccountPwd = '" + password + "';";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs.next())
                {
                    if(rs.getString("AccountName").compareTo(login)==0)
                    {
                        Toast.makeText(this,"Registration successful", Toast.LENGTH_LONG).show();
                        //con.close();
                    }
                    else
                        Toast.makeText(this,"Registration unsuccessful \n Nickname occupied", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this,"Registration unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
