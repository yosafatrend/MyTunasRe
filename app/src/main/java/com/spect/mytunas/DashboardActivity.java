package com.spect.mytunas;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    MeowBottomNavigation meo;
    DrawerLayout drawerLayout;
    private final static int ID_NEWS=1;
    private final static int ID_SEARCH=2;
    private final static int ID_HOME=3;
    private final static int ID_SCHOOLNEWS=4;
    private final static int ID_JOB=5;

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
        meo=(MeowBottomNavigation)findViewById(R.id.bottom_nav);
        meo.add(new MeowBottomNavigation.Model(1,R.drawable.ic_view_news_black_24dp));
        meo.add(new MeowBottomNavigation.Model(2,R.drawable.ic_search_black_24dp));
        meo.add(new MeowBottomNavigation.Model(3,R.drawable.ic_home_black_24dp));
        meo.add(new MeowBottomNavigation.Model(4,R.drawable.ic_info_sekolah_black_24dp));
        meo.add(new MeowBottomNavigation.Model(5,R.drawable.ic_loker_black_24dp));

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        meo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });
        meo.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
        meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment select_fragment=null;
                switch (item.getId()){
                    case ID_NEWS:
                        Toast.makeText(getApplicationContext(),"Berita",Toast.LENGTH_SHORT).show();
                        select_fragment=new NewsFragment();
                        break;
                    case ID_SEARCH:
                        Toast.makeText(getApplicationContext(),"Pencarian",Toast.LENGTH_SHORT).show();
                        select_fragment=new SearchFragment();
                        break;
                    case ID_HOME:
                        Toast.makeText(getApplicationContext(),"Halaman Utama",Toast.LENGTH_SHORT).show();
                        select_fragment=new HomeFragment();
                        break;
                    case ID_SCHOOLNEWS:
                        Toast.makeText(getApplicationContext(),"Info Sekolah",Toast.LENGTH_SHORT).show();
                        select_fragment=new SchoolFragment();
                        break;
                    case ID_JOB:
                        Toast.makeText(getApplicationContext(),"Lowongan Pekerjaan",Toast.LENGTH_SHORT).show();
                        select_fragment=new JobFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,select_fragment).commit();
            }
        });

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
            case R.id.nav_profile:
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
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
