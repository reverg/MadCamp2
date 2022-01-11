package com.example.madcamp2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;


import com.example.madcamp2.auth.SignInActivity;
import com.example.madcamp2.auth.TokenManager;
import com.example.madcamp2.community.FragmentCommunity;
import com.example.madcamp2.map.FragmentMap;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabLayout.Tab mapTab;
    TabLayout.Tab communityTab;
    FragmentMap fragmentMap;
    FragmentCommunity fragmentCommunity;

    private DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);

        fragmentMap = new FragmentMap();
        fragmentCommunity = new FragmentCommunity();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragmentMap).commit();

        mapTab = tabLayout.newTab().setText("Map");
        communityTab = tabLayout.newTab().setText("Community");

        tabLayout.addTab(mapTab);
        tabLayout.addTab(communityTab);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#abc4ff"));
        tabLayout.setTabTextColors(Color.parseColor("#bdbdbd"), Color.parseColor("#abc4ff"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if (position == 0)
                    selected = fragmentMap;
                else
                    selected = fragmentCommunity;
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setCheckedItem(R.id.nav_home);
        drawerLayout = findViewById(R.id.drawerLayout);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();

                int id = item.getItemId();

                if (id == R.id.nav_logout) {
                    TokenManager.setToken(getApplicationContext(), TokenManager.TOKEN_KEY, "");

                    Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }

                return true;
            }
        });
    }
}