package com.example.madcamp2.community;

import static android.view.View.VISIBLE;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcamp2.R;
import com.example.madcamp2.RetrofitClient;
import com.example.madcamp2.auth.TokenManager;
import com.example.madcamp2.community.DTO.Group;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCommunity extends Fragment {

    View v;
    private ArrayList<Group> groupList;
    private RecyclerView recyclerview;
    private CommunityAdapter communityAdapter;
    private FloatingActionButton fab_main;
    private FloatingActionButton fab_make_group;
    private FloatingActionButton fab_join_group;
    private TextView fab_make_group_text;
    private TextView fab_join_group_text;
    boolean fabVisible = false;
    private ConstraintLayout no_group;

    TextInputEditText makeGroupName, makeGroupInfo;
    TextView makeGroupSave, makeGroupCancel;

    TextInputEditText joinGroupName, joinGroupCode;
    TextView joinGroupSave, joinGroupCancel;

    public FragmentCommunity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_community, container, false);
        recyclerview = v.findViewById(R.id.recycler_view);

        fab_main = v.findViewById(R.id.fab_main);
        fab_make_group = v.findViewById(R.id.fab_make_group);
        fab_join_group = v.findViewById(R.id.fab_join_group);
        fab_make_group_text = v.findViewById(R.id.fab_make_group_text);
        fab_join_group_text = v.findViewById(R.id.fab_join_group_text);

        no_group = v.findViewById(R.id.layout_no_group);

        fab_make_group.setVisibility(View.GONE);
        fab_join_group.setVisibility(View.GONE);
        fab_make_group_text.setVisibility(View.GONE);
        fab_join_group_text.setVisibility(View.GONE);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fabVisible) {
                    fab_make_group.show();
                    fab_join_group.show();
                    fab_make_group_text.setVisibility(VISIBLE);
                    fab_join_group_text.setVisibility(VISIBLE);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View alertView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_make_group, null, false);
                builder.setView(alertView);

                fab_make_group.hide();
                fab_join_group.hide();
                fab_make_group_text.setVisibility(View.GONE);
                fab_join_group_text.setVisibility(View.GONE);
                fabVisible = false;

                final AlertDialog dialog = builder.create();

                makeGroupName = (TextInputEditText) alertView.findViewById(R.id.make_group_name);
                makeGroupInfo = (TextInputEditText) alertView.findViewById(R.id.make_group_info);
                makeGroupSave = (TextView) alertView.findViewById(R.id.make_group_save);
                makeGroupCancel = (TextView) alertView.findViewById(R.id.make_group_cancel);

                makeGroupSave.setOnClickListener(view -> {
                    // 저장버튼 클릭
                    if (TextUtils.isEmpty(makeGroupName.getText().toString())) {
                        Toast.makeText(getActivity(), "Group Name Required",
                                Toast.LENGTH_LONG).show();
                    } else if (TextUtils.isEmpty(makeGroupName.getText().toString())) {
                        Toast.makeText(getActivity(), "Group Info Required",
                                Toast.LENGTH_LONG).show();
                    } else {
                        String token = TokenManager.getToken(getActivity(), TokenManager.TOKEN_KEY);
                        makeGroup(token, communityAdapter.getItemCount());
                    }
                    dialog.dismiss();
                });

                makeGroupCancel.setOnClickListener(view -> {
                    // 취소버튼 클릭
                    dialog.dismiss();
                });

                dialog.show();
            }
        });

        fab_join_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View alertView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_join_group, null, false);
                builder.setView(alertView);

                fab_make_group.hide();
                fab_join_group.hide();
                fab_make_group_text.setVisibility(View.GONE);
                fab_join_group_text.setVisibility(View.GONE);
                fabVisible = false;

                final AlertDialog dialog = builder.create();

                joinGroupName = (TextInputEditText) alertView.findViewById(R.id.join_group_name);
                joinGroupCode = (TextInputEditText) alertView.findViewById(R.id.join_group_code);
                joinGroupSave = (TextView) alertView.findViewById(R.id.join_group_save);
                joinGroupCancel = (TextView) alertView.findViewById(R.id.join_group_cancel);

                joinGroupSave.setOnClickListener(view -> {
                    // 저장버튼 클릭
                    if (TextUtils.isEmpty(joinGroupName.getText().toString())) {
                        Toast.makeText(getActivity(), "Group Name Required",
                                Toast.LENGTH_LONG).show();
                    } else if (TextUtils.isEmpty(joinGroupCode.getText().toString())) {
                        Toast.makeText(getActivity(), "Group Code Required",
                                Toast.LENGTH_LONG).show();
                    } else {
                        String token = TokenManager.getToken(getActivity(), TokenManager.TOKEN_KEY);
                        joinGroup(token, communityAdapter.getItemCount());
                    }
                    dialog.dismiss();
                });

                joinGroupCancel.setOnClickListener(view -> {
                    // 취소버튼 클릭
                    dialog.dismiss();
                });

                dialog.show();
            }
        });

        String token = TokenManager.getToken(getContext(), TokenManager.TOKEN_KEY);
        getGroupList(token);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ArrayList<Group> getGroupList(String token) {
        Call<ArrayList<Group>> callCommunity = RetrofitClient.getCommunityService()
                .getAllGroupFunc(token);
        callCommunity.enqueue(new Callback<ArrayList<Group>>() {
            @Override
            public void onResponse(Call<ArrayList<Group>> call, Response<ArrayList<Group>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Group> groupArrayList = response.body();
                    groupList = groupArrayList;

                    communityAdapter = new CommunityAdapter(getActivity(), groupArrayList, no_group);
                    recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerview.setAdapter(communityAdapter);

                    if (groupList.size() == 0) {
                        no_group.setVisibility(VISIBLE);
                    }

                } else {
                    Toast.makeText(getContext(), "error = " + String.valueOf(response.code()),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Group>> call, Throwable t) {
                Log.d("FragmentCommunity", t.getMessage());
                Toast.makeText(getContext(), "Response Fail", Toast.LENGTH_LONG).show();
            }
        });

        return groupList;
    }

    public void makeGroup(String token, int pos) {
        Call<Group> callCommunity = RetrofitClient.getCommunityService()
                .insertGroupFunc(token, makeGroupName.getText().toString(), makeGroupInfo.getText().toString());
        callCommunity.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                if (response.isSuccessful()) {
                    Group result = response.body();
                    groupList.add(result);

                    if (groupList.size() == 1) {
                        no_group.setVisibility(View.GONE);
                    }

                    communityAdapter.notifyItemInserted(pos);

                    AlertDialog.Builder dgBuilder = new AlertDialog.Builder(getActivity());
                    View successAlertView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_create_success, null, false);
                    dgBuilder.setView(successAlertView);

                    final AlertDialog successDialog = dgBuilder.create();
                    successDialog.show();

                    ImageView cancelBtn = successAlertView.findViewById(R.id.group_dialog_cancel);
                    TextView tokenTxt = successAlertView.findViewById(R.id.group_token);

                    tokenTxt.setText(result.getGroupCode());
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            successDialog.dismiss();
                        }
                    });

                } else {
                    Toast.makeText(getActivity(), "error = " + String.valueOf(response.code()),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                Log.d("MakeGroupDialog", t.getMessage());
                Toast.makeText(getActivity(), "Response Fail", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void joinGroup(String token, int pos) {
        Call<Group> callCommunity = RetrofitClient.getCommunityService()
                .joinGroupFunc(token, joinGroupName.getText().toString(), joinGroupCode.getText().toString());
        callCommunity.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                if (response.isSuccessful()) {
                    Group result = response.body();
                    groupList.add(result);

                    if (groupList.size() == 1) {
                        no_group.setVisibility(View.GONE);
                    }

                    communityAdapter.notifyItemInserted(pos);
                } else {
                    Toast.makeText(getActivity(), "error = " + String.valueOf(response.code()),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                Log.d("JoinGroupDialog", t.getMessage());
                Toast.makeText(getActivity(), "Response Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}