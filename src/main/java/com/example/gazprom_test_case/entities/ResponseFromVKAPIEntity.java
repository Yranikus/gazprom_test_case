package com.example.gazprom_test_case.entities;

import java.util.ArrayList;

public class ResponseFromVKAPIEntity<E> {

    private ArrayList<E> response;

    public ResponseFromVKAPIEntity() {
    }

    public ResponseFromVKAPIEntity(ArrayList<E> response) {
        this.response = response;
    }

    public ArrayList<E> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<E> response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ResponseFromVKAPI{" +
                "response=" + response +
                '}';
    }
}
