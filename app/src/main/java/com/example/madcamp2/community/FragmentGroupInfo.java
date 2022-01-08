package com.example.madcamp2.community;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcamp2.R;
import com.example.madcamp2.community.DTO.User;

import java.util.ArrayList;
import java.util.List;

public class FragmentGroupInfo extends Fragment {
    View v;
    private List<User> userList;
    private RecyclerView recyclerview;
    private GroupInfoAdapter groupInfoAdapter;


    public FragmentGroupInfo(List<User> userList) {
        this.userList = userList;
        Log.d("FragmentGroupInfo", String.valueOf(userList.size()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_group_info, container, false);
        recyclerview = v.findViewById(R.id.recycler_view);

        groupInfoAdapter = new GroupInfoAdapter(getContext(), userList);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(groupInfoAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
