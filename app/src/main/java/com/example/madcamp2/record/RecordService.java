package com.example.madcamp2.record;

import com.example.madcamp2.record.DTO.Record;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RecordService {
    @GET("/record/list")
    Call<ArrayList<Record>> getAllRecordFunc(@Header("access-token") String token);
}
