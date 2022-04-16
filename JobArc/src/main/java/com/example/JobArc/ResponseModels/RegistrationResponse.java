package com.example.JobArc.ResponseModels;

import com.example.JobArc.Enums.Status;

import java.awt.desktop.PreferencesEvent;

public class RegistrationResponse {
    Status status;
    long id;
    String name;

    public RegistrationResponse(Status status, long id, String name){
        this.status = status;
        this.id = id;
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public String getName(){
        return name;
    }
}
