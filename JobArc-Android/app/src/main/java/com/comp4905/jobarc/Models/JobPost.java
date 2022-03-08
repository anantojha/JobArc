package com.comp4905.jobarc.Models;

public class JobPost {

    private String title;
    private String description;
    private long employerId;
    private String location;
    private String jobType;

    public JobPost(String title, String description, long employerId, String location, String jobType) {
        this.title = title;
        this.description = description;
        this.employerId = employerId;
        this.location = location;
        this.jobType = jobType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(long employerId) {
        this.employerId = employerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
}
