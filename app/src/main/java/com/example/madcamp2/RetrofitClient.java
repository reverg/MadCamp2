package com.example.madcamp2;

import com.example.madcamp2.community.CommunityService;
import com.example.madcamp2.auth.SignInService;
import com.example.madcamp2.map.MapService;
import com.example.madcamp2.record.RecordService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.249.18.69:443";
    private static Retrofit retrofit = null;

    private RetrofitClient() {
    }

    private static Retrofit getRetrofit() {
        // Json 응답을 객체로 변환
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;

    }

    public static SignInService getSignInService() {
        SignInService service = getRetrofit().create(SignInService.class);
        return service;
    }

    public static CommunityService getCommunityService() {
        CommunityService service = getRetrofit().create(CommunityService.class);
        return service;
    }

    public static MapService getMapService() {
        MapService service = getRetrofit().create(MapService.class);
        return service;
    }

    public static RecordService getRecordService() {
        RecordService service = getRetrofit().create(RecordService.class);
        return service;
    }
}
