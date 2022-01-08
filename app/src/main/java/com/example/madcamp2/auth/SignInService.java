package com.example.madcamp2.auth;

import com.example.madcamp2.auth.DTO.SignInResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignInService {
    @FormUrlEncoded
    @POST("/signin")
    Call<SignInResult> signinFunc(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/signup")
    Call<ResponseBody> signupFunc(@Field("username") String username, @Field("password") String password,
                                  @Field("displayName") String displayName);
}
