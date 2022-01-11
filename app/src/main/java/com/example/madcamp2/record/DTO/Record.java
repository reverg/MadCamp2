package com.example.madcamp2.record.DTO;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Record {
    //List<LatLng> pathMarkers;

    //@SerializedName("pathMarkers")
    //String path;

    @SerializedName("lan")
    List<Double> lan;

    @SerializedName("lng")
    List<Double> lng;

    String recordName;
    @SerializedName("info")
    String recordInfo;
    String createdAt;

    @SerializedName("distance")
    double totalDistance = 0;
    double maxSpeed = 0;
    @SerializedName("time")
    double totalTime = 0;

    public Record() {
        //pathMarkers = new ArrayList<>();
        //pathMarkers.add(new LatLng(37.57152, 126.97714));
        //pathMarkers.add(new LatLng(37.56607, 126.98268));
        //pathMarkers.add(new LatLng(37.56445, 126.97707));
        //pathMarkers.add(new LatLng(37.55855, 126.97822));

        recordName = "abc";
        recordInfo = "def";
        createdAt = "2020-10-10";
        totalDistance = 100;
        maxSpeed = 10;
        totalTime = 10;
    }

    public List<Double> getLan() {
        return lan;
    }

    public void setLan(List<Double> lan) {
        this.lan = lan;
    }

    public List<Double> getLng() {
        return lng;
    }

    public void setLng(List<Double> lng) {
        this.lng = lng;
    }

    /*
    // public String getPath() {
        return path;
    }

    //public void setPath(String path) {
        this.path = path;
                }
                */

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

    /*
    public void setPathMarkers(List<LatLng> pathMarkers) {
        this.pathMarkers = pathMarkers;
    }

    */

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

    /*
    public List<LatLng> getPathMarkers() {
        return pathMarkers;
    }

     */

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public String getRecordDate() {
        return createdAt;
    }
}
