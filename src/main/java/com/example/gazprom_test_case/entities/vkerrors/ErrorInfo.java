package com.example.gazprom_test_case.entities.vkerrors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorInfo {

    private int error_code;
    private String error_msg;

    public ErrorInfo() {
    }

    public ErrorInfo(int error_code, String error_msg) {
        this.error_code = error_code;
        this.error_msg = error_msg;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "error_code=" + error_code +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }
}
