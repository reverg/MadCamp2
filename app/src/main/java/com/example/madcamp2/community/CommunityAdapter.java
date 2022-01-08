package com.example.madcamp2.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcamp2.R;

import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.GroupViewHolder> {

    Context mContext;
    List<Group> groupList;

    public CommunityAdapter(Context mContext, List<Group> mData) {
        this.mContext = mContext;
        this.groupList = mData;
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout community_group;
        private ImageView group_img;
        private TextView group_name;
        private TextView group_info;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            community_group = itemView.findViewById(R.id.community_group_item);
            group_img = itemView.findViewById(R.id.group_img);
            group_name = itemView.findViewById(R.id.group_name);
            group_info = itemView.findViewById(R.id.group_info);
        }
    }
    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.community_item, parent, false);
        GroupViewHolder viewHolder = new GroupViewHolder(view);

        viewHolder.community_group.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                int groupId = groupList.get(viewHolder.getAdapterPosition()).getGroupId();
                FragmentGroupInfo groupInfo = new FragmentGroupInfo(groupId);
                fragmentManager.beginTransaction().add(R.id.fragment_community, groupInfo).addToBackStack(null).commit();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        Group groupItem = groupList.get(position);
        holder.group_name.setText(groupItem.getGroupName());
        holder.group_info.setText(groupItem.getGroupInfo());
        holder.group_img.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }
}
