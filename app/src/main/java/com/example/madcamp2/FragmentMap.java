package com.example.madcamp2;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;

import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.MapFragment;

import java.util.ArrayList;

public class FragmentMap extends Fragment implements OnMapReadyCallback {
    View v;
    Button startButton;
    //Button infoButton;
    boolean isRunning = false;
    Button stopButton;
    TextView distanceInfo, speedInfo;

    NaverMap currentNaverMap;
    LatLng currentPosition = null;
    LatLng previousPosition = null;
    ArrayList<LatLng> pathMarkers;
    double speed_2 = 0;
    double speed_1 = 0;
    double speed_0 = 0;

    double totalDistance = 0;
    long currentMillis = System.currentTimeMillis();
    long previousMillis = currentMillis;

    private double latitude = 0;
    private double longitude = 0;

    private FusedLocationSource fusedLocationSource;
    private PathOverlay path;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;

    public FragmentMap() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fusedLocationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        v = inflater.inflate(R.layout.fragment_map, container, false);
        startButton = v.findViewById(R.id.start_button);
        stopButton = v.findViewById(R.id.stop_button);
        distanceInfo = v.findViewById(R.id.distance_info);
        speedInfo = v.findViewById(R.id.speed_info);
        //infoButton = v.findViewById(R.id.info_button);
        //infoButton.setVisibility(View.GONE);

        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = com.naver.maps.map.MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        path = new PathOverlay();
        path.setColor(Color.BLUE);
        pathMarkers = new ArrayList<>();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    //infoButton.setVisibility(View.VISIBLE);
                    // startButton.setText("Stop");
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                    isRunning = true;
                } else {
                    //infoButton.setVisibility(View.GONE);
                    // startButton.setText("Start");
                    isRunning = false;
                    pathMarkers = new ArrayList<>();
                    distanceInfo.setText("Distance: 0m");
                    speedInfo.setText("Speed: 0km/h");
                    stopButton.setEnabled(false);
                    startButton.setEnabled(true);
                    //infoButton.setText("Distance: 0m\nSpeed: 0km/h");
                    totalDistance = 0;
                }
            }
        });
        return v;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        naverMap.setLocationSource(fusedLocationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Face);
        naverMap.getUiSettings().setLocationButtonEnabled(true);
        naverMap.addOnLocationChangeListener(new NaverMap.OnLocationChangeListener() {
            @Override
            public void onLocationChange(@NonNull Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                previousPosition = currentPosition;
                currentPosition = new LatLng(latitude, longitude);

                previousMillis = currentMillis;
                currentMillis = System.currentTimeMillis();

                if (isRunning) {
                    pathMarkers.add(currentPosition);
                    double moveDistance = previousPosition.distanceTo(currentPosition);
                    totalDistance += moveDistance;
                    double updateSec = ((currentMillis - previousMillis) / ((double) 1000.0));
                    speed_2 = speed_1;
                    speed_1 = speed_0;
                    speed_0 = 3.6 * (moveDistance / updateSec);
                    double speed = (speed_2 + speed_1 + speed_0) / 3.0;
                    if (pathMarkers.size() > 2) {
                        path.setCoords(pathMarkers);
                        path.setMap(naverMap);
                        distanceInfo.setText("Distance: " + String.format("%.1f", totalDistance));
                        speedInfo.setText("Speed: " + String.format("%.1f", speed) + "km/h");
                        //infoButton.setText("Distance: " + String.format("%.1f", totalDistance) + "m\nSpeed:" + String.format("%.1f", speed) + "km/h");
                    }
                }
            }
        });
        currentNaverMap = naverMap;
    }
}