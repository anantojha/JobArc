package com.example.JobArc.ResponseModels;

import com.example.JobArc.Enums.Status;

import java.awt.desktop.PreferencesEvent;

public class RegistrationResponse {
    String status;
    long id;
    String name;

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

    public String getName(){
        return name;
    }
}
