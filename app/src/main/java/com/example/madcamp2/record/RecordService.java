package com.example.madcamp2.record;

import com.example.madcamp2.record.DTO.Record;

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

public interface RecordService {
    @GET("/record")
    Call<ArrayList<Record>> getAllRecordFunc(@Header("access-token") String token);

    @FormUrlEncoded
    @POST("/record")
    Call<Record> insertRecord(@Header("access-token") String token,
                              //@Field("pathMarkers") ArrayList<LatLngObj> pathMarkers,
                              @Field("distance") double distance,
                              @Field("maxSpeed") double maxSpeed,
                              @Field("time") double time,
                              @Field("pathMarkers") String pathMarkers,
                              @Field("info") String info);

    @DELETE("/record/{recordId}")
        // Todo
    Call<ResponseBody> deleteRecordFunc(@Header ("access-token") String token, @Path("recordId") int recordId);
}
