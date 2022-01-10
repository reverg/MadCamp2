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
    private User owner;
    private TextView userName, owner_ranking;

    public FragmentGroupInfo(List<User> userList, User owner) {
        this.userList = userList;
        this.owner = owner;
        Log.d("FragmentGroupInfo", String.valueOf(userList.size()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_group_info, container, false);
        recyclerview = v.findViewById(R.id.recycler_view);

        userName = v.findViewById(R.id.my_name);

        if (owner.getUserName() != null) {
            userName.setText(owner.getUserName());
        }

        owner_ranking = v.findViewById(R.id.my_ranking);

        int myRank = userList.indexOf(owner);
        owner_ranking.setText("No."+Integer.toString(myRank));

        groupInfoAdapter = new GroupInfoAdapter(getContext(), userList, owner);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(groupInfoAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
