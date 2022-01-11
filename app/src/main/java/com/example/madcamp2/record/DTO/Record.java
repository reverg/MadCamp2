package com.example.madcamp2.record.DTO;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Record {
    List<LatLng> pathMarkers;

    String recordName;
    String recordInfo;
    String createdAt;
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

    /*
    public class jsLatLng {
        double lat;
        double lng;
    }
    public void setPathMarkers(ArrayList<jsLatLng> pathMarkers) {
        ArrayList<LatLng> list = new ArrayList<LatLng>();
        for (int i=0; i<pathMarkers.size(); i++) {
            LatLng coord = new LatLng(pathMarkers.get(i).lat, pathMarkers.get(i).lng);
            list.add(coord);
        }
        this.pathMarkers = list;
    }
     */

    public void setPathMarkers(ArrayList<LatLng> pathMarkers) {
        this.pathMarkers = pathMarkers;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public void setRecordInfo(String recordInfo) {
        this.recordInfo = recordInfo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
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
