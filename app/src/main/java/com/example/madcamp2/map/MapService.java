package com.example.madcamp2.map;

import com.example.madcamp2.community.DTO.User;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MapService {
    @FormUrlEncoded
    @POST("/users/data")
    Call<User> sendRunningData(@Header ("access-token") String token, @Field("distance") double distance);
}
