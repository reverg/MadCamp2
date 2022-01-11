package com.example.madcamp2.record;

import static android.view.View.VISIBLE;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcamp2.R;
import com.example.madcamp2.RetrofitClient;
import com.example.madcamp2.auth.TokenManager;
import com.example.madcamp2.community.CommunityAdapter;
import com.example.madcamp2.community.DTO.Group;
import com.example.madcamp2.community.DTO.User;
import com.example.madcamp2.record.DTO.Record;
import com.google.gson.Gson;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRecord extends Fragment {
    View v;
    private ArrayList<Record> recordList;
    private RecyclerView recyclerview;
    private RecordAdapter recordAdapter;
    private ConstraintLayout no_record;

    public FragmentRecord() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_record, container, false);
        recyclerview = v.findViewById(R.id.record_view);

        no_record = v.findViewById(R.id.layout_no_record);
        recordList = new ArrayList<>();

        String token = TokenManager.getToken(getContext(), TokenManager.TOKEN_KEY);
        getRecordList(token);
        System.out.println(recordList.size());

        return v;
    }

    public ArrayList<Record> getRecordList(String token) {
        Call<ArrayList<Record>> callRecord = RetrofitClient.getRecordService().getAllRecordFunc(token);
        callRecord.enqueue(new Callback<ArrayList<Record>>() {
            @Override
            public void onResponse(Call<ArrayList<Record>> call, Response<ArrayList<Record>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Record> recordArrayList = response.body();

                    Gson gson = new Gson();
                    Log.d("FragmentRecord", gson.toJson(recordArrayList));

                    recordAdapter = new RecordAdapter(getActivity(), recordArrayList, no_record);
                    recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerview.setAdapter(recordAdapter);

                    if (recordArrayList.size() == 0) {
                        no_record.setVisibility(VISIBLE);
                    }

                } else {
                    Toast.makeText(getContext(), "error = " + String.valueOf(response.code()),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Record>> call, Throwable t) {
                Log.d("FragmentRecord", t.getMessage());
                Toast.makeText(getContext(), "Response Fail", Toast.LENGTH_LONG).show();
            }
        });

        return recordList;
    }
}