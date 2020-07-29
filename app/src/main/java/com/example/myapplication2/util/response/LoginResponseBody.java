package com.example.myapplication2.util.response;

import com.google.gson.annotations.Expose;

public class LoginResponseBody {

    @Expose
    private int errorCode;

    @Expose
    private String message;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
