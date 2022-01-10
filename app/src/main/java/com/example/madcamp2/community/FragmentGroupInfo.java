package com.example.madcamp2.community;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcamp2.R;
import com.example.madcamp2.community.DTO.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragmentGroupInfo extends Fragment {
    View v;
    private List<User> userList;
    private RecyclerView recyclerview;
    private ImageView clearButton;
    private GroupInfoAdapter groupInfoAdapter;
    private User owner;
    private TextView userName, owner_ranking;


    public FragmentGroupInfo(List<User> userList, User owner) {
        this.userList = userList;
        this.owner = owner;
        Collections.sort(this.userList, new UserComparator());
        Log.d("FragmentGroupInfo", String.valueOf(userList.size()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            v = inflater.inflate(R.layout.fragment_group_info, container, false);
            recyclerview = v.findViewById(R.id.recycler_view);

            groupInfoAdapter = new GroupInfoAdapter(getActivity(), userList, owner);
            recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerview.setAdapter(groupInfoAdapter);

            userName = v.findViewById(R.id.my_name);
        v = inflater.inflate(R.layout.fragment_group_info, container, false);
        recyclerview = v.findViewById(R.id.recycler_view);
        clearButton = v.findViewById(R.id.clear_button);
        userName = v.findViewById(R.id.my_name);

            if (owner.getUserName() != null) {
                userName.setText(owner.getUserName());
            }

            owner_ranking = v.findViewById(R.id.my_ranking);

            int myRank = -1;
            String newRank = "No. ";

        owner_ranking.setText("No." + Integer.toString(myRank));

            for (int i =0; i < userList.size(); i++) {
                if (userList.get(i).getUserId() == owner.getUserId()) {
                    myRank = i + 1;
                }
            }

            newRank += Integer.toString(myRank);
            owner_ranking.setText(newRank);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(FragmentGroupInfo.this).commit();
                fragmentManager.popBackStack();
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

class UserComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        if (u1.getUserDistance() < u2.getUserDistance()) {
            return 1;
        } else if (u1.getUserDistance() == u2.getUserDistance()) {
            if (u1.getUserId() > u2.getUserId()) {
                return 1;
            }
        }
        return -1;
    }
}