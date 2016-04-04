package com.example.ruby.loltellme.utils.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class APIError {

    @SerializedName("status")
    @Expose
    private Status status;

    public APIError() {
    }

    public Status getStatus() {
        return status;
    }

    public class Status {
        String message;
        int status_code;

        public Status() {
        }

        public String getMessage() {
            return message;
        }

        public int getStatus_code() {
            return status_code;
        }
    }
}
