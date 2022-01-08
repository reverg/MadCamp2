package com.example.madcamp2.community;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.madcamp2.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentCommunity extends Fragment {

    View v;
    private List<Group> groupList;

    public FragmentCommunity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_community, container, false);
        groupList = getGroupList();
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