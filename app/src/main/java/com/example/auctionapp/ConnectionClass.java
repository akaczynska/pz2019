package com.example.auctionapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    Connection connection;
    public ConnectionClass(){
        connection=null;
    }
    public ConnectionClass(Connection connection) {
        this.connection = connection;
    }

    @SuppressLint("NewApi")
    public Connection getConnection()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String connectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://auctionapp.database.windows.net:1433;DatabaseName=AuctionAppDB;user=kaczynskaa@auctionapp;password=Slash1998;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(connectionURL);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}
