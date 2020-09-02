package com.spect.mytunas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class ClassroomActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("My Tunas");

            DrawerLayout drawerLayout;
            drawerLayout = findViewById(R.id.drawerLayout);
            NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
            navigationView.setNavigationItemSelectedListener(this);
            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();
        }

        setContentView(R.layout.activity_kelas);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String itemName = (String) menuItem.getTitle();

        closeDrawer();

        switch (menuItem.getItemId()){
            case R.id.nav_profile:
                startActivity(new Intent(ClassroomActivity.this, ProfileActivity.class));
                break;
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