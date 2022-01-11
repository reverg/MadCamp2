package com.example.madcamp2.record.DTO;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Record {
    List<LatLng> pathMarkers;

    String recordName;
    String recordInfo;
    double totalDistance = 0;
    double maxSpeed = 0;
    double totalTime = 0;

    public Record() {
        pathMarkers = new ArrayList<>();
        pathMarkers.add(new LatLng(37.57152, 126.97714));
        pathMarkers.add(new LatLng(37.56607, 126.98268));
        pathMarkers.add(new LatLng(37.56445, 126.97707));
        pathMarkers.add(new LatLng(37.55855, 126.97822));

        recordName = "abc";
        recordInfo = "def";
        totalDistance = 100;
        maxSpeed = 10;
        totalTime = 10;
    }

    public String getRecordName() {
        return recordName;
    }

    public String getRecordInfo() {
        return recordInfo;
    }

    public List<LatLng> getPathMarkers() {
        return pathMarkers;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getTotalTime() {
        return totalTime;
    }
}
