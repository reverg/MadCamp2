package com.example.madcamp2.community;

import java.time.LocalDate;
import java.util.List;

public class Group {
    private int groupId;
    private String groupName;
    private String groupInfo;
    private List<User> memberList;
    private String createdDate;

    public Group(int groupId, String groupName, String groupInfo, List<User> memberList){
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupInfo = groupInfo;
        this.memberList = memberList;

        // change this code to get date from server
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            createdDate = LocalDate.now().toString();
        } else {
            createdDate = "Android Version Check Needed";
        }
    }

    public String getGroupName(){
        return groupName;
    }

    public String getGroupInfo(){
        return groupInfo;
    }

    public List<User> getMemberList(){
        return memberList;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }

    public void setGroupInfo(String groupInfo){
        this.groupName = groupInfo;
    }

    public void setMemberList(List<User> memberList){
        this.memberList = memberList;
    }
}
