package com.example.madcamp2.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.auth0.android.jwt.JWT;

public class TokenManager {
    public static final String PREFERENCE_NAME = "jwt_token";
    public static final String TOKEN_KEY = "jwt_token";

    private static final String DEFAULT_VALUE_STRING = "";

    public static SharedPreferences getTokenManager(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static void setToken(Context context, String key, String token) {
        SharedPreferences prefs = getTokenManager(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(key, token);
        editor.commit();
    }

    public static String getToken(Context context, String key) {
        SharedPreferences prefs = getTokenManager(context);
        String resp = prefs.getString(key, "");

        return resp;
    }

    public static int getUserId(Context context, String token) {
        JWT jwt = new JWT(token);
        return jwt.getClaim("_id").asInt();
    }
}
