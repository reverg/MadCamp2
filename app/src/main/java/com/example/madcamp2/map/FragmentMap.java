package com.example.madcamp2.map;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.madcamp2.R;
import com.example.madcamp2.RetrofitClient;
import com.example.madcamp2.auth.TokenManager;
import com.example.madcamp2.community.DTO.User;
import com.naver.maps.geometry.LatLng;

import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.MapFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMap extends Fragment implements OnMapReadyCallback {
    View v;
    Button startButton;
    //Button infoButton;
    boolean isRunning = false;
    Button stopButton;
    TextView distanceInfo, speedInfo;
    Chronometer chronometer;

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
        chronometer = v.findViewById(R.id.chronometer);
        chronometer.setFormat("Time: %s");

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
                    isRunning = true;
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    stopButton.setBackgroundColor(Color.parseColor("#cc8472"));
                    startButton.setBackgroundColor(Color.parseColor("#dee8ff"));
                    startButton.setTextColor(Color.parseColor("#ffffff"));
                    startButton.setEnabled(false);
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRunning) {
                    isRunning = false;
                    pathMarkers = new ArrayList<>();
                    distanceInfo.setText("Distance: 0m");
                    speedInfo.setText("Speed: 0km/h");
                    String token = TokenManager.getToken(getActivity(), TokenManager.TOKEN_KEY);
                    // sendData(token, totalDistance);
                    sendData(token, totalDistance);
                    totalDistance = 0;
                    startButton.setEnabled(true);
                    startButton.setBackgroundColor(Color.parseColor("#79a1fc"));
                    stopButton.setBackgroundColor(Color.parseColor("#eac9c1"));
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.stop();
                }
            }
        });
        return v;
    }

    public void sendData(String token, double distance) {
        Call<User> callCommunity = RetrofitClient.getMapService().sendRunningData(token, distance);
        callCommunity.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "success = " + response.code(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getActivity(), "error = " + String.valueOf(response.code()),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Send Data", t.getMessage());
                Toast.makeText(getActivity(), "Send Data Fail", Toast.LENGTH_LONG).show();
            }
        });
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
                    double updateSec = ((currentMillis - previousMillis) / 1000.0);
                    speed_2 = speed_1;
                    speed_1 = speed_0;
                    speed_0 = 3.6 * (moveDistance / updateSec);
                    double speed = (speed_2 + speed_1 + speed_0) / 3.0;
                    if (pathMarkers.size() > 2) {
                        path.setCoords(pathMarkers);
                        path.setMap(naverMap);
                        distanceInfo.setText("Distance: " + String.format("%.1f", totalDistance) + "m");
                        speedInfo.setText("Speed: " + String.format("%.1f", speed) + "km/h");
                    }
                }
            }
        });
        currentNaverMap = naverMap;
    }
}