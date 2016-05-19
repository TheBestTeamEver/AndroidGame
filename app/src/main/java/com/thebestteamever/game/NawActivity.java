package com.thebestteamever.game;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;

import com.thebestteamever.game.naw_fragments.PlayFragment;
import com.thebestteamever.game.naw_fragments.SettingsFragment;
import com.thebestteamever.game.naw_fragments.TopFragment;

public class NawActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    PlayFragment playFragment;
    SettingsFragment settingsFragment;
    TopFragment topFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NawActivity.this, GameActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Фрагменты
        playFragment = new PlayFragment();
        settingsFragment = new SettingsFragment();
        topFragment = new TopFragment();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_game) {
            fragmentTransaction.replace(R.id.container, playFragment);
        } else if (id == R.id.nav_top) {
            fragmentTransaction.replace(R.id.container, topFragment);
        } else if (id == R.id.nav_settings) {
            fragmentTransaction.replace(R.id.container, settingsFragment);
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(NawActivity.this, LoginActivity.class));
            finish();
        }

        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean OnRadioChecked(View view){
        final RadioButton radio1 = (RadioButton)findViewById(R.id.radioButton);
        final RadioButton radio2 = (RadioButton)findViewById(R.id.radioButton2);
        if (radio1.isChecked()){
            radio1.setChecked(true);
            radio2.setChecked(false);
            int themeColor = getResources().getColor(R.color.themeColorBlue);
            themeColor = Color.parseColor("#5EBA7D");
        } else {
            radio1.setChecked(false);
            radio2.setChecked(true);
            int themeColor = getResources().getColor(R.color.themeColorBlue);
            themeColor = Color.parseColor("#2BE7E5");
        }
       return true;
    }
}
