package com.example.madcamp2.community.DTO;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Group {
    @SerializedName("_id")
    private int groupId;
    @SerializedName("name")
    private String groupName;
    @SerializedName("info")
    private String groupInfo = "";
    @SerializedName("owner")
    private User groupOwner;
    @SerializedName("member")
    private ArrayList<User> memberList;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("groupCode")
    private String groupCode;

    public Group(int groupId, String groupName, User groupOwner,
                 ArrayList<User> memberList, String createdAt) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupOwner = groupOwner;
        this.memberList = memberList;
        this.createdAt = createdAt;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public ArrayList<User> getMemberList() {
        return memberList;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupInfo(String groupInfo) {
        this.groupName = groupInfo;
    }

    public void setMemberList(ArrayList<User> memberList) {
        this.memberList = memberList;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public User getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(User groupOwner) {
        this.groupOwner = groupOwner;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getGroupInfo() {
        return groupInfo;
    }
}
