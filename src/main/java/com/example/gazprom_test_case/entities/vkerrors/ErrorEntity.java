package com.example.gazprom_test_case.entities.vkerrors;



public class ErrorEntity {

    private ErrorInfo error;


    public ErrorEntity() {
    }

    public ErrorEntity(ErrorInfo error) {
        this.error = error;
    }

    public ErrorInfo getError() {
        return error;
    }

    public void setError(ErrorInfo error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ErrorEntity{" +
                "error=" + error +
                '}';
    }
}
