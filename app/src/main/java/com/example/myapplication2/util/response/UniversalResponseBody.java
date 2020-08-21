package com.example.myapplication2.util.response;

import com.google.gson.annotations.Expose;

public class UniversalResponseBody<T> {
    @Expose
    private int errorCode;

    @Expose
    private String message;

    public UniversalResponseBody(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public UniversalResponseBody(int errorCode, String message, T data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    //@Expose
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
