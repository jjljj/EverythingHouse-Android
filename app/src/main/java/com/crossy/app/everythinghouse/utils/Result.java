package com.crossy.app.everythinghouse.utils;

/**
 * Created by ljj on 2014/12/25.
 */
public class Result {
    private boolean success;
    private String message;

    public Result(boolean isSuccess, String resultMessage){
        success = isSuccess;
        message = resultMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
