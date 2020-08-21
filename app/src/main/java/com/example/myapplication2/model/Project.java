package com.example.myapplication2.model;

import java.util.Date;

public class Project {
    private Integer groupId;

    private String groupName;

    private String groupBeginDate;

    private String groupEndDate;

    private Integer groupCanBeSearch;

    private Integer groupCreator;

    private String groupIntroduction;

    private Integer groupStatus;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupBeginDate() {
        return groupBeginDate;
    }

    public void setGroupBeginDate(String groupBeginDate) {
        this.groupBeginDate = groupBeginDate;
    }

    public String getGroupEndDate() {
        return groupEndDate;
    }

    public void setGroupEndDate(String groupEndDate) {
        this.groupEndDate = groupEndDate;
    }

    public Integer getGroupCanBeSearch() {
        return groupCanBeSearch;
    }

    public void setGroupCanBeSearch(Integer groupCanBeSearch) {
        this.groupCanBeSearch = groupCanBeSearch;
    }

    public Integer getGroupCreator() {
        return groupCreator;
    }

    public void setGroupCreator(Integer groupCreator) {
        this.groupCreator = groupCreator;
    }

    public String getGroupIntroduction() {
        return groupIntroduction;
    }

    public void setGroupIntroduction(String groupIntroduction) {
        this.groupIntroduction = groupIntroduction;
    }

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }
}
