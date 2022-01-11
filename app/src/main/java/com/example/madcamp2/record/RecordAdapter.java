package com.example.madcamp2.record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcamp2.R;
import com.example.madcamp2.auth.TokenManager;
import com.example.madcamp2.community.CommunityAdapter;
import com.example.madcamp2.community.DTO.Group;
import com.example.madcamp2.community.DTO.User;
import com.example.madcamp2.community.FragmentGroupInfo;
import com.example.madcamp2.record.DTO.Record;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {
    Context mContext;
    ArrayList<Record> recordList;
    ConstraintLayout no_record;

    public RecordAdapter(Context mContext, ArrayList<Record> mData, ConstraintLayout no_record) {
        this.mContext = mContext;
        this.recordList = mData;
        this.no_record = no_record;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.record_item, parent, false);
        RecordAdapter.RecordViewHolder viewHolder = new RecordAdapter.RecordViewHolder(view);

        viewHolder.record_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentRecordInfo recordInfo = new FragmentRecordInfo(recordList.get(viewHolder.getAdapterPosition()));
                FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_record, recordInfo).addToBackStack(null).commit();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Record recordItem = recordList.get(position);
        holder.record_name.setText(recordItem.getRecordName());
        holder.record_info.setText(recordItem.getRecordInfo());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        private MaterialCardView record_group;
        private TextView record_name;
        private TextView record_info;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            record_group = itemView.findViewById(R.id.record_group_item);
            record_name = itemView.findViewById(R.id.record_name);
            record_info = itemView.findViewById(R.id.record_info);
        }
    }
}
