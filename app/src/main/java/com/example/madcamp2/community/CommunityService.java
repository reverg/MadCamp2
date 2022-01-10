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
    Call<Group> insertGroupFunc(@Header ("access-token") String token, @Field("name") String name, @Field("info") String info);

    @DELETE("/group/{groupId}")
    // Todo
    Call<ResponseBody> deleteGroupFunc(@Header ("access-token") String token, @Path("groupId") int groupId);

    @DELETE("/group/member")
    Call<ResponseBody> deleteMemberFunc(@Header ("access-token") String token, @Path("groupId") int groupId);

    @GET("/group/list")
    Call<ArrayList<Group>> getAllGroupFunc(@Header ("access-token") String token);

    @FormUrlEncoded
    @POST("/group/join")
    Call<Group> joinGroupFunc(@Header("access-token") String token, @Field("name") String name, @Field("code") String code);
}
