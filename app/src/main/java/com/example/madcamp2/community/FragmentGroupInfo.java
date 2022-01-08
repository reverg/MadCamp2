package com.example.madcamp2.community;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class FragmentGroupInfo extends Fragment {
    View v;
    int groupId;
    private List<User> userList;
    private RecyclerView recyclerview;
    private GroupInfoAdapter groupInfoAdapter;


    public FragmentGroupInfo(int groupId) {
        this.groupId = groupId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_group_info, container, false);
        userList = getUserList();
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

    public ArrayList<User> getUserList() {
        // initialize user list with group id;
        ArrayList<User> sampleUserList = new ArrayList<>();
        sampleUserList.add(new User("AAA", groupId));
        sampleUserList.add(new User("BBB", groupId));
        sampleUserList.add(new User("CCC", groupId));

        return sampleUserList;
    }
}
