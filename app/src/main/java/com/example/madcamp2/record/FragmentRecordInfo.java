package com.example.madcamp2.record;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.madcamp2.record.DTO.Record;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRecordInfo extends Fragment implements OnMapReadyCallback {
    View v;

    NaverMap currentNaverMap;

    List<LatLng> pathMarkers;
    TextView distanceInfo;
    TextView speedInfo;
    TextView recordInfo;
    TextView recordDate;

    double totalDistance = 0;
    double maxSpeed = 0;
    double totalTime = 0;

    String info;
    String date;

    private PathOverlay path;

    public FragmentRecordInfo(Record record) {
        this.pathMarkers = record.getPathMarkers();
        this.totalDistance = record.getTotalDistance();
        this.maxSpeed = record.getMaxSpeed();
        this.totalTime = record.getTotalTime();
        this.date = record.getRecordDate();
        this.info = record.getRecordInfo();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_record_info, container, false);
        distanceInfo = v.findViewById(R.id.record_distance);
        speedInfo = v.findViewById(R.id.record_speed);
        recordDate = v.findViewById(R.id.record_date);
        recordInfo = v.findViewById(R.id.record_info);

        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        path = new PathOverlay();
        path.setColor(Color.BLUE);

        distanceInfo.setText("Distance: " + String.format("%.1f", totalDistance) + "m");
        speedInfo.setText("Max Speed: " + String.format("%.1f", maxSpeed) + "km/h");

        recordDate.setText(date);
        recordInfo.setText(info);

        return v;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        LatLng startLatLng = pathMarkers.get(0);
        LatLng endLatLng = pathMarkers.get(pathMarkers.size() - 1);
        LatLng middleLatLng = new LatLng((startLatLng.latitude + endLatLng.latitude) / 2, (startLatLng.longitude + endLatLng.longitude) / 2);
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(middleLatLng);
        naverMap.moveCamera(cameraUpdate);

        path.setCoords(pathMarkers);
        path.setMap(naverMap);


        currentNaverMap = naverMap;
    }
}
