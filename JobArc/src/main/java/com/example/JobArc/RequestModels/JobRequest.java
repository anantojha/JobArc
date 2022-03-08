package com.example.JobArc.RequestModels;

import javax.validation.constraints.NotBlank;

public class JobRequest {

    private @NotBlank String title;
    private @NotBlank String description;
    private @NotBlank long employerId;
    private @NotBlank String location;
    private @NotBlank String jobType;

    public JobRequest(String title, String description, long employerId, String location, String jobType) {
        this.title = title;
        this.description = description;
        this.employerId = employerId;
        this.location = location;
        this.jobType = jobType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getEmployerId() {
        return employerId;
    }

    public String getLocation() { return location; }

    public String getJobType() { return jobType; }
}
