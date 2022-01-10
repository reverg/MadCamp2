package com.example.madcamp2.community;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcamp2.R;
import com.example.madcamp2.RetrofitClient;
import com.example.madcamp2.auth.SignInActivity;
import com.example.madcamp2.auth.SignUpActivity;
import com.example.madcamp2.auth.TokenManager;
import com.example.madcamp2.community.DTO.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentGroupInfo extends Fragment {
    View v;
    private List<User> userList;
    private RecyclerView recyclerview;
    private ImageView clearButton;
    private GroupInfoAdapter groupInfoAdapter;
    private TextView userName, owner_ranking;
    private int userId;
    private String token;
    private int groupId;

    public FragmentGroupInfo(List<User> userList, int groupId) {
        this.userList = userList;
        this.groupId = groupId;
        Collections.sort(this.userList, new UserComparator());
        Log.d("FragmentGroupInfo", String.valueOf(userList.size()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            v = inflater.inflate(R.layout.fragment_group_info, container, false);
            recyclerview = v.findViewById(R.id.recycler_view);

            groupInfoAdapter = new GroupInfoAdapter(getActivity(), userList);
            recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerview.setAdapter(groupInfoAdapter);

            userName = v.findViewById(R.id.my_name);
            clearButton = v.findViewById(R.id.clear_button);

            token = TokenManager.getToken(getContext(), TokenManager.TOKEN_KEY);
            userId = TokenManager.getUserId(getContext(), token);

            int myRank = -1;
            String userDisplayName = "";

            for (int i =0; i < userList.size(); i++) {
                if (userList.get(i).getUserId() == userId) {
                    myRank = i + 1;
                    userDisplayName = userList.get(i).getDisplayName();
                }
            }

            userName.setText(userDisplayName);

            owner_ranking = v.findViewById(R.id.my_ranking);

            String newRank = "No. ";
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

        // if (token != "") {
        //     deleteMember(token, groupId);
        // }

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void deleteMember(String token, int groupId) {
        Call<ResponseBody> callCommunity = RetrofitClient.getCommunityService()
                .deleteMemberFunc(token, groupId);
        callCommunity.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(getContext(), "error = " + String.valueOf(response.code()),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("SignInActivity", t.getMessage());
                Toast.makeText(getContext(), "Response Fail", Toast.LENGTH_LONG).show();
            }
        });
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