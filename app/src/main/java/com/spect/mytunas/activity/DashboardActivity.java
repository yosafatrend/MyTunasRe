package com.spect.mytunas.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.spect.mytunas.fragment.JobFragment;
import com.spect.mytunas.R;
import com.spect.mytunas.fragment.HomeFragment;
import com.spect.mytunas.fragment.NewsFragment;
import com.spect.mytunas.fragment.SchoolFragment;
import com.spect.mytunas.fragment.SearchFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    private static final String TAG = DashboardActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#FFFFFF\">MyTunas</font>"));

            FirebaseMessaging.getInstance().subscribeToTopic("all");

            drawerLayout = findViewById(R.id.drawerLayout);
            NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
            navigationView.setNavigationItemSelectedListener(this);
            View headerView = navigationView.getHeaderView(0);
            TextView navUsername = (TextView) headerView.findViewById(R.id.tvTitle);
            TextView navNis = (TextView) headerView.findViewById(R.id.tvSubtitle);

            DatabaseReference siswa = FirebaseDatabase.getInstance().getReference("siswa");
            DatabaseReference childSiswa = siswa.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            childSiswa.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Toast.makeText(getApplicationContext(), snapshot.child("email").getValue().toString(), Toast.LENGTH_SHORT).show();
                    try {
                        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
                        View headerView = navigationView.getHeaderView(0);
                        TextView navUsername = headerView.findViewById(R.id.tvTitle);
                        TextView navNis = headerView.findViewById(R.id.tvSubtitle);
                        CircleImageView imgUser = headerView.findViewById(R.id.imgUser);
                        navUsername.setText(snapshot.child("nama_lengkap").getValue().toString());
                        navNis.setText(snapshot.child("nis").getValue().toString());
                        FirebaseMessaging.getInstance().subscribeToTopic(snapshot.child("kelas").getValue().toString().replaceAll("\\s+",""));
                        Glide.with(getApplicationContext())
                                .load(snapshot.child("imgUri").getValue().toString()).into(imgUser);
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),  ""+ e, Toast.LENGTH_SHORT);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();
        }
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_nav_menu);
        fragmentManager = getSupportFragmentManager();

        //Untuk inisialisasi fragment pertama kali
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        //Memberikan listener saat menu item_user di bottom navigation diklik
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.navigation_news:
                        fragment = new NewsFragment();
                        break;
                    case R.id.navigation_search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.navigation_sekolah:
                        fragment = new SchoolFragment();
                        break;
                    case R.id.navigation_job:
                        fragment = new JobFragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isOpen())
            drawerLayout.close();
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem Item) {
        String itemName = (String) Item.getTitle();

        closeDrawer();

        switch (Item.getItemId()) {
            case R.id.nav_profile:
                startActivity(new Intent(DashboardActivity.this, ProfileUserActivity.class));
                break;
            case R.id.nav_about:
                startActivity(new Intent(DashboardActivity.this,AboutActivity.class));
                break;
            case R.id.nav_Logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DashboardActivity.this,AuthActivity.class));
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
