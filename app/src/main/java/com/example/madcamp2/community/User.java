package com.example.madcamp2.community;

public class User {
    private int userId;
    private String userName;
    private String displayName;
    private String userPassword;
    private String userPhotoUrl;
    private String userJoinedDate;
    private int userDistance;

    public User(String userName, int userDistance) {
        this.userName = userName;
        this.userDistance = userDistance;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserDistance() {
        return userDistance;
    }

}
