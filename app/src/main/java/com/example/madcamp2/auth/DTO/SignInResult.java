package com.example.madcamp2.auth.DTO;

import com.google.gson.annotations.SerializedName;

public class SignInResult {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("message")
    private String message;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
