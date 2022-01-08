package com.example.madcamp2.community;

import com.example.madcamp2.community.DTO.Group;

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

public interface CommunityService {
    @FormUrlEncoded
    @POST("/group")
    Call<Group> insertGroupFunc(@Header ("access-token") String token, @Field("name") String name);

    @DELETE("/group/{groupId}")
    // Todo
    Call<ResponseBody> deleteGroupFunc(@Header ("access-token") String token, @Path("groupId") int groupId);

    @GET("/group")
    Call<ArrayList<Group>> getAllGroupFunc();
}
