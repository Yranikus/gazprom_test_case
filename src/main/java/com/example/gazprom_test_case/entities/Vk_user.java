package com.example.gazprom_test_case.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vk_user {

    private int id;
    private String last_name;
    private String first_name;
    private String nickname;
    private boolean is_closed;

    public Vk_user() {
    }

    public Vk_user(int id, String last_name, String first_name, String nickname, boolean is_closed) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.nickname = nickname;
        this.is_closed = is_closed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    @Override
    public String toString() {
        return "Vk_user{" +
                "id=" + id +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", is_closed=" + is_closed +
                '}';
    }
}
