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

    public Job(long id, String title, String description, Timestamp createDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.createDate = createDate;
    }

    public long getJobId() {
        return id;
    }

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
