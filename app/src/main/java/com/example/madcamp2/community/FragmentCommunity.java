package com.example.madcamp2.community;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentCommunity extends Fragment {

    View v;
    private List<Group> groupList;
    private RecyclerView recyclerview;
    private CommunityAdapter communityAdapter;
    private FloatingActionButton fab_main;
    private FloatingActionButton fab_make_group;
    private FloatingActionButton fab_join_group;
    private TextView fab_make_group_text;
    private TextView fab_join_group_text;
    boolean fabVisible = false;

    public FragmentCommunity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_community, container, false);
        groupList = getGroupList();
        recyclerview = v.findViewById(R.id.recycler_view);

        fab_main = v.findViewById(R.id.fab_main);
        fab_make_group = v.findViewById(R.id.fab_make_group);
        fab_join_group = v. findViewById(R.id.fab_join_group);
        fab_make_group_text = v.findViewById(R.id.fab_make_group_text);
        fab_join_group_text = v.findViewById(R.id.fab_join_group_text);

        fab_make_group.setVisibility(View.GONE);
        fab_join_group.setVisibility(View.GONE);
        fab_make_group_text.setVisibility(View.GONE);
        fab_join_group_text.setVisibility(View.GONE);


        communityAdapter = new CommunityAdapter(getContext(), groupList);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(communityAdapter);

        fab_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!fabVisible){
                    fab_make_group.show();
                    fab_join_group.show();
                    fab_make_group_text.setVisibility(View.VISIBLE);
                    fab_join_group_text.setVisibility(View.VISIBLE);
                    fabVisible = true;
                } else {
                    fab_make_group.hide();
                    fab_join_group.hide();
                    fab_make_group_text.setVisibility(View.GONE);
                    fab_join_group_text.setVisibility(View.GONE);
                    fabVisible = false;
                }
            }

        });

        fab_make_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_make_group.hide();
                fab_join_group.hide();
                fab_make_group_text.setVisibility(View.GONE);
                fab_join_group_text.setVisibility(View.GONE);
                fabVisible = false;
            }
        });

        fab_join_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_make_group.hide();
                fab_join_group.hide();
                fab_make_group_text.setVisibility(View.GONE);
                fab_join_group_text.setVisibility(View.GONE);
                fabVisible = false;
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ArrayList<Group> getGroupList() {
        ArrayList<Group> sampleGroupList = new ArrayList<>();
        sampleGroupList.add(new Group(1, "sample1", "1st group", null));
        sampleGroupList.add(new Group(2, "sample2", "2nd group", null));
        sampleGroupList.add(new Group(3, "sample3", "3rd group", null));

        return sampleGroupList;
    }
}