package com.example.madcamp2;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;


import com.google.android.material.tabs.TabLayout;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    FragmentManager fm;
    boolean isPermissionGiven = false;

    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checkAndRequestPermissions();

        tabLayout = findViewById(R.id.tabs);
        pager2 = findViewById(R.id.view_pager_2);

        fm = getSupportFragmentManager();

        tabLayout.addTab(tabLayout.newTab().setText("Contacts"));
        tabLayout.addTab(tabLayout.newTab().setText("Gallery"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        setAdapter();
        if (isPermissionGiven) {

        }

    }

    private void setAdapter() {
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);
    }

    // function to check permission
    private void checkAndRequestPermissions() {
        /*int WExtstorePermission = ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ActivityCompat.checkSelfPermission(this, CAMERA);
        int WcontactPermission = ActivityCompat.checkSelfPermission(this, WRITE_CONTACTS);
        int RcontactPermission = ActivityCompat.checkSelfPermission(this, READ_CONTACTS);
        int VibratePermission = ActivityCompat.checkSelfPermission(this, VIBRATE);
        int CallPermission = ActivityCompat.checkSelfPermission(this, CALL_PHONE);
        int ActivityPermission = ActivityCompat.checkSelfPermission(this, ACTIVITY_RECOGNITION);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(CAMERA);
            isPermissionGiven = false;
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(WRITE_EXTERNAL_STORAGE);
            isPermissionGiven = false;
        }

        if (WcontactPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(WRITE_CONTACTS);
            isPermissionGiven = false;
        }

        if (RcontactPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(READ_CONTACTS);
            isPermissionGiven = false;
        }

        if (VibratePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(VIBRATE);
            isPermissionGiven = false;
        }

        if (CallPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(CALL_PHONE);
            isPermissionGiven = false;
        }

        for (int i=0; i < listPermissionsNeeded.size(); i++) {
            Log.d("Permission Check", String.valueOf(listPermissionsNeeded.get(i)));
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    PERMISSION_REQUEST_CODE);
        } else {
            isPermissionGiven = true;
        }

         */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                boolean isPermissionAllGranted = false;

                if (grantResults.length > 0 && permissions.length == grantResults.length) {
                    for (int i = 0; i < permissions.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            isPermissionAllGranted = true;
                        } else {
                            isPermissionAllGranted = false;
                        }
                    }
                } else {
                    isPermissionAllGranted = true;
                }

                if (isPermissionAllGranted) {
                    setAdapter();
                }
                break;
        }
    }

    public void replaceFragments(String imgPath) {
        /*
        ImageDetailFragment newFragment = ImageDetailFragment.newInstance(imgPath);
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.fragment_second, newFragment, null);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
         */
    }
}