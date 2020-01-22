package com.example.auctionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.auctionapp.Fragments.BidFragment;
import com.example.auctionapp.Fragments.SellFragment;
import com.example.auctionapp.Model.ProductInformation;
import com.google.android.material.navigation.NavigationView;



public class AuctionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView textViewNick;
    public ProductInformation productInformation;
    public final static String EXTRA_LOGIN = "";
    Fragment sellfragment;
    String productID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);

        Intent intent = getIntent();
        String login = intent.getStringExtra(LoginActivity.EXTRA_LOGIN);


        Bundle bundle = new Bundle();
        bundle.putString("EXTRA_LOGIN",login);
        sellfragment = new SellFragment();
        sellfragment.setArguments(bundle);

        textViewNick = (TextView)findViewById(R.id.textViewNick);
        textViewNick.setText(login);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar
                , R.string.navigation_draver_close, R.string.navigation_draver_open);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BidFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_bid);
        }

    }

/*    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }*/

    //Menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {


            case R.id.nav_sell:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        sellfragment).commit();
                break;

            case R.id.nav_bid:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BidFragment()).commit();
                break;
        }

            item.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;

    }


    @Override public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_view);
        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }
    }

   public void openHistory (View view){
        Intent intent = new Intent (this, HistoryActivity.class);
        //intent.putExtra(EXTRA_PRODUCTID,productID);
        startActivity(intent);
        //startActivity(new Intent(this, HistoryActivity.class));
    }
}
