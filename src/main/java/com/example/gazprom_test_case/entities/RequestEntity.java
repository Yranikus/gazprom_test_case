package com.example.gazprom_test_case.entities;

public class RequestEntity {

    private String user_id;
    private String group_id;

    public RequestEntity() {
    }

    public RequestEntity(String user_id, String group_id) {
        this.user_id = user_id;
        this.group_id = group_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
