package com.example.madcamp2.community;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcamp2.R;
import com.example.madcamp2.RetrofitClient;
import com.example.madcamp2.auth.TokenManager;
import com.example.madcamp2.community.DTO.Group;
import com.example.madcamp2.community.DTO.User;
import com.google.android.material.card.MaterialCardView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.CommunityViewHolder> {

    Context mContext;
    ArrayList<Group> groupList;

    public CommunityAdapter(Context mContext, ArrayList<Group> mData) {
        this.mContext = mContext;
        this.groupList = mData;
    }

    public static class CommunityViewHolder extends RecyclerView.ViewHolder {
        private MaterialCardView community_group;
        //private ImageView group_img;
        private TextView group_name;
        private ImageView group_delete;

        public CommunityViewHolder(@NonNull View itemView) {
            super(itemView);
            community_group = itemView.findViewById(R.id.community_group_item);
            // group_img = itemView.findViewById(R.id.group_img);
            group_name = itemView.findViewById(R.id.group_name);
            // group_delete = itemView.findViewById(R.id.group_delete);
            };
        }
    @NonNull
    @Override
    public CommunityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.community_item, parent, false);
        CommunityViewHolder viewHolder = new CommunityViewHolder(view);

        viewHolder.community_group.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                List<User> userList = groupList.get(viewHolder.getAdapterPosition()).getMemberList();
                FragmentGroupInfo groupInfo = new FragmentGroupInfo(userList);
                fragmentManager.beginTransaction().add(R.id.fragment_community, groupInfo).addToBackStack(null).commit();
            }
        });

        viewHolder.community_group.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String token = TokenManager.getToken(mContext, TokenManager.TOKEN_KEY);
                deleteGroup(token, groupList.get(viewHolder.getAdapterPosition()).getGroupId(), viewHolder.getAdapterPosition());
                return true;
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityViewHolder holder, int position) {
        Group groupItem = groupList.get(position);
        holder.group_name.setText(groupItem.getGroupName());
        // holder.group_img.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public void deleteGroup(String token, int groupId, int pos) {
        Call<ResponseBody> callCommunity = RetrofitClient.getCommunityService()
                .deleteGroupFunc(token, groupId);
        callCommunity.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
                        groupList.remove(pos);
                        notifyItemRemoved(pos);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(mContext, "ANAUTHORIZED!",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("CommunityAdapter", t.getMessage());
                Toast.makeText(mContext, "Response Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}
