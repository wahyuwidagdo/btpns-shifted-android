package com.example.tugasday4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PulsaResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private Pulsa data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Pulsa getData() {
        return data;
    }

    public void setData(Pulsa data) {
        this.data = data;
    }
}
