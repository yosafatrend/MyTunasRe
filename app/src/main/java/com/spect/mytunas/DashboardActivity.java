package com.spect.mytunas;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("My Tunas");

            drawerLayout = findViewById(R.id.drawerLayout);
            NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
            navigationView.setNavigationItemSelectedListener(this);
            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_news, R.id.navigation_sekolah)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem Item) {
        String itemName = (String) Item.getTitle();

        closeDrawer();

        switch (Item.getItemId()){
            case R.id.nav_setting:
                break;
            case R.id.nav_Logout:
                break;
        }
        return false;
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);

    }
}
