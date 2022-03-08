package com.comp4905.jobarc.Models;

import com.comp4905.jobarc.Enums.Status;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Job {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("createDate")
    @Expose
    private Timestamp createDate;

    @SerializedName("employerName")
    @Expose
    private String employerName;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("jobType")
    @Expose
    private String jobType;

    public Job(long id, String title, String description, Timestamp createDate, String employerName, String location, String jobType){
        this.id = id;
        this.title = title;
        this.description = description;
        this.createDate = createDate;
        this.employerName = employerName;
        this.location = location;
        this.jobType = jobType;
    }

    public long getJobId() {
        return id;
    }

    public long getId() { return id; }

    public String getEmployerName() { return employerName; }

    public String getLocation() { return location; }

    public String getJobType() { return jobType; }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }
}
