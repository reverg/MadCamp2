package com.example.madcamp2.community.DTO;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("_id")
    private int userId;
    @SerializedName("username")
    private String userName;
    @SerializedName("displayName")
    private String displayName;
    @SerializedName("photoUrl")
    private String userPhotoUrl;
    @SerializedName("totalDistance")
    private double userDistance;
    @SerializedName("joinedAt")
    private String userJoinedDate;

    public User(String userName, int userDistance) {
        this.userName = userName;
        this.userDistance = userDistance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getUserJoinedDate() {
        return userJoinedDate;
    }

    public void setUserJoinedDate(String userJoinedDate) {
        this.userJoinedDate = userJoinedDate;
    }

    public double getUserDistance() {
        return userDistance;
    }
}
