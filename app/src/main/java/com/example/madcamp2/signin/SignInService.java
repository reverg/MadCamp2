package com.example.madcamp2.signin;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignInService {
    @FormUrlEncoded
    @POST("/signin")
    Call<ResponseBody> getFunc(@Field("data") String data);

    @FormUrlEncoded
    @POST("/signup")
    Call<ResponseBody> postFunc(@Field("username") String username, @Field("password") String password);
}
