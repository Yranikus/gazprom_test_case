package com.example.gazprom_test_case.entities;

public class ResponceToClientEntity {

    private String last_name;
    private String first_name;
    private String middle_name;
    private boolean member;


    public ResponceToClientEntity(String last_name, String first_name, String middle_name, boolean member) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.member = member;
    }

    public ResponceToClientEntity() {
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

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "ResponceToClientEntity{" +
                "last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", member=" + member +
                '}';
    }
}
