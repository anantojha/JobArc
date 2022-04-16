package com.comp4905.jobarc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("id")
    @Expose
    private long id;

    public RegistrationResponse(String status, long id, String name){
        this.status = status;
        this.id = id;
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
