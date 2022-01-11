package com.example.madcamp2.record.DTO;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;

public class Record {
    ArrayList<LatLng> pathMarkers;

    String recordName;
    String recordInfo;
    double totalDistance = 0;
    double maxSpeed = 0;
    double totalTime = 0;

    public String getRecordName() {
        return recordName;
    }

    public String getRecordInfo() {
        return recordInfo;
    }

    public ArrayList<LatLng> getPathMarkers() {
        return pathMarkers;
    }

    public double getTotalDistance(){
        return totalDistance;
    }

    public double getMaxSpeed(){
        return maxSpeed;
    }

    public double getTotalTime(){
        return totalTime;
    }
}
